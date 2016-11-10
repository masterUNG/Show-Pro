package appewtc.masterung.showpro;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private ImageView aisImageView, dtacImageView, trueImageView;
    private MyManage myManage;
    private MyConstant myConstant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myManage = new MyManage(MainActivity.this);
        myConstant = new MyConstant();

        //Bind Widget
        aisImageView = (ImageView) findViewById(R.id.imageView);
        dtacImageView = (ImageView) findViewById(R.id.imageView2);
        trueImageView = (ImageView) findViewById(R.id.imageView3);

        //Image Controller
        aisImageView.setOnClickListener(this);
        dtacImageView.setOnClickListener(this);
        trueImageView.setOnClickListener(this);

        //Test Add Value to SQLite
        //testAddValue();

        //Check Internet
        checkInternet();



    }   // Main Method

    private void checkInternet() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if ((networkInfo != null) && (networkInfo.isConnected())) {

            //Internet OK
            deleteAllSQLite();


        }   // if

    }   // checkInternet

    private void deleteAllSQLite() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(myConstant.getDatabase_name(),
                MODE_PRIVATE, null);
        String[] strings = myConstant.getTableStrings();
        for (int i=1;i<strings.length;i++) {
            sqLiteDatabase.delete(strings[i], null, null);

            SynPromotion synPromotion = new SynPromotion(MainActivity.this, i);
            synPromotion.execute(myConstant.getUrlJSONString());

        }   // for


    }

    private class SynPromotion extends AsyncTask<String, Void, String> {

        //Explicit
        private Context context;
        private int anInt;

        public SynPromotion(Context context, int anInt) {
            this.context = context;
            this.anInt = anInt;
        }

        @Override
        protected String doInBackground(String... strings) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("isAdd", "true")
                        .add("pro_brand", Integer.toString(anInt))
                        .build();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strings[0]).post(requestBody).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                Log.d("10novV1", "e doIn ==> " + e.toString());
                return null;
            }

        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("10novV1", "index ==> " + anInt);
            Log.d("10novV1", "JSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);
                String[] columnStrings = myConstant.getColumnStrings();

                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String[] resultStrings = new String[columnStrings.length];
                    resultStrings[0] = null;

                    for (int i1=1;i<columnStrings.length;i++) {

                        resultStrings[i1] = jsonObject.getString(columnStrings[i1]);

                    }   // for2

                    myManage.addValueToSQLite(anInt, resultStrings);

                }   // for

            } catch (Exception e) {
                e.printStackTrace();
            }


        }   // onPost

    }   // SynPromotion Class


    private void testAddValue() {
        String[] strings = new String[]{null,
                "name", "detail",
                "price", "reg",
                "period", "date"};

        myManage.addValueToSQLite(1, strings);
        myManage.addValueToSQLite(2, strings);
        myManage.addValueToSQLite(3, strings);


    }

    @Override
    public void onClick(View view) {

        int i = 0;

        switch (view.getId()) {
            case R.id.imageView:
                i = 1;
                break;
            case R.id.imageView2:
                i = 2;
                break;
            case R.id.imageView3:
                i = 3;
                break;
        }   // switch

        //Intent to ShowPro
        Intent intent = new Intent(MainActivity.this, ShowPro.class);
        intent.putExtra("Index", i);
        startActivity(intent);



    }   // onClick

}   // Main Class

package appewtc.masterung.showpro;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

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
        }


    }

    private class SynPromotion extends AsyncTask<String, Void, String> {

        //Explicit
        private Context context;

        public SynPromotion(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
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

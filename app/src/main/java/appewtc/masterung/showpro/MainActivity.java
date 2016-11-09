package appewtc.masterung.showpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private ImageView aisImageView, dtacImageView, trueImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        aisImageView = (ImageView) findViewById(R.id.imageView);
        dtacImageView = (ImageView) findViewById(R.id.imageView2);
        trueImageView = (ImageView) findViewById(R.id.imageView3);

        //Image Controller
        aisImageView.setOnClickListener(this);
        dtacImageView.setOnClickListener(this);
        trueImageView.setOnClickListener(this);

    }   // Main Method

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

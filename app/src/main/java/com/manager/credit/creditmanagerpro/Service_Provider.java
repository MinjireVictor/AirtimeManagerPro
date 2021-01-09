package com.manager.credit.creditmanagerpro;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;



public class Service_Provider extends AppCompatActivity implements View.OnClickListener{

    Button safaricom,airtel,telkom;
    TextView tv;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service__provider);
        safaricom=(Button)findViewById(R.id.safaricom_button);
        airtel=(Button)findViewById(R.id.airtel_button);
        telkom=(Button)findViewById(R.id.telkom_button);
        tv=(TextView)findViewById(R.id.textView);
        safaricom.setOnClickListener(this);
        airtel.setOnClickListener(this);
        telkom.setOnClickListener(this);
        Typeface myTypeface= Typeface.createFromAsset(getAssets(),"fonts/GothamBook.otf");
        Typeface myTypeface2= Typeface.createFromAsset(getAssets(),"fonts/GothamBold.ttf");
        safaricom.setTypeface(myTypeface2);
        airtel.setTypeface(myTypeface2);
        telkom.setTypeface(myTypeface2);
        tv.setTypeface(myTypeface2);


        MobileAds.initialize(this,"ca-app-pub-1383320353699684/3471831523");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);





    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.safaricom_button:
                Intent intent= new Intent(Service_Provider.this,NewSafaricomDailer.class);
                startActivity(intent);
                break;

            case R.id.airtel_button:
                Intent intent2= new Intent(Service_Provider.this, AirtelTariffActivity.class);
                startActivity(intent2);
                break;

            case  R.id.telkom_button:
                Intent intent1= new Intent(Service_Provider.this,TelkomTarrifActivity.class);
                startActivity(intent1);
                break;
        }
    }
}

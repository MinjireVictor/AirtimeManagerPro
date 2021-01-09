package com.manager.credit.creditmanagerpro;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class TelkomTarrifActivity extends AppCompatActivity implements View.OnClickListener {

    Button Holla, Hollapremium, tujuane,call_bila_worries;
    TextView Tv1;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telkom_tarrif);

        Holla= (Button)findViewById(R.id.hollaBundles);
        Hollapremium= (Button)findViewById(R.id.hollaPremium);
        tujuane= (Button)findViewById(R.id.tujuane);
        Tv1=(TextView)findViewById(R.id.textView7);
        call_bila_worries=(Button) findViewById(R.id.call_bila_worries);
        Typeface myTypeface2= Typeface.createFromAsset(getAssets(),"fonts/PoppinsBlack.otf");

        Hollapremium.setTypeface(myTypeface2);
        Holla.setTypeface(myTypeface2);
        tujuane.setTypeface(myTypeface2);
        Tv1.setTypeface(myTypeface2);
        call_bila_worries.setTypeface(myTypeface2);
        Holla.setOnClickListener(this);
        tujuane.setOnClickListener(this);
        Hollapremium.setOnClickListener(this);
        call_bila_worries.setOnClickListener(this);


        TranslateAnimation animation= new TranslateAnimation(200.0f,0.0f,0.0f,0.0f);
        animation.setDuration(500);
        animation.setStartOffset(150);
        animation.setRepeatCount(0);
        animation.setFillAfter(false);

        TranslateAnimation animation2= new TranslateAnimation(150.0f,0.0f,0.0f,0.0f);
        animation2.setDuration(700);
        animation2.setStartOffset(150);
        animation2.setRepeatCount(0);
        animation2.setFillAfter(false);

        TranslateAnimation animation3= new TranslateAnimation(180.0f,0.0f,0.0f,0.0f);
        animation3.setDuration(700);
        animation3.setStartOffset(150);
        animation3.setRepeatCount(0);
        animation3.setFillAfter(false);

        TranslateAnimation animation4= new TranslateAnimation(200.0f,0.0f,0.0f,0.0f);
        animation4.setDuration(700);
        animation4.setStartOffset(150);
        animation4.setRepeatCount(0);
        animation4.setFillAfter(false);

        TranslateAnimation animation5= new TranslateAnimation(120.0f,0.0f,0.0f,0.0f);
        animation5.setDuration(700);
        animation5.setStartOffset(150);
        animation5.setRepeatCount(0);
        animation5.setFillAfter(false);

        TranslateAnimation animation6= new TranslateAnimation(130.0f,0.0f,0.0f,0.0f);
        animation6.setDuration(700);
        animation6.setRepeatCount(0);
        animation6.setStartOffset(150);
        animation6.setFillAfter(false);


        Tv1.startAnimation(animation);
        Holla.startAnimation(animation2);
        Hollapremium.startAnimation(animation3);
        tujuane.startAnimation(animation);
        call_bila_worries.startAnimation(animation3);

        MobileAds.initialize(this,"ca-app-pub-1383320353699684/1879548255");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.hollaBundles:
                Intent intent = new Intent (TelkomTarrifActivity.this, Holla_dailer.class);
                startActivity(intent);
                break;

            case R.id.hollaPremium:
                Intent intent2 = new Intent (TelkomTarrifActivity.this, NiajeDailer.class);
                startActivity(intent2);
                break;

            case R.id.tujuane:

                    Intent intent3 = new Intent (TelkomTarrifActivity.this, Tujuane_dailer.class);
                    startActivity(intent3);
                    break;
            default:
                    Intent intent4=new Intent(TelkomTarrifActivity.this,CallBilaWorriesDailer.class);
                    startActivity(intent4);
                    break;
        }
    }
}

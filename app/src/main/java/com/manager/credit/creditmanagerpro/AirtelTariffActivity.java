package com.manager.credit.creditmanagerpro;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AirtelTariffActivity extends AppCompatActivity implements View.OnClickListener{

    Button Tubonge,Airtel23;
    TextView Tv1;
    private static  final int MAKE_CALL_PERMISSION_REQUEST_CODE=1;

    ImageView AlertImageView;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airtel_tariff);

        Tubonge=(Button)findViewById(R.id.TubongeButton);
        Airtel23=(Button)findViewById(R.id.Airtel23);


        Tv1=(TextView)findViewById(R.id.textView8);
        AlertImageView=(ImageView)findViewById(R.id.alertTitle);

        Typeface myTypeface2= Typeface.createFromAsset(getAssets(),"fonts/PoppinsBlack.otf");
        Tubonge.setTypeface(myTypeface2);
        Tv1.setTypeface(myTypeface2);
        Airtel23.setTypeface(myTypeface2);


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

        Tv1.startAnimation(animation2);
        Tubonge.startAnimation(animation4);
        Airtel23.startAnimation(animation);
        AlertImageView.setAnimation(animation2);


        Airtel23.setOnClickListener(this);
        Tubonge.setOnClickListener(this);
        AlertImageView.setOnClickListener(this);
        MobileAds.initialize(this,"ca-app-pub-1383320353699684/1577183972");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.TubongeButton:
                Intent intent= new Intent(AirtelTariffActivity.this,TubongeDailer.class);
                startActivity(intent);

                break;

                case R.id.Airtel23:
                    Intent intent2= new Intent(AirtelTariffActivity.this, AirtelTwoBobDailer.class);
                    startActivity(intent2);

                    break;
            case R.id.alertTitle:

                AlertDialog.Builder alertBuilder=new AlertDialog.Builder(AirtelTariffActivity.this);
                alertBuilder.setTitle("Check your airtel tariff");
                alertBuilder.setMessage("To check your airtel tariff dail *121#, then select My account, then select Tariff");
                alertBuilder.setPositiveButton("Dail Now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String Number= "*121"+Uri.encode("#");
                        String number_to_call = "tel:"+ Number;
                        Intent callIntent = null;
                        callIntent = new Intent((Intent.ACTION_CALL));
                        callIntent.setData(Uri.parse(number_to_call));

                        callIntent.setData(Uri.parse(number_to_call));
                        if (ActivityCompat.checkSelfPermission(AirtelTariffActivity.this, Manifest.permission.CALL_PHONE) !=
                                PackageManager.PERMISSION_GRANTED) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(AirtelTariffActivity.this, Manifest.permission.CALL_PHONE)) {
                                startActivity(callIntent);

                            } else {
                                ActivityCompat.requestPermissions(AirtelTariffActivity.this,
                                        new String[]{Manifest.permission.CALL_PHONE}, MAKE_CALL_PERMISSION_REQUEST_CODE);
                                startActivity(callIntent);
                            }
                        }
                        startActivity(callIntent);
                    }
                });


                alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog TarifAlert=alertBuilder.create();
                TarifAlert.show();

                break;

                    default:

                        break;

        }
    }
}

package com.manager.credit.creditmanagerpro;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;

import android.os.Vibrator;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button prepaid,postpaid,lottery,statistics;
    String name;
    TextView Hello,Username;
    AlertDialog.Builder builder;
    String seen;
    int color;

    private final String TAG = MainActivity.class.getSimpleName();
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize the Audience Network SDK

        AudienceNetworkAds.initialize(this);
        setContentView(R.layout.activity_main);

        // Instantiate an InterstitialAd object.
        // NOTE: the placement ID will eventually identify this as your App, you can ignore it for
        // now, while you are testing and replace it later when you have signed up.
        // While you are using this temporary code you will only get test ads and if you release
        // your code like this to the Google Play your users will not receive ads (you will get a no fill error).
        interstitialAd = new InterstitialAd(this, "2468977193151832_2469018939814324");
        // Set listeners for the Interstitial Ad
        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        });

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd();






        SharedPreferences UsernamePref= getSharedPreferences("Username", Context.MODE_PRIVATE);
        final SharedPreferences.Editor UsernameEditor=UsernamePref.edit();
        final Typeface mytypeface3= Typeface.createFromAsset(getAssets(),"fonts/PoppinsRegular.otf");
        seen=UsernamePref.getString("Seen","");

        if (seen.equals("")){
            View dialog2= LayoutInflater.from(getBaseContext()).inflate(R.layout.dialog2,null);
            final AlertDialog.Builder alertDialog= new AlertDialog.Builder(MainActivity.this);
            alertDialog.setView(dialog2)
                    .create()
                    .show();
            Button vibrate=(Button)dialog2.findViewById(R.id.bt_dialog_next2);
            final Button cancel=(Button)dialog2.findViewById(R.id.bt_dialog_next3);
            TextView textView=(TextView)dialog2.findViewById(R.id.tv_dialog2);
            TextView textView2=(TextView)dialog2.findViewById(R.id.tip2);
            TextView textView1=(TextView)dialog2.findViewById(R.id.tip1);
            textView.setTypeface(mytypeface3);
            textView1.setTypeface(mytypeface3);
            textView2.setTypeface(mytypeface3);

            vibrate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    long[] pattern = { 0, 1000, 500, 100, 500, 100, 500, 1000};
                    vibrator.vibrate(pattern , -1);
                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    color++;
                    if (color>=1){
                        cancel.setTextColor(getResources().getColor(R.color.colorPrimary));
                    }
                    UsernameEditor.putString("Seen","Seen");
                    UsernameEditor.apply();


                    alertDialog.create()
                            .cancel();

                }
            });

        }





        prepaid=(Button)findViewById(R.id.prepaid_button);
        postpaid=(Button)findViewById(R.id.postpaid_button);
       // statistics=(Button)findViewById(R.id.stats_button);
        Hello=(TextView)findViewById(R.id.textView2);
        Username=(TextView)findViewById(R.id.textView3);
        postpaid.setOnClickListener(this);
        prepaid.setOnClickListener(this);
//        statistics.setOnClickListener(this);
        Typeface mytypeface= Typeface.createFromAsset(getAssets(),"fonts/GothamBook.otf");
        Typeface mytypeface2= Typeface.createFromAsset(getAssets(),"fonts/GothamBold.ttf");
        prepaid.setTypeface(mytypeface);
        postpaid.setTypeface(mytypeface);
       // statistics.setTypeface(mytypeface);
        Hello.setTypeface(mytypeface2);
        Username.setTypeface(mytypeface2);
        Username.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                UsernameEditor.putInt("hasLoggedIn",0);
                UsernameEditor.apply();
                Intent intent= new Intent(MainActivity.this,FirstActivity.class);
                startActivity(intent);
                return false;
            }
        });


        name=UsernamePref.getString("Username","");
        Username.setText(name);

        UsernameEditor.putInt("hasLoggedIn",1);
        UsernameEditor.putString("Login","Login");
        UsernameEditor.apply();

        TranslateAnimation animation= new TranslateAnimation(50.0f,0.0f,0.0f,0.0f);
        animation.setDuration(700);
        animation.setStartOffset(150);
        animation.setRepeatCount(0);
        animation.setFillAfter(false);

        TranslateAnimation animation2= new TranslateAnimation(80.0f,0.0f,0.0f,0.0f);
        animation2.setDuration(700);
        animation2.setStartOffset(150);
        animation2.setRepeatCount(0);
        animation2.setFillAfter(false);

        TranslateAnimation animation3= new TranslateAnimation(100.0f,0.0f,0.0f,0.0f);
        animation3.setDuration(700);
        animation3.setStartOffset(150);
        animation3.setRepeatCount(0);
        animation3.setFillAfter(false);

        TranslateAnimation animation4= new TranslateAnimation(110.0f,0.0f,0.0f,0.0f);
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

        Hello.startAnimation(animation);
        Username.startAnimation(animation2);
        prepaid.startAnimation(animation3);
        postpaid.startAnimation(animation4);
       // statistics.startAnimation(animation5);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.prepaid_button:

                Intent intent= new Intent(MainActivity.this,PrepaidCredit.class);
                startActivity(intent);
                break;

            case R.id.postpaid_button:

                Intent intent1= new Intent(MainActivity.this,PostpaidCredit.class);
                startActivity(intent1);
                break;

//            case R.id.stats_button:
//                Intent intent2= new Intent(MainActivity.this,NewStatistics.class);
//                startActivity(intent2);
//                break;
        }
    }

    @Override
    public void onBackPressed() {

            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        dialog.dismiss();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Exit Application?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}

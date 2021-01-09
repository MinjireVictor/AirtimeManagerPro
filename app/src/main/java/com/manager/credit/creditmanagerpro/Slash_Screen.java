package com.manager.credit.creditmanagerpro;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;



public class Slash_Screen extends AppCompatActivity {
    TextView TvIntro;
    private final int SPLASH_DISPLAY_LENGTH = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //initialize the audience network sdk
        AudienceNetworkAds.initialize(this);
        setContentView(R.layout.activity_slash__screen);
        
        TvIntro=(TextView)findViewById(R.id.intro);
        Typeface mytypeface2= Typeface.createFromAsset(getAssets(),"fonts/PoppinsBlack.otf");
        TvIntro.setTypeface(mytypeface2);
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(400);
        AnimationSet animation = new AnimationSet(false); //change to false
        animation.addAnimation(fadeIn);
        TvIntro.setAnimation(fadeIn);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Slash_Screen.this,WalkThroughActivity.class);
                Slash_Screen.this.startActivity(mainIntent);
                Slash_Screen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

package com.manager.credit.creditmanagerpro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.startapp.android.publish.adsCommon.StartAppAd;

public class PrepaidCredit extends AppCompatActivity implements View.OnClickListener{
    Button SubmitButton;
    TextView TvCreditLimit, one, two, three, four, five, six , seven, eight, nine, zero, del;
    private EditText CreditEntered;
    private AdView mAdView;

    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepaid_credit);
        initializeView();
        // for admob intersitials
        mInterstitialAd = new InterstitialAd(this);
        // this is a test unit ID, to be replaced by production ad id

       // mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdUnitId("ca-app-pub-1383320353699684/6557879730");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        //  For admob banner test ads
        //  MobileAds.initialize(this,"3940256099942544~3347511713");
        // this is for the real banner ad
        MobileAds.initialize(this,"ca-app-pub-1383320353699684/4815205612");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                           }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        SubmitButton=(Button)findViewById(R.id.credit_submit_button);
        SubmitButton.setOnClickListener(this);
        one=(TextView)findViewById(R.id.t9_key_1);
        two=(TextView)findViewById(R.id.t9_key_2);
        three=(TextView)findViewById(R.id.t9_key_3);
        four=(TextView)findViewById(R.id.t9_key_4);
        five=(TextView)findViewById(R.id.t9_key_5);
        six=(TextView)findViewById(R.id.t9_key_6);
        seven=(TextView)findViewById(R.id.t9_key_7);
        eight=(TextView)findViewById(R.id.t9_key_8);
        nine=(TextView)findViewById(R.id.t9_key_9);
        zero=(TextView)findViewById(R.id.t9_key_0);
        del=(TextView)findViewById(R.id.t9_key_backspace);
        TvCreditLimit=(TextView)findViewById(R.id.textView4);


        Typeface mytypeface= Typeface.createFromAsset(getAssets(),"fonts/GothamBook.otf");
        SubmitButton.setTypeface(mytypeface);
        TvCreditLimit.setTypeface(mytypeface);
        CreditEntered=(EditText)findViewById(R.id.password_field);
        CreditEntered.setTypeface(mytypeface);
        one.setTypeface(mytypeface);
        three.setTypeface(mytypeface);
        two.setTypeface(mytypeface);
        five.setTypeface(mytypeface);
        four.setTypeface(mytypeface);
        six.setTypeface(mytypeface);
        seven.setTypeface(mytypeface);
        eight.setTypeface(mytypeface);
        nine.setTypeface(mytypeface);
        zero.setTypeface(mytypeface);
        del.setTypeface(mytypeface);



    }

    private void initializeView(){
        CreditEntered=(EditText)findViewById(R.id.password_field);
        int[] idList= new int[]{R.id.t9_key_0, R.id.t9_key_1, R.id.t9_key_2,
                R.id.t9_key_3, R.id.t9_key_4, R.id.t9_key_5, R.id.t9_key_6,
                R.id.t9_key_7, R.id.t9_key_8, R.id.t9_key_9, R.id.t9_key_backspace,
        };

        for (int d: idList){
            View v=(View)findViewById(d);
            v.setOnClickListener(this);
        }

    }

    public  void display(String val){
        CreditEntered.append(val);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.t9_key_0:
                display("0");
                break;
            case R.id.t9_key_1:
                display("1");
                break;
            case R.id.t9_key_2:
                display("2");
                break;
            case R.id.t9_key_3:
                display("3");
                break;
            case R.id.t9_key_4:
                display("4");
                break;
            case R.id.t9_key_5:
                display("5");
                break;
            case R.id.t9_key_6:
                display("6");
                break;
            case R.id.t9_key_7:
                display("7");
                break;
            case R.id.t9_key_8:
                display("8");
                break;
            case R.id.t9_key_9:
                display("9");
                break;
            case R.id.t9_key_backspace:

                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                if (CreditEntered.getText().toString().isEmpty()){
                    Toast.makeText(PrepaidCredit.this, "Enter a value", Toast.LENGTH_SHORT).show();

                }
                else if (CreditEntered.getText().toString().length()>=1){
                    String newString=CreditEntered.getText().toString().substring(0,CreditEntered.getText().toString().length()-1);
                    CreditEntered.setText(newString);
                }


                break;

            case R.id.credit_submit_button:
                if (CreditEntered.getText().toString().isEmpty()){
                    Toast.makeText(PrepaidCredit.this, "Enter Maximum credit limit to proceed", Toast.LENGTH_SHORT).show();
                    CreditEntered.setError("Enter Maximum credit limit to proceed");
                }else if (CreditEntered.getText().toString().length()>4){
                    Toast.makeText(PrepaidCredit.this, "Limit too big", Toast.LENGTH_SHORT).show();
                    CreditEntered.setError("Limit too big");
                }else if (CreditEntered.getText().toString().equals("0")|| CreditEntered.getText().toString().equals("00")
                        ||CreditEntered.getText().toString().equals("000")||CreditEntered.getText().toString().equals("0000")||
                        CreditEntered.getText().toString().equals("00000")){
                    Toast.makeText(PrepaidCredit.this, "Erroneous value", Toast.LENGTH_SHORT).show();
                    CreditEntered.setError("Erroneous value");
                }else if (Integer.parseInt(CreditEntered.getText().toString())< 4){
                    Toast.makeText(this, "Minimum value is 5", Toast.LENGTH_SHORT).show();
                    CreditEntered.setError("Minimum value is 5");
              }
                else{
                    int Credit=Integer.parseInt(CreditEntered.getText().toString());
                    SharedPreferences creditSharedpref= getSharedPreferences("CreditLimit", Context.MODE_PRIVATE);
                    SharedPreferences.Editor creditEditor= creditSharedpref.edit();
                    creditEditor.putInt("CreditValue", Credit);
                    creditEditor.apply();

                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        Toast.makeText(this, "the intersitial wasnt loaded yet", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent=new Intent(PrepaidCredit.this, Service_Provider.class);
                    startActivity(intent);
                }
              break;

        }


    }

}

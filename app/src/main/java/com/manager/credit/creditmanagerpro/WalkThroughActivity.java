package com.manager.credit.creditmanagerpro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WalkThroughActivity extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotsLayout;
    private SliderAdapter sliderAdapter;
    private TextView[]mDots;
    private Button Next,Back;
    private int mCurrentPage;
    SharedPreferences OnceSharedPreference;
    int seen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_walk_through);
        Typeface mytypeface2= Typeface.createFromAsset(getAssets(),"fonts/GothamBold.ttf");

        OnceSharedPreference=getSharedPreferences("OnceSharedPreference", Context.MODE_PRIVATE);
        seen=OnceSharedPreference.getInt("Seen",0);
         if (seen!=0){
            Intent intent = new Intent(WalkThroughActivity.this, FirstActivity.class);
            startActivity(intent);
        }
        mDotsLayout=(LinearLayout)findViewById(R.id.dotsLayout);
        mSlideViewPager=(ViewPager) findViewById(R.id.slideViewPager);
        Next=(Button) findViewById(R.id.nextBtn);
        Next.setTextColor(getResources().getColor(R.color.colorWhite));
        Back=(Button) findViewById(R.id.backBtn);
        Back.setTextColor(getResources().getColor(R.color.colorWhite));
        Back.setTypeface(mytypeface2);
        Next.setTypeface(mytypeface2);
        addDotsIndicator(0);

        sliderAdapter= new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        mSlideViewPager.addOnPageChangeListener(viewListener);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCurrentPage==mDots.length-1){
                    SharedPreferences.Editor OnceEditor=OnceSharedPreference.edit();
                    OnceEditor.putInt("Seen",1);
                    String str= new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
                    OnceEditor.putString("StartTime",str);
                    OnceEditor.apply();
                    Intent intent = new Intent(WalkThroughActivity.this, FirstActivity.class);
                    startActivity(intent);

                }else {
                    mSlideViewPager.setCurrentItem(mCurrentPage+1);
                    }
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage-1);
            }
        });
    }
    public void addDotsIndicator(int position){

        mDots=new TextView[3];
        mDotsLayout.removeAllViews();
        for (int i=0;i< mDots.length; i++){
            mDots[i]= new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotsLayout.addView(mDots[i]);
        }
        if (mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }
    ViewPager.OnPageChangeListener viewListener= new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage=position;
            if (position==0){
                Next.setEnabled(true);
                Back.setEnabled(false);
                Back.setVisibility(View.INVISIBLE);
                Next.setText("Next");
                Back.setText("");
            }else if (position== mDots.length-1){
                Next.setEnabled(true);
                Back.setEnabled(true);
                Back.setVisibility(View.VISIBLE);
                Next.setText("Finish");
                Back.setText("Back");

            }else{
                Next.setEnabled(true);
                Back.setEnabled(true);
                Back.setVisibility(View.VISIBLE);
                Next.setText("Next");
                Back.setText("Back");
            }
        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}

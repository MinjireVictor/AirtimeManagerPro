package com.manager.credit.creditmanagerpro;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter (Context context){
        this.context= context;
    }

    public int [] slide_images = {
            R.drawable.dog,
            R.drawable.gamepad,
            R.drawable.money1,
           // R.drawable.money,


    };
    public  String [] slide_headings= {
            "WELCOME",
            "PREPAID & POSTPAID",
            "SAVE"
            //"DONATE"
    };

    public String [] slide_deescriptions= {
            " Welcome to Airtime Manager Pro the app that gives you more control of your airtime and minutes",
            " With prepaid option you can enter the amount of airtime you want to spend on your call and with the post" +
                    "paid option you can set the amount of time you want to spend on your call",
            "Save some of your airtime and by controlling how you use it "
            //"Donate to the cause and help us make this app better"

    };


    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
       layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE );
       View view=layoutInflater.inflate(R.layout.slide_layout,container,false);
        Typeface mytypeface2= Typeface.createFromAsset(context.getAssets(),"fonts/GothamBold.ttf");
        Typeface mytypeface= Typeface.createFromAsset(context.getAssets(),"fonts/GothamBook.otf");


        ImageView slideImageView=(ImageView)view.findViewById(R.id.slide_image);
        TextView slideHeading=(TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription=(TextView) view.findViewById(R.id.slide_description);
        slideDescription.setTypeface(mytypeface);
        slideHeading.setTypeface(mytypeface2);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_deescriptions[position]);

        container.addView(view);
        return view;




    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout)object);
    }
}

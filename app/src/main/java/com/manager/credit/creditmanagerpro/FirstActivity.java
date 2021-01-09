package com.manager.credit.creditmanagerpro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    TextView Tv1,Tv2;
    Button Login_button;
    EditText EtUsername;
    String Name;
    String Login;

    int logged_in;
    SharedPreferences OnceSharedPreference;
    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        OnceSharedPreference=getSharedPreferences("OnceSharedPreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor OnceEditor=OnceSharedPreference.edit();
        OnceEditor.putInt("Seen",1);
        OnceEditor.apply();

        SharedPreferences UsernamePref= getSharedPreferences("Username", Context.MODE_PRIVATE);
       // logged_in=UsernamePref.getInt("hasLoggedIn",0);
        Login=UsernamePref.getString("Login","");
//        if (logged_in==1){
//            Intent intent= new Intent(FirstActivity.this,MainActivity.class);
//            startActivity(intent);
//        }


        Tv1=(TextView)findViewById(R.id.textView5);
        Tv2=(TextView)findViewById(R.id.textView6);
        Login_button=(Button)findViewById(R.id.Login_button);
        EtUsername=(EditText)findViewById(R.id.EtUsername);

        Typeface myTypeface= Typeface.createFromAsset(getAssets(),"fonts/GothamBook.otf");
        Typeface myTypeface2= Typeface.createFromAsset(getAssets(),"fonts/GothamBold.ttf");
        Login_button.setTypeface(myTypeface2);
        Tv1.setTypeface(myTypeface);
        Tv2.setTypeface(myTypeface2);
        EtUsername.setTypeface(myTypeface2);

        if (Login.equals("Login")){
            Intent intent= new Intent(FirstActivity.this,MainActivity.class);
            startActivity(intent);

        } else{
            Login_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (EtUsername.getText().toString().length()<1){
                        Toast.makeText(FirstActivity.this, "Enter Username", Toast.LENGTH_SHORT).show();
                    }else{
                        Name=EtUsername.getText().toString();
                        SharedPreferences UsernamePref= getSharedPreferences("Username", Context.MODE_PRIVATE);
                        SharedPreferences.Editor UsernameEditor=UsernamePref.edit();
                        UsernameEditor.putString("Username",Name);

                        // UsernameEditor.putInt("hasLoggedIn",1);
                        // logged_in=UsernamePref.getInt("hasLoggedIn",0);
                        UsernameEditor.apply();
                        Intent intent= new Intent(FirstActivity.this, MainActivity.class);
                        startActivity(intent);
                        FirstActivity.this.finish();
                    }
                }
            });

        }


    }

  }

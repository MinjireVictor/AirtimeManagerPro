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

import com.startapp.android.publish.adsCommon.StartAppAd;

public class PostpaidCredit extends AppCompatActivity implements View.OnClickListener{

    Button SubmitButton;
    TextView TvCreditLimit, one, two, three, four, five, six , seven, eight, nine, zero, del;
    private EditText CreditEntered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postpaid_credit);
        initializeView();

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
                if (CreditEntered.getText().toString().isEmpty()){
                    Toast.makeText(PostpaidCredit.this, "Enter a value", Toast.LENGTH_SHORT).show();

                }
                else if (CreditEntered.getText().toString().length()>=1){
                    String newString=CreditEntered.getText().toString().substring(0,CreditEntered.getText().toString().length()-1);
                    CreditEntered.setText(newString);
                }


                break;

            case R.id.credit_submit_button:
                if (CreditEntered.getText().toString().isEmpty()){
                    Toast.makeText(PostpaidCredit.this, "Enter Maximum credit limit to proceed", Toast.LENGTH_SHORT).show();
                }else if (CreditEntered.getText().toString().length()>4){
                    Toast.makeText(PostpaidCredit.this, "Limit to big", Toast.LENGTH_SHORT).show();
                }else if (CreditEntered.getText().toString().equals("0")|| CreditEntered.getText().toString().equals("00")
                        ||CreditEntered.getText().toString().equals("000")||CreditEntered.getText().toString().equals("0000")||
                        CreditEntered.getText().toString().equals("00000")){
                    Toast.makeText(PostpaidCredit.this, "Erroneous value", Toast.LENGTH_SHORT).show();
                }
                else{
                    int Credit=Integer.parseInt(CreditEntered.getText().toString());

                    SharedPreferences timeSharedpref= getSharedPreferences("TimeLimit", Context.MODE_PRIVATE);
                    SharedPreferences.Editor creditEditor= timeSharedpref.edit();
                    creditEditor.putInt("TimeValue", Credit);
                    creditEditor.apply();

                    Intent intent=new Intent(PostpaidCredit.this, PostPaid_Dailer.class);
                    startActivity(intent);

                    StartAppAd.showAd(this);
                }
                break;

        }


    }
}


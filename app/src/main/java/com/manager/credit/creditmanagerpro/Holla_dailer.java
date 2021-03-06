package com.manager.credit.creditmanagerpro;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.startapp.android.publish.adsCommon.StartAppAd;

import java.util.Calendar;

public class Holla_dailer extends AppCompatActivity  implements View.OnClickListener{

    TextView one, two, three, four, five, six, seven, eight, nine, zero, hash, star;
    ImageView CallButton;
    private EditText NumberScreen;
    ImageView ContactsImageView, IvDelete;
    private static  final int MAKE_CALL_PERMISSION_REQUEST_CODE=1;
    AlarmManager alarmManager;
    Context context;
    Calendar calendar;
    PendingIntent pendingIntent;
    float Credit;
    int Credit_received, count=0, alarmWakeUp=10*1000,value;
    String inputNumber;
    long CreditMills,elaspseTime;
    int hour, called = 0,tariff;
    SharedPreferences creditSharedpref;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holla_dailer);
        this.context= this;
        initializeView();
        creditSharedpref= getSharedPreferences("CreditLimit", Context.MODE_PRIVATE);
        Credit_received=(creditSharedpref.getInt("CreditValue",Credit_received));
        Intent call_Intent = new Intent(this.context, NewAlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(Holla_dailer.this, 0, call_Intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        Typeface mytypeface = Typeface.createFromAsset(getAssets(), "fonts/AvenirRoman.otf");
        Typeface mytypeface2 = Typeface.createFromAsset(getAssets(), "fonts/GothamBold.ttf");
        ContactsImageView = (ImageView) findViewById(R.id.contactsImageView);

        one = (TextView) findViewById(R.id.tv1);
        IvDelete=(ImageView)findViewById(R.id.deleteImageView);
        two = (TextView) findViewById(R.id.tv2);
        three = (TextView) findViewById(R.id.tv3);
        four = (TextView) findViewById(R.id.tv4);
        five = (TextView) findViewById(R.id.tv5);
        six = (TextView) findViewById(R.id.tv6);
        seven = (TextView) findViewById(R.id.tv7);
        eight = (TextView) findViewById(R.id.tv8);
        nine = (TextView) findViewById(R.id.tv9);
        zero = (TextView) findViewById(R.id.tv0);
        hash = (TextView) findViewById(R.id.TvStar);
        star = (TextView) findViewById(R.id.tvHash);

        CallButton = (ImageView) findViewById(R.id.call_ImageView);
        NumberScreen = (EditText) findViewById(R.id.screen);
        alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
        one.setTypeface(mytypeface);
        two.setTypeface(mytypeface);
        three.setTypeface(mytypeface);
        four.setTypeface(mytypeface);
        five.setTypeface(mytypeface);
        six.setTypeface(mytypeface);
        seven.setTypeface(mytypeface);
        eight.setTypeface(mytypeface);
        nine.setTypeface(mytypeface);
        zero.setTypeface(mytypeface);
        star.setTypeface(mytypeface);
        hash.setTypeface(mytypeface);
        NumberScreen.setTypeface(mytypeface);
        ContactsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // user BoD suggests using Intent.ACTION_PICK instead of .ACTION_GET_CONTENT to avoid the chooser
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                // BoD con't: CONTENT_TYPE instead of CONTENT_ITEM_TYPE
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(intent, 1);

            }
        });
        IvDelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                NumberScreen.setText("");
                return false;
            }
        });
        NumberScreen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputNumber=editable.toString();
                if      (inputNumber.contains("077")||inputNumber.contains("0770")||inputNumber.contains("0771")||inputNumber.contains("0772")||inputNumber.contains("0773")
                        ||inputNumber.contains("0774")||inputNumber.contains("0775")||inputNumber.contains("0776")
                        ||inputNumber.contains("0777")||inputNumber.contains("0778")||inputNumber.contains("0779")
                        ||inputNumber.contains("25777")
                        ||inputNumber.contains("254770")||inputNumber.contains("254771")||inputNumber.contains("254772")||inputNumber.contains("254773")||inputNumber.contains("254774")
                        ||inputNumber.contains("254775")||inputNumber.contains("254776")||inputNumber.contains("254777")||inputNumber.contains("254778")
                        ||inputNumber.contains("254779"))
                {

                    if (count<=0){

                        final AlertDialog.Builder builder=new AlertDialog.Builder(Holla_dailer.this);
                        builder.setTitle("This is a Telkom number");
                        builder.setMessage( "Telkom do not incur any charges on this tariff," + " the credit value entered will be converted" +
                                " to minutes."+
                                " Do you wish to proceed");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Credit= (float) (((float)Credit_received)*60);
                                CreditMills= (long) (Credit*1000);
                                Toast.makeText(context, "Credit changed to "+Credit+" minutes", Toast.LENGTH_SHORT).show();
                                AlertDialog dialog=builder.create();
                                dialog.dismiss();
                                count++;
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent= new Intent(Holla_dailer.this,PrepaidCredit.class);
                                startActivity(intent);

                            }
                        });
                        AlertDialog dialog=builder.create();
                        dialog.show();

                    }
                }else {
                    tariff=1;
                    Credit= (float) (((float)Credit_received/3.3)*60);
                    CreditMills= (long) (Credit*1000);

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            Uri uri = data.getData();

            if (uri != null) {
                Cursor c = null;
                try {
                    c = getContentResolver().query(uri, new String[]{
                                    ContactsContract.CommonDataKinds.Phone.NUMBER,
                                    ContactsContract.CommonDataKinds.Phone.TYPE },
                            null, null, null);

                    if (c != null && c.moveToFirst()) {
                        String number = c.getString(0);
                        int type = c.getInt(1);
                        showSelectedNumber(type, number);
                    }
                } finally {
                    if (c != null) {
                        c.close();
                    }
                }
            }
        }
    }
    public void showSelectedNumber(int type, String number) {
        Toast.makeText(this, type + ": ", Toast.LENGTH_LONG).show();
        NumberScreen.setText(number);
    }


    private void initializeView() {
        NumberScreen = (EditText) findViewById(R.id.screen);
        int[] idList = new int[]{R.id.tv0, R.id.tv1, R.id.tv2,
                R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6,
                R.id.tv7, R.id.tv8, R.id.tv9, R.id.tvHash,
                R.id.TvStar, R.id.deleteImageView,R.id.call_ImageView

        };

        for (int d : idList) {
            View v = (View) findViewById(d);
            v.setOnClickListener(this);
        }

    }

    public void display(String val) {
        NumberScreen.append(val);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv0:
                display("0");
                break;
            case R.id.tv1:
                display("1");
                break;
            case R.id.tv2:
                display("2");
                break;
            case R.id.tv3:
                display("3");
                break;
            case R.id.tv4:
                display("4");
                break;
            case R.id.tv5:
                display("5");
                break;
            case R.id.tv6:
                display("6");
                break;
            case R.id.tv7:
                display("7");
                break;
            case R.id.tv8:
                display("8");
                break;
            case R.id.tv9:
                display("9");
                break;
            case R.id.tvHash:
                display("#");
                break;
            case R.id.TvStar:
                display("*");
                break;
            case R.id.deleteImageView:
                if (NumberScreen.getText().toString().isEmpty()) {
                    Toast.makeText(Holla_dailer.this, "Enter a number", Toast.LENGTH_SHORT).show();

                } else if (NumberScreen.getText().toString().length() >= 1) {
                    String newString = NumberScreen.getText().toString().substring(0, NumberScreen.getText().toString().length() - 1);
                    NumberScreen.setText(newString);
                }


                break;
            case R.id.call_ImageView:
                if (NumberScreen.getText().toString().isEmpty()) {
                    Toast.makeText(Holla_dailer.this, "Enter a number", Toast.LENGTH_SHORT).show();
                    NumberScreen.setError("Field cannot be empty");
                } else {
                    called++;
                    String Number = NumberScreen.getText().toString();
                    String number_to_call = "tel:" + Number;
                    Intent callIntent = null;
                    callIntent = new Intent((Intent.ACTION_CALL));
                    callIntent.setData(Uri.parse(number_to_call));

                    callIntent.setData(Uri.parse(number_to_call));
                    if (ActivityCompat.checkSelfPermission(Holla_dailer.this, Manifest.permission.CALL_PHONE) !=
                            PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(Holla_dailer.this, Manifest.permission.CALL_PHONE)) {
                            startActivity(callIntent);

                        } else {
                            ActivityCompat.requestPermissions(Holla_dailer.this,
                                    new String[]{Manifest.permission.CALL_PHONE}, MAKE_CALL_PERMISSION_REQUEST_CODE);
                            startActivity(callIntent);
                        }
                    }
                    startActivity(callIntent);
                    calendar = Calendar.getInstance();
                    elaspseTime=SystemClock.elapsedRealtime();
                    SharedPreferences sharedPreference= getSharedPreferences("TestTime",Context.MODE_PRIVATE);
                    SharedPreferences.Editor TimeEditor=sharedPreference.edit();
                    TimeEditor.putLong("Test_time",elaspseTime);
                    TimeEditor.putLong("TimeTest",CreditMills);
                    TimeEditor.putFloat("TimeToEnd",Credit);
                    TimeEditor.apply();

                    alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, (long) (SystemClock.elapsedRealtime() + alarmWakeUp), pendingIntent);
                }
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Holla_dailer.this,MainActivity.class);
        startActivity(intent);

    }

}

package com.manager.credit.creditmanagerpro;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.os.Vibrator;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class NewPostpaid_Receiver extends BroadcastReceiver {

    TelephonyManager telephonyManager;
    PhoneStateListener listener;
    long TimeBefore,TimeNow;
    Float TimeToEnd;
    int time_difference,nextTime;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    int CreditMills=10*1000,call_details;

    @Override
    public void onReceive(Context context, Intent intent) {

        TimeNow = SystemClock.elapsedRealtime();
        SharedPreferences sharedPreference = context.getSharedPreferences("TestTime", Context.MODE_PRIVATE);
        TimeBefore = sharedPreference.getLong("Test_time", 0);
        TimeToEnd = sharedPreference.getFloat("TimeToEnd", 0);
        time_difference = (int) ((TimeNow - TimeBefore) / 1000);

        nextTime = (int) (TimeToEnd - time_difference);

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent call_Intent = new Intent(context.getApplicationContext(), NewPostpaid_Receiver.class);
        pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 0, call_Intent, PendingIntent.FLAG_UPDATE_CURRENT);


        if (nextTime >= 10) {

            CallState(context);

        } else if (nextTime < 10 && nextTime > 0) {
            // add a vibrate here
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            long[] pattern = {0, 1000, 500, 100, 500, 100, 500, 1000};
            vibrator.vibrate(pattern, -1);

            call_details++;
            Intent call_intent2 = new Intent(context.getApplicationContext(), EndCallService.class);
            PendingIntent pendingIntent2 = PendingIntent.getBroadcast(context.getApplicationContext(), 0, call_intent2, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, (long) (SystemClock.elapsedRealtime() + nextTime + 10000), pendingIntent2);
        }
    }

        void CallState(final Context context){
            // Get the telephony manager
            telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            // Create a new PhoneStateListener
            listener = new PhoneStateListener() {
                @Override
                public void onCallStateChanged(int state, String incomingNumber) {
                    String stateString = "N/A";
                    switch (state) {
                        case TelephonyManager.CALL_STATE_IDLE:
                            stateString = "Idle";
                            break;
                        case TelephonyManager.CALL_STATE_OFFHOOK:
                            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, (long) (SystemClock.elapsedRealtime() + CreditMills), pendingIntent);
                            stateString = "Off Hook";
                            break;
                        case TelephonyManager.CALL_STATE_RINGING:
                            stateString = "Ringing";
                            break;
                    }
                  //   Toast.makeText(context, ""+stateString, Toast.LENGTH_SHORT).show();
                }
            };

            // Register the listener with the telephony manager
            telephonyManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
        }

}

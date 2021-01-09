package com.manager.credit.creditmanagerpro;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.app.Service.START_NOT_STICKY;
import static android.content.Context.TELEPHONY_SERVICE;

public class EndCallService extends BroadcastReceiver{
    Context context;

   public  void endCall(Context context){
       this.context = context;
       TelephonyManager tm=(TelephonyManager)context.getSystemService(TELEPHONY_SERVICE);
        try {
            {
                Class c = Class.forName(tm.getClass().getName());
                Method m = c.getDeclaredMethod("getITelephony");
                m.setAccessible(true);
                Object telephonyService=m.invoke(tm);
                c=Class.forName(telephonyService.getClass().getName());
                m=c.getDeclaredMethod("endCall");
                m.setAccessible(true);
                m.invoke(telephonyService);




            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
   }

    @Override
    public void onReceive(Context context, Intent intent) {


        endCall(context);



    }
}

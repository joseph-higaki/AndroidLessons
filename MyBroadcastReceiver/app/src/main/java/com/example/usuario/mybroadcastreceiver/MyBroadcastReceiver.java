package com.example.usuario.mybroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by usuario on 4/25/2015.
 */
public class MyBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(getClass().getName(), "onReceive");
    }
}

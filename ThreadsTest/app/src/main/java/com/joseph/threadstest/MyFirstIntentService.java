package com.joseph.threadstest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Administrator on 25/04/2015.
 */
public class MyFirstIntentService extends IntentService {

    public MyFirstIntentService(String name){
        super(name);
    }

    public MyFirstIntentService(){
        super("MyFirstIntentService");
    }

    public void longTask(){
        int i = 10;
        while (i-- != 0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(this.getClass().getName(), "onHandleIntent");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(this.getClass().getName(), "onStartCommand");
        longTask();
        return super.onStartCommand(intent, flags, startId);
    }
}

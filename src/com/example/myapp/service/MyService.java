package com.example.myapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    boolean isRun = true;
    int count = 0;
    Timer timer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showToast();
        return START_STICKY;
    }

    private void showToast() {
        Toast.makeText(this, "Service Started" + count++, Toast.LENGTH_LONG).show();
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            showToast();
            //insert your methods here
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        isRun = false;
        Toast.makeText(this, "Service STOPppp" + count++, Toast.LENGTH_SHORT).show();
    }

}

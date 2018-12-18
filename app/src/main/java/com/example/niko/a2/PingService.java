package com.example.niko.a2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PingService extends Service {
    MyThread thr;
    TimeThread xhr;

    final String myLog = "trollo";

    public PingService() {
        thr = new MyThread();
        xhr = new TimeThread();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(myLog, "Create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(myLog, "onStartComandService");
        thr.run("http://google.com");

        //xhr.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(myLog, "onDestroyService");
        xhr.astanawis();
        super.onDestroy();
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}

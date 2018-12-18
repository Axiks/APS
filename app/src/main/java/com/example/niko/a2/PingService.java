package com.example.niko.a2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class PingService extends Service {
    MyThread thr;
    int li = 0;
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
        Log.d(myLog, "onStartComand");
        //runn("http://google.com");
        //thr.run("http://google.com");
        //code();



        xhr.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        Log.d(myLog, "onDestroyService");
        xhr.astanawis();
        //xhr.interrupt();
        super.onDestroy();
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void code(){
        li++;
        int tm = 2;
        String label = "code bulun "+li;
        Log.d(myLog, "onHandleIntent");

        try {
            TimeUnit.SECONDS.sleep(tm);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(myLog, "onHandleIntent end " + label);

        code();
    }

//    private void startInForeground() {
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notificationIntent,0);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID)
//                .setSmallIcon(R.drawable.shsl_notification)
//                .setContentTitle("TEST")
//                .setContentText("HELLO")
//                .setTicker("TICKER")
//                .setContentIntent(pendingIntent);
//        Notification notification=builder.build();
//        if(Build.VERSION.SDK_INT>=26) {
//            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
//            channel.setDescription(NOTIFICATION_CHANNEL_DESC);
//            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            notificationManager.createNotificationChannel(channel);
//        }
//        startForeground(NOTIFICATION_ID, notification);
//    }

}

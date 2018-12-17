package com.example.niko.a2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PingService extends Service {
    MyThread thr;
    final String myLog = "trollo";

    public PingService() {
        thr = new MyThread();
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
        thr.run("http://google.com");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(myLog, "onDestroyService");
        super.onDestroy();
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public String runn(String inUrl) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Log.d("trollo", "Mой поток2 запущен...");

        try {
            //URL url = new URL("http://googlebdbdb345.com");
            URL url = new URL(inUrl);

            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setRequestProperty("User-Agent", "Android Application: 10");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(1000 * 3); // mTimeout is in seconds
            urlc.connect();

            if (urlc.getResponseCode() == 200) {
                Log.d("trollo","getResponseCode == 200");
                return "getResponseCode == 200";
                //return new Boolean(true);
            }
            else {
                Log.d("trollo","error conn");
                return("error connect");
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            Log.d("trollo","exep1");
            return "exep1";
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("trollo","exep2");
            return "exep2";
        }
    }

}

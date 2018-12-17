package com.example.niko.a2;

import android.os.StrictMode;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*Створюю новий поток*/
class MyThread extends Thread {
    public String run(String inUrl) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Log.d("trollo", "Mой поток запущен...");

        try {
            //URL url = new URL("http://googlebdbdb345.com");
            URL url = new URL(inUrl);

            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setRequestProperty("User-Agent", "Android Application: 10");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(1000 * 1); // mTimeout is in seconds
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

package com.example.niko.a2;
import android.util.Log;
import java.util.concurrent.TimeUnit;

/*Створюю новий поток*/
class TimeThread extends Thread {
    int li;
    boolean running;
    final String myLog = "trollo";

    TimeThread(){
        running = true;
        li = 0;
    }
    public void run() {
        if (!running) return;
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
        run();
    }

    public void astanawis(){
        running = false;
    }

}

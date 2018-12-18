package com.example.niko.a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    MainActivity activity = new MainActivity();
    /*Запускає код коли до нього задзвонять) */
    final String myLog = "trollo";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.d(myLog, "MyReceiver: Run BroadcastReceiver");

        Toast.makeText(context, "Action: " + intent.getAction(), Toast.LENGTH_SHORT).show();

        Intent i = new Intent(context, PingService.class);
        context.startService(i);

        //activity.servOn();
    }
}

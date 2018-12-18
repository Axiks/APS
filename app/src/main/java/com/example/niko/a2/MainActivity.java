package com.example.niko.a2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView tvRes;
    EditText inUrl;
    String res = "";
    public MyThread myThread;

    public MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myThread = new MyThread();
        myReceiver = new MyReceiver();
        start();
    }

    private void start(){
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        //myReceiver.onReceive();
        inUrl = (EditText) findViewById(R.id.utiPuti);
        inUrl.setText("http://google.com");
        tvRes = (TextView) findViewById(R.id.textVi);
        tvRes.setText("Поки що тут нічо :d");
    }

    public void onClick(View view) {
        render("Какая то не понятна кнопка натиснута");
        render(myThread.run(inUrl.getText().toString()));
    }

    public void servStart(View view){
        Intent intent = new Intent(this,  PingService.class);
        PendingIntent  pi = PendingIntent.getBroadcast(this.getApplicationContext(),1,intent, 0);

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (7*1000), pi);

        Toast.makeText(this, "5 second projshlo", Toast.LENGTH_LONG).show();

        //servOn();
    }

    public void servOn(){
        startService(new Intent(this, PingService.class));
//        startService(new Intent(this, PiniService.class).putExtra("time", 3) .putExtra("label", "Call 1") );
//        startService(new Intent(this, PiniService.class).putExtra("time", 5) .putExtra("label", "Call 2") );
//        startService(new Intent(this, PiniService.class).putExtra("time", 15) .putExtra("label", "Call 3") );
//        startService(new Intent(this, PiniService.class).putExtra("time", 4) .putExtra("label", "Call 4") );
    }

    public void destroy(View view){
        stopService(new Intent(this, PingService.class));
        //stopService(new Intent(this, PiniService.class));
    }

    public void render(String mess){
        Log.d("trollo", mess);
        tvRes = (TextView) findViewById(R.id.textVi);
        tvRes.setText(mess);
    }

}




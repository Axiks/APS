package com.example.niko.a2;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView tvRes;
    EditText inUrl;
    String res = "";
    public MyThread myThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myThread = new MyThread();
        start();
    }

    private void start(){
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

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
        startService(new Intent(this, PingService.class));
    }

    public void destroy(View view){
        stopService(new Intent(this, PingService.class));
    }

    public void render(String mess){
        Log.d("trollo", mess);
        tvRes = (TextView) findViewById(R.id.textVi);
        tvRes.setText(mess);
    }

}




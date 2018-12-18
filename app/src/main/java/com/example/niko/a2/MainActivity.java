package com.example.niko.a2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView tvRes;
    EditText inUrl;
    String res = "";
    public MyThread myThread;
    public MyReceiver myReceiver;
    public DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myThread = new MyThread();
        myReceiver = new MyReceiver();
        dbHelper = new DBHelper(this);
        start();
    }

    private void start(){
        //myReceiver.onReceive();
        inUrl = (EditText) findViewById(R.id.utiPuti);
        inUrl.setText("http://google.com");
        tvRes = (TextView) findViewById(R.id.textVi);
        tvRes.setText("Поки що тут нічо :d");
        //dbTest();
    }

    public void onClick(View view) {
        render("Какая то не понятна кнопка натиснута");
        render(myThread.run(inUrl.getText().toString()));
    }

    public void servStart(View view){
        servOn();
    }

    public void servOn(){
        startService(new Intent(this, PingService.class));
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

    public void dbTest(){
        /*Запис даних*/
        //render("DB Start )");

        String site = "google.com";
        String conn = "true";

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.KEY_SITE, site);
        contentValues.put(DBHelper.KEY_CONN, conn);

        database.insert(DBHelper.TABLE_PINGS, null, contentValues);

        /*Читання даних*/
        Cursor cursor = database.query(DBHelper.TABLE_PINGS, null,null,null,null,null,null);
        int c = cursor.getCount();
        //render(c + "");
    }

}




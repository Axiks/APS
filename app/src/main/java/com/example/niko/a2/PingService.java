package com.example.niko.a2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class PingService extends Service {
    MyThread thr;
    DBHelper dbHelper;
    final String myLog = "trollo";

    public PingService() {
        thr = new MyThread();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new DBHelper(this);
        Log.d(myLog, "Create Ping Service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(myLog, "PingService: onStartCommandService");
        String text = thr.run("http://google.com");
        Log.d(myLog, "PingService: text-- " + text);
        dbAdd(text);

        recUpdate();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(myLog, "PingService: onDestroyService");
        super.onDestroy();
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void recUpdate(){
        Log.d(myLog, "PingService: Run recUpdate");
        Intent intent = new Intent(this,  MyReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this.getApplicationContext(),1,intent, 0);

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (7*1000), pi);

        Toast.makeText(this, "Наставлено на 7 секунд", Toast.LENGTH_LONG).show();
    }

    public void dbAdd(String site){
        /*Запис даних*/
        //render("DB Start )");

        //String site = "google.com";
        String conn = "true";

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.KEY_SITE, site);
        contentValues.put(DBHelper.KEY_CONN, conn);

        database.insert(DBHelper.TABLE_PINGS, null, contentValues);

        /*Читання даних*/
        Cursor cursor = database.query(DBHelper.TABLE_PINGS, null,null,null,null,null,null);
        int c = cursor.getCount();

        Log.d(myLog, "Db count: " + c);
        //render(c + "");
    }

}

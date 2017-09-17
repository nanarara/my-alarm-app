package com.nara.alarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by nara.yoon on 2017-08-21.
 */

public class removeActivity extends Activity implements View.OnClickListener{
    private Intent intent;
    private PendingIntent servicePending;
    private AlarmManager alarmManager;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_layout);

        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
//        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.);

    }

    void removeAlarm(){
        String u = getIntent().getStringExtra("URI");
        Uri uri = Uri.parse(u);
        Log.d(MainActivity.TAG, getClass() + "uri : " + uri);
        MediaPlayer player = new MediaPlayer();

        try {
            player.setDataSource(getApplicationContext(), uri);
            player.stop();
        }catch (Exception e){

        }
        Intent intent = new Intent("AlarmReceiver");
        PendingIntent servicePending = PendingIntent.getBroadcast(removeActivity.this, 111, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(servicePending);
        Toast.makeText(getBaseContext(), "알람해제", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.removeAlarm:
                removeAlarm();
                break;

        }
    }
}

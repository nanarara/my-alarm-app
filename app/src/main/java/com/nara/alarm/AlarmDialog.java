package com.nara.alarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nara.word.NoteDbAdapter;

/**
 * Created by nara.yoon on 2017-08-22.
 */

public class AlarmDialog extends Activity implements View.OnClickListener {

    Button cancel;
    Button ok;

    String u;

    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;

    TextView mEnglishText;

    NoteDbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alarm_dialog);

        if (MainActivity.IS_TAG_ON)
            Log.d(MainActivity.TAG, getClass() + " onCreate");

        dbAdapter = new NoteDbAdapter(this);
        dbAdapter.open();
        Log.d(MainActivity.TAG, getClass() + " db fetch :" + dbAdapter.fetchNote(2).getString(1) + ", " + dbAdapter.fetchNote(2).getString(2));



        cancel = (Button) findViewById(R.id.cancel);
        ok = (Button) findViewById(R.id.ok);

        cancel.setOnClickListener(this);
        ok.setOnClickListener(this);

        u = getIntent().getStringExtra("URI");

        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        mEnglishText = (TextView) findViewById(R.id.eng_word);
        mEnglishText.setText(dbAdapter.fetchNote(2).getString(1));

        radioButton1.setOnClickListener(radioClickListener);
        radioButton2.setOnClickListener(radioClickListener);
        radioButton3.setOnClickListener(radioClickListener);

        radioButton1.setChecked(true);

        radioButton1.setText(dbAdapter.fetchNote(2).getString(2));
        radioButton2.setText(dbAdapter.fetchNote(3).getString(2));
        radioButton3.setText(dbAdapter.fetchNote(4).getString(2));

    }

    RadioButton.OnClickListener radioClickListener = new RadioButton.OnClickListener() {

        @Override
        public void onClick(View view) {
            Toast.makeText(AlarmDialog.this, "1: " + radioButton1.isChecked() + "\n"
                    + "2: " + radioButton2.isChecked() + "\n" + "3: " + radioButton3.isChecked(), Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if (!dialogBounds.contains((int) ev.getX(), (int) ev.getY())) {
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        if (MainActivity.IS_TAG_ON)
            Log.d(MainActivity.TAG, getClass() + " dismiss");

        Log.d(MainActivity.TAG, getClass() + "uri ok ? " + u);
        if (u != null) {
            Uri uri = Uri.parse(u);
            Log.d(MainActivity.TAG, getClass() + "uri : " + uri);
            MediaPlayer player = new MediaPlayer();

            try {
                player.release();

            } catch (Exception e) {
                Log.d(MainActivity.TAG, getClass() + "error : " + e);
            }

        }

        Intent intent = new Intent("AlarmReceiver");
        PendingIntent servicePending = PendingIntent.getBroadcast(getApplicationContext(), 111, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Toast.makeText(getApplicationContext(), "알람해제", Toast.LENGTH_SHORT).show();
        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(ALARM_SERVICE);
        alarmManager.cancel(servicePending);

        this.finish();
    }
}

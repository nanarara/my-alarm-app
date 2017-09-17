package com.nara.alarm;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.nara.word.ExcelToDBTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nara.yoon on 2017-08-21.
 */

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener {

    Calendar Time;

    private Intent intent;
    private PendingIntent servicePending;
    private AlarmManager alarmManager;

    Uri uri;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss초");

    TextView textView;

    DatePickerDialog.OnDateSetListener eDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            Time.set(Calendar.YEAR, year);
            Time.set(Calendar.MONTH, month);
            Time.set(Calendar.DAY_OF_MONTH, day);

            updateLabel();
        }
    };

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            Time.set(Calendar.HOUR_OF_DAY, hour);
            Time.set(Calendar.MINUTE, minute);
            Time.set(Calendar.SECOND, 0);

            updateLabel();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Time = Calendar.getInstance();

        findViewById(R.id.setAlarm).setOnClickListener(this);
        findViewById(R.id.removeAlarm).setOnClickListener(this);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.repeatAlarm).setOnClickListener(this);
        findViewById(R.id.ringtone).setOnClickListener(this);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        textView = (TextView) findViewById(R.id.textView);

        updateLabel();

    }

    private void updateLabel() {
        textView.setText(simpleDateFormat.format(Time.getTime()));
    }

    void setAlarm() {
        intent = new Intent("AlarmReceiver");
        if (uri != null)
            intent.putExtra("URI", uri.toString());
        servicePending = PendingIntent.getBroadcast(getBaseContext(), 111, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, Time.getTimeInMillis(), servicePending);

        Toast.makeText(getBaseContext(), "알람설정" + Time.getTime(), Toast.LENGTH_SHORT).show();

        Intent i = new Intent(AlarmActivity.this, MainActivity.class);
        i.putExtra("time", Time.getTime() + "");
        startActivity(i);

    }


    void removeAlarm() {
        intent = new Intent("AlarmReceiver");
        servicePending = PendingIntent.getBroadcast(AlarmActivity.this, 111, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Toast.makeText(getBaseContext(), "알람해제" + Time.getTime(), Toast.LENGTH_SHORT).show();

        alarmManager.cancel(servicePending);
    }

    void setRepeatAlarm() {
        intent = new Intent("AlarmReceiver");

        servicePending = PendingIntent.getBroadcast(AlarmActivity.this, 111, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Time.getTimeInMillis(), 20000, servicePending);

        Toast.makeText(getBaseContext(), "알람설정" + Time.getTime(), Toast.LENGTH_SHORT).show();
    }

    void setRingtone() {
        Uri currentTone = RingtoneManager.getActualDefaultRingtoneUri(AlarmActivity.this, RingtoneManager.TYPE_ALARM);
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select ringtone for notifications:");
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, currentTone);
        startActivityForResult(intent, 999);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(MainActivity.TAG, getClass() + "requestCode : " + requestCode);
        Log.d(MainActivity.TAG, getClass() + "resultCode : " + resultCode);
//        if (requestCode == 999 && requestCode == RESULT_OK) {
        uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
        Log.d(MainActivity.TAG, getClass() + "onActivityResult : " + uri.toString());

//        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.setAlarm:
                setAlarm();

                break;

            case R.id.removeAlarm:
                removeAlarm();
                break;

            case R.id.button:
                new TimePickerDialog(AlarmActivity.this, timeSetListener, Time.get(Calendar.HOUR_OF_DAY), Time.get(Calendar.MINUTE), false).show();
                new DatePickerDialog(AlarmActivity.this, eDateSetListener, Time.get(Calendar.YEAR), Time.get(Calendar.MONTH), Time.get(Calendar.DAY_OF_MONTH)).show();

                break;

            case R.id.repeatAlarm:
                setRepeatAlarm();
                break;

            case R.id.ringtone:
                setRingtone();
                break;

        }
    }

}

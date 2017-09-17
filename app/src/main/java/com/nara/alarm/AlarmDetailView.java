package com.nara.alarm;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.nara.alarm.details.DetailRepeatDays;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nara.yoon on 2017-09-10.
 */

public class AlarmDetailView extends Activity implements ViewGroup.OnClickListener{


    TextView mTimeSet;
    SimpleDateFormat simpleDateFormat;
    LinearLayout mTimeSetLayout;

    TimePicker mTimePicker;
    Calendar mCalendar;

    String hour;
    String minute;

    ViewGroup repeatDaysLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_detail_list_layout);

        repeatDaysLayout = (ViewGroup)findViewById(R.id.detail_time_repeat_days_layout);
        repeatDaysLayout.setOnClickListener(this);


        mCalendar = Calendar.getInstance();

        mTimePicker = (TimePicker) findViewById(R.id.time_picker);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = mTimePicker.getHour() + "";
            minute = mTimePicker.getMinute() + "";
        } else {
            hour = mTimePicker.getCurrentHour() + "";
            minute = mTimePicker.getCurrentMinute() + "";
        }

        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int min) {
                Toast.makeText(AlarmDetailView.this, "hour : " + hour + ", min : " + min, Toast.LENGTH_LONG).show();

            }
        });

//        simpleDateFormat = new SimpleDateFormat("a hh시 mm분");

    }

    private String getAmPm(int hour) {
        if (hour >= 12)
            return "AM";
        else
            return "PM";
    }

    private void setMinute(int min) {
        if (min >= 10)
            minute = min + "";
        else
            minute = "0" + min;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){
            case R.id.detail_time_repeat_days_layout:
                Intent intent = new Intent(this, DetailRepeatDays.class);
                startActivity(intent);
                break;
        }

    }
}

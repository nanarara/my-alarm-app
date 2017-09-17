package com.nara.alarm.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.nara.alarm.R;

public class DetailRepeatDays extends AppCompatActivity implements ViewGroup.OnClickListener {

    ImageView sunday;
    ImageView monday;
    ImageView tuesday;
    ImageView wednesday;
    ImageView thursday;
    ImageView friday;
    ImageView saturday;

    ViewGroup sundayLayout;
    ViewGroup mondayLayout;
    ViewGroup tuesdayLayout;
    ViewGroup wednesdayLayout;
    ViewGroup thursdayLayout;
    ViewGroup fridayLayout;
    ViewGroup saturdayLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_repeat_days);

        sunday = (ImageView) findViewById(R.id.sunday_check_image);
        monday = (ImageView) findViewById(R.id.monday_check_image);
        tuesday = (ImageView) findViewById(R.id.tuesday_check_image);
        wednesday = (ImageView) findViewById(R.id.wednesday_check_image);
        thursday = (ImageView) findViewById(R.id.thursday_check_image);
        friday = (ImageView) findViewById(R.id.friday_check_image);
        saturday = (ImageView) findViewById(R.id.saturday_check_image);

        sundayLayout = (RelativeLayout) findViewById(R.id.sunday_layout);
        mondayLayout = (RelativeLayout) findViewById(R.id.monday_layout);
        tuesdayLayout = (RelativeLayout) findViewById(R.id.tuesday_layout);
        wednesdayLayout = (RelativeLayout) findViewById(R.id.wednesday_layout);
        thursdayLayout = (RelativeLayout) findViewById(R.id.thursday_layout);
        fridayLayout = (RelativeLayout) findViewById(R.id.friday_layout);
        saturdayLayout = (RelativeLayout) findViewById(R.id.saturday_layout);

        sundayLayout.setOnClickListener(this);
        mondayLayout.setOnClickListener(this);
        tuesdayLayout.setOnClickListener(this);
        wednesdayLayout.setOnClickListener(this);
        thursdayLayout.setOnClickListener(this);
        fridayLayout.setOnClickListener(this);
        saturdayLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.sunday_layout:
                if(sunday.getVisibility()==View.VISIBLE){
                    sunday.setVisibility(View.GONE);
                }else{
                    sunday.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.monday_layout:
                if(monday.getVisibility()==View.VISIBLE){
                    monday.setVisibility(View.GONE);
                }else{
                    monday.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.tuesday_layout:
                if(tuesday.getVisibility()==View.VISIBLE){
                    tuesday.setVisibility(View.GONE);
                }else{
                    tuesday.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.wednesday_layout:
                if(wednesday.getVisibility()==View.VISIBLE){
                    wednesday.setVisibility(View.GONE);
                }else{
                    wednesday.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.thursday_layout:
                if(thursday.getVisibility()==View.VISIBLE){
                    thursday.setVisibility(View.GONE);
                }else{
                    thursday.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.friday_layout:
                if(friday.getVisibility()==View.VISIBLE){
                    friday.setVisibility(View.GONE);
                }else{
                    friday.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.saturday_layout:
                if(saturday.getVisibility()==View.VISIBLE){
                    saturday.setVisibility(View.GONE);
                }else{
                    saturday.setVisibility(View.VISIBLE);
                }
                break;

        }

    }
}

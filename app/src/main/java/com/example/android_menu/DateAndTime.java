package com.example.android_menu;

import android.app.Application;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;


public class DateAndTime extends AppCompatActivity {
    DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
    TextView lblDateAndTime;
    Calendar myCalendar = Calendar.getInstance();
    int Year;
    int Month;
    int Day;
    int Hour;
    int Minute;
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view,
                              int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            Year = year;
            Month = monthOfYear;
            Day = dayOfMonth;
            updateLabel(year, monthOfYear, dayOfMonth, Hour, Minute);
        }
    };
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            myCalendar.set(Calendar.MINUTE, minute);
            Hour = hourOfDay;
            Minute = minute;
            updateLabel(Year, Month, Day, hourOfDay, minute);
        }
    };

    public void updateLabel(int year, int month, int day, int hour, int minute) {
        lblDateAndTime = (TextView) findViewById(R.id.lblDateAndTime);
        lblDateAndTime.setText("Time is" + Hour + ":" + Minute + " " + Day + "/" + Month + "/" + Year);
    }

    ;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.date_time);
        lblDateAndTime = (TextView) findViewById(R.id.lblDateAndTime);
        Button btnDate = (Button) findViewById(R.id.btnDate);
        btnDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(DateAndTime.this, d,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        Button btnTime = (Button) findViewById(R.id.btnTime);
        btnTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(DateAndTime.this, t,
                        myCalendar.get(Calendar.HOUR_OF_DAY),
                        myCalendar.get(Calendar.MINUTE), true).show();
            }
        });
        updateLabel(Day, Month, Year, Hour, Minute);
    }
}

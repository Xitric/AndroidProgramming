package com.xitric.wizardapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

public class BirthdateActivity extends AppCompatActivity {

    private String name;
    private String address;
    private String birthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthdate);

        Intent intent = getIntent();
        name = intent.getStringExtra(Extras.EXTRA_NAME);
        address = intent.getStringExtra(Extras.EXTRA_ADDRESS);

        CalendarView calendarView = findViewById(R.id.datePicker);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                birthDate = dayOfMonth + "/" + (month + 1) + " - " + year;
            }
        });
    }

    public void nextButtonPressed(View view) {
        Intent intent = new Intent(this, ResumeActivity.class);
        intent.putExtra(Extras.EXTRA_NAME, name);
        intent.putExtra(Extras.EXTRA_ADDRESS, address);
        intent.putExtra(Extras.EXTRA_BIRTHDATE, birthDate);
        startActivity(intent);
    }

    public void previousButtonPressed(View view) {
        finish();
    }
}

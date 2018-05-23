package com.example.yarasabry.store2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {


    CalendarView myCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myCalendarView = (CalendarView) findViewById(R.id.myCalendarView); // get the reference of CalendarView
        myCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // display the selected date by using a toast
                month=month+1;
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();

            }
        });
    }

    public void buttonClick(View view){
        Intent mintent = new Intent(HomeActivity.this,AddActivity.class);
        startActivity(mintent);
    }
    public void viewEvents(View view){
        Intent min = new Intent(HomeActivity.this,ViewActivity.class);
        startActivity(min);
    }

}
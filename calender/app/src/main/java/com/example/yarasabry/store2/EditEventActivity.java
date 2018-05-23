package com.example.yarasabry.store2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class EditEventActivity extends AppCompatActivity {
    String nameValue,locationValue,startdateValue,enddateValue,id;
    EditText name,location;
    TextView startdate,enddate;
    private DatabaseReference mDatabase;
    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();
    private Button start_date_btn;
    private Button start_time_btn;
    private Button end_date_btn;
    private Button end_time_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        //elli hastlmhom mn el productadapter
        nameValue = getIntent().getExtras().getString("name");
        locationValue = getIntent().getExtras().getString("location");
        startdateValue = getIntent().getExtras().getString("startdate");
        enddateValue = getIntent().getExtras().getString("enddate");
        id = getIntent().getExtras().getString("id");

        name = (EditText) findViewById(R.id.update_name);
        location = (EditText)findViewById(R.id.update_location);
        startdate = (TextView) findViewById(R.id.update_startdate);
        enddate = (TextView) findViewById(R.id.update_enddate);
        start_date_btn = (Button) findViewById(R.id.start_date_btn);
        start_time_btn = (Button) findViewById(R.id.start_time_btn);
        end_date_btn = (Button) findViewById(R.id.end_date_btn);
        end_time_btn = (Button) findViewById(R.id.end_time_btn);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //----------------------------------------------------------------------

        name.setText(nameValue);
        location.setText(locationValue);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        start_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDateStart();
            }

        });
        start_time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTimeStart();
            }
        });
        updateTextstart();
        end_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDateEnd();
            }

        });
        end_time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTimeEnd();
            }
        });
        updateTextend();

    }
    private void updateDateStart(){
        new DatePickerDialog(this, ds, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateTimeStart(){
        new TimePickerDialog(this, ts, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }

    private void updateDateEnd(){
        new DatePickerDialog(this, de, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }
    private void updateTimeEnd(){
        new TimePickerDialog(this, te, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }

    DatePickerDialog.OnDateSetListener ds = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTextstart();
        }
    };
    TimePickerDialog.OnTimeSetListener ts = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            updateTextstart();
        }
    };

    DatePickerDialog.OnDateSetListener de = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTextend();
        }
    };
    TimePickerDialog.OnTimeSetListener te = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            updateTextend();
        }
    };
    private void updateTextstart(){
        startdate.setText(formatDateTime.format(dateTime.getTime()));
    }
    private void updateTextend(){
        enddate.setText(formatDateTime.format(dateTime.getTime()));
    }




    public void Update(View view){
        String nameText,loactionText,startdateText,enddateText;
        nameText = name.getText().toString();
        loactionText = location.getText().toString();
        startdateText = startdate.getText().toString();
        enddateText = enddate.getText().toString();
        Event event = new Event(nameText,loactionText,startdateText,enddateText);
        event.setId(id);
        mDatabase.child("events").child(id).setValue(event);//kda el key hyb2a random
        onBackPressed();

    }
}

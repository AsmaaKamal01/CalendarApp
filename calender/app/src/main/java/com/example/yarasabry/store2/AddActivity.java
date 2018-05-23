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

public class AddActivity extends AppCompatActivity {


    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();
    private DatabaseReference mDatabase;
    private EditText name;
    private EditText loc;
    private TextView start_text;
    private TextView end_text;
    private Button start_date_btn;
    private Button start_time_btn;
    private Button end_date_btn;
    private Button end_time_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        start_text = (TextView) findViewById(R.id.start);
        end_text = (TextView) findViewById(R.id.end);
        start_date_btn = (Button) findViewById(R.id.start_date_btn);
        start_time_btn = (Button) findViewById(R.id.start_time_btn);
        end_date_btn = (Button) findViewById(R.id.end_date_btn);
        end_time_btn = (Button) findViewById(R.id.end_time_btn);
        name = (EditText) findViewById(R.id.name_event);
        loc = (EditText) findViewById(R.id.location);
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
        start_text.setText(formatDateTime.format(dateTime.getTime()));
    }
    private void updateTextend(){
        end_text.setText(formatDateTime.format(dateTime.getTime()));
    }

    public void Add(View view){
        //-----validation-------------------------
        String nameEvent,locEvent,start_Text,end_Text;
        nameEvent = name.getText().toString();
        locEvent = loc.getText().toString();
        start_Text = start_text.getText().toString();
        end_Text = end_text.getText().toString();


        //------------------add to firebesae-----------------
        Event event = new Event(nameEvent,locEvent,start_Text,end_Text);
        String key = mDatabase.child("events").push().getKey();
        //events de el id kda hwa lkol event da5l gded hyd5l id unique
        event.setId(key);//kda adeto el key
        mDatabase.child("events").child(key).setValue(event);//kda el key hyb2a random
        //ha5lii b2a b3d ma ydef el add yfdii el edit text
        name.setText("");
        loc.setText("");
        start_text.setText("");
        end_text.setText("");






    }

}

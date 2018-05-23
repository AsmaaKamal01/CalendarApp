/*package com.example.yarasabry.store2;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.yarasabry.store2.R.string.date;

public class AddEventActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
TextInputEditText name ,loc,start_time,end_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        name = (TextInputEditText)findViewById(R.id.add_name);
        start_time = (TextInputEditText)findViewById(R.id.add_date);
        time = (TextInputEditText)findViewById(R.id.add_time);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public void Add(View view){
        //-----validation-------------------------
        String nameText,dateText,timeText;
        nameText = name.getText().toString();
        dateText = date.getText().toString();
        timeText = time.getText().toString();

        if(nameText.isEmpty() ||nameText.equals(" ")){
            name.setError("fill here please !");
            return ;
        }
        if(dateText.isEmpty() ||dateText.equals(" ")){
            date.setError("fill there !");
            return ;
        }
        if(timeText.isEmpty() ||timeText.equals(" ")){
            time.setError("fill there !");
            return ;
        }
        //----------------------------------end validation-----------------------
        //------------------add to firebesae-----------------
        Event event = new Event(nameText,dateText,timeText);
        String key = mDatabase.child("events").push().getKey();
        //events de el id kda hwa lkol event da5l gded hyd5l id unique
        event.setId(key);//kda adeto el key
        mDatabase.child("events").child(key).setValue(event);//kda el key hyb2a random
        //ha5lii b2a b3d ma ydef el add yfdii el edit text
        name.setText("");
        date.setText("");
        time.setText("");






    }
}*/

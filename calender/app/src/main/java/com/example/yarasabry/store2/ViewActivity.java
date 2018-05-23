package com.example.yarasabry.store2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Event> events;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        listView = (ListView)findViewById(R.id.events_list);

        //----------------3shan agep el data elli fe el firebease(source data)-----
        mDatabase = FirebaseDatabase.getInstance().getReference().child("events");


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                events = new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    //hna hagep el event elli rage3 mn el firebease
                    Event event = postSnapshot.getValue(Event.class);
                    events.add(event);
                    //kda hygap el data mn el firebase w y7tha fe arraylist elii asmo events
                }
                EventsAdapter adapter = new EventsAdapter(ViewActivity.this, events);// el adapter

                listView.setAdapter(adapter);// kda rpt el list view bl adapter
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

//---------------------------------------------------

    }
    public void addProduct(View view){
        Intent addproduct = new Intent (ViewActivity.this,AddActivity.class);
        startActivity(addproduct);
    }
}

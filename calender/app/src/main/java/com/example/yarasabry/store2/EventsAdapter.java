package com.example.yarasabry.store2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Yara Sabry on 4/22/2018.
 */

public class EventsAdapter extends ArrayAdapter<Event> {
    private DatabaseReference mDatabase;
    public EventsAdapter(Context context, ArrayList<Event> events) {//list de yhgbha mn el firebease
        super(context, 0, events);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override // del elli bt3ml el 3red(bt3rd lya y3nii )
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Event event = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_event, parent, false);
            //elly by3rd el view
        }
        TextView name,location, startdate, enddatee;
        ImageView edit, delete;
        name = (TextView) convertView.findViewById(R.id.event_name);
        location = (TextView) convertView.findViewById(R.id.event_location);
        startdate = (TextView) convertView.findViewById(R.id.event_startdate);
        enddatee = (TextView) convertView.findViewById(R.id.event_enddate);
        name.setText(event.getName());
        location.setText("Location = "+ event.getLocation());
        startdate.setText("StartDate,Time = "+ event.getStarttime());
        enddatee.setText("EndDate,Time = "+ event.getEndtime());

        edit = (ImageView)convertView.findViewById(R.id.edit);
        delete = (ImageView)convertView.findViewById(R.id.delete);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(getContext(),EditEventActivity.class);
                edit.putExtra("name", event.getName());//hab3t m3ah eldata bta3to to EditEventActivity.class
                edit.putExtra("location", event.getLocation());
                edit.putExtra("starttime", event.getStarttime());
                edit.putExtra("endtime", event.getEndtime());
                edit.putExtra("id", event.getId());
                getContext().startActivity(edit);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//el mafrood hamse7 mn el firebease w mn el list view
                // hamse7 el awel mn el firebease
                mDatabase.child("events").child(event.getId()).removeValue();
            }
        });

        return convertView;
    }
}

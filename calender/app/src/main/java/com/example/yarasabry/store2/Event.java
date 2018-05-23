package com.example.yarasabry.store2;

/**
 * Created by Yara Sabry on 4/21/2018.
 */

public class Event {
    private String id;
    private String name;
    private String location;
    private String starttime;
    private String endtime;

    public Event(String name, String location, String starttime,String endtime) {
        this.name = name;
        this.location = location;
        this.starttime = starttime;
        this.endtime = endtime;
    }
    public Event() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }
    public String getEndtime() {
        return endtime;
    }

    public void setendtime(String endtime) {
        this.endtime = endtime;
    }
}



package com.example.timetable;

public class EventModel {

    private int eventID;
    private String eventName;
    private String eventDescription;
    private String eventLocation;
    private String sTime;
    private String eTime;

    // constructor
    public EventModel(int eventID, String eventName, String eventDescription, String eventLocation, String sTime, String eTime) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.sTime = sTime;
        this.eTime = eTime;
    }

    // creating getter and setter methods
    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }


    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getStartTime() {
        return sTime;
    }

    public void setStartTime(String sTime) {
        this.sTime = sTime;
    }

    public String getFinishTime() {
        return eTime;
    }

    public void setFinishTime(String eTime) {
        this.eTime = eTime;
    }



}


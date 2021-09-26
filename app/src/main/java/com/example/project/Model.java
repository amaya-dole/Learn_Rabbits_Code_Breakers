package com.example.project;

public class Model {
    private int id;
    private String eName;
    private String eDescription;
    private String eLocation;
    private String sTime;
    private String eTime;

    //create constructor

    public Model(int id, String eName, String eDescription, String eLocation, String sTime, String eTime) {
        this.id = id;
        this.eName = eName;
        this.eDescription = eDescription;
        this.eLocation = eLocation;
        this.sTime = sTime;
        this.eTime = eTime;
    }

    //generate setters and getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String geteDescription() {
        return eDescription;
    }

    public void seteDescription(String eDescription) {
        this.eDescription = eDescription;
    }

    public String geteLocation() {
        return eLocation;
    }

    public void seteLocation(String eLocation) {
        this.eLocation = eLocation;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }
}

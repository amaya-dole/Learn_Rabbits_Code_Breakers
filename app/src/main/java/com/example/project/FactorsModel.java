package com.example.project;

public class FactorsModel {

    private int id;
    private String num1;
    private String num2;
    private String num3;

    //constructor
    public FactorsModel(int id, String num1, String num2, String num3) {
        this.id = id;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }


    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getNum3() {
        return num3;
    }

    public void setNum3(String num3) {
        this.num3 = num3;
    }


}


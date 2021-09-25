package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Calculation2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation2);
        getSupportActionBar().hide();
    }
    public void openCalc(View view){
        Intent i = new Intent(this, Calculation1.class);
        startActivity(i);
    }
}
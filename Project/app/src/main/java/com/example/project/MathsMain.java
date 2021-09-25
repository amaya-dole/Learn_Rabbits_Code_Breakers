package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MathsMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths_main);
    }
    public void openCalculation(View view){
        Intent i = new Intent(this, Calculation2.class);
        startActivity(i);
    }

    public void openNumbers(View view){
        Intent i = new Intent(this, Numbers1.class);
        startActivity(i);
    }
}
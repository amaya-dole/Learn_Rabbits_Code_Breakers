package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main_ui extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);
    }
    public void openCount(View view){
        Intent intent1 = new Intent(Main_ui.this, CountMain.class);

        startActivity(intent1);
    }
    public void openTime(View view){
        Intent intent1 = new Intent(Main_ui.this, viewEvents.class);

        startActivity(intent1);
    }
}
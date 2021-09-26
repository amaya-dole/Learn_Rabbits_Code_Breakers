package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ImageUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.imageui);

        Intent intentHome1 = getIntent();
    }

    public void openHome(View view){
        Intent intent = new Intent(this, Main_UI.class);
        startActivity(intent);
    }

    public void openAnimals(View view) {
        Intent intent1 = new Intent(this, animalsPg.class);

        startActivity(intent1);
    }
    public void openColors(View view) {
        Intent intent2 = new Intent(this, coloursPg.class);
        startActivity(intent2);
    }


}

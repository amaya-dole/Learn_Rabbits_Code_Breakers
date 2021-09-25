package com.example.intentproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText n1;
    EditText n2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        n1 = findViewById(R.id.n1);
        n2 = findViewById(R.id.n2);

    }

    public void openSecond(View view){
        Intent pass = new Intent(this, SecondActivity.class);

        String num1 = n1.getText().toString();
        String num2 = n2.getText().toString();

        pass.putExtra("n1", num1);
        pass.putExtra("n2", num2);

        startActivity(pass);
    }
}
package com.example.tempcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

     EditText TempInput;
     RadioButton radioButtonCel;
     RadioButton radioButtonfahren;
     Button buttonCalc;
     TextView txt_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TempInput = findViewById(R.id.TempInput);
        radioButtonCel = findViewById(R.id.radioButtonCel);
        radioButtonfahren = findViewById(R.id.radioButtonfahren);
        buttonCalc = findViewById(R.id.buttonCalc);
        txt_answer = findViewById(R.id.txt_answer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAnswer();
            }
        });
    }
    private void calculateAnswer() {
        Calculations calc = new Calculations();
        String value = TempInput.getText().toString();
        if(TextUtils.isEmpty(value)){
            Toast.makeText(this, "Please add a value", Toast.LENGTH_SHORT).show();
        }else{
            float temp = Float.parseFloat(value);
            if(radioButtonCel.isChecked()){
                temp = calc.convertCelciusToFahrenheit(temp);
            }else{
                temp = calc.convertFahrenheitToCelcius(temp);
            }

            txt_answer.setText(new Float(temp).toString());
        }
    }
}
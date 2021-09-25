package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import javax.microedition.khronos.egl.EGLDisplay;

import static com.example.timetable.DatabaseHelper.TABLE2_NAME;

public class CreateEvent extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;

    EditText eName, eDescription, eLocation, sTime, eTime;
    Button createBtn, readBtn, update;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        databaseHelper = new DatabaseHelper(CreateEvent.this);
        createBtn = (Button)findViewById(R.id.btn_create);
        readBtn = (Button)findViewById(R.id.btn_read);

        //create method
        InsertData();
        findId();
        updateData();
    }

    private void updateData() {
        if (getIntent().getBundleExtra("eventdata")!=null){
            Bundle bundle = getIntent().getBundleExtra("eventdata");
            id = bundle.getInt("id");
            eName.setText(bundle.getString("eName"));
            eDescription.setText(bundle.getString("eDescription"));
            eLocation.setText(bundle.getString("eLocation"));
            sTime.setText(bundle.getString("sTime"));
            update.setVisibility(View.VISIBLE);
            createBtn.setVisibility(View.GONE);
        }

    }


    private void InsertData() {

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get data from all edit text fields.
                //get data from all edit text fields.

                String eventName = eName.getText().toString();
                String eventDescription = eDescription.getText().toString();
                String eventLocation = eLocation.getText().toString();
                String startTime = sTime.getText().toString();
                String endTime = eTime.getText().toString();

                // validating if the text fields are empty or not.
                if (eventName.isEmpty() && eventDescription.isEmpty() && eventLocation.isEmpty() && startTime.isEmpty() && endTime.isEmpty()) {
                    Toast.makeText(CreateEvent.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                databaseHelper.addNewEvent(eventName, eventDescription, eventLocation, startTime, endTime);

                // after adding the data we are displaying a toast message.
                Toast.makeText(CreateEvent.this, "Event has been created.", Toast.LENGTH_SHORT).show();

                //clear fields when click on create
                eName.setText("");
                eDescription.setText("");
                eLocation.setText("");
                sTime.setText("");
                eTime.setText("");

            }

        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateEvent.this, viewEvents.class);
                startActivity(intent);
            }
        });

        //storing updated data



    }
    private void findId(){
        eName = (EditText)findViewById(R.id.event);
        eDescription = (EditText)findViewById(R.id.explain);
        eLocation = (EditText)findViewById(R.id.location);
        sTime = (EditText)findViewById(R.id.stime);
        eTime = (EditText) findViewById(R.id.etime);
        createBtn = (Button)findViewById(R.id.btn_create);
        readBtn = (Button)findViewById(R.id.btn_read);
        update = (Button) findViewById(R.id.btn_update);
    }


}
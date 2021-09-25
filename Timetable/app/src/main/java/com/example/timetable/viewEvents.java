package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import static com.example.timetable.DatabaseHelper.TABLE2_NAME;
import java.util.ArrayList;

public class viewEvents extends AppCompatActivity {

     ArrayList<EventModel> eventModelArrayList;
     RecyclerView recyclerView;
     DatabaseHelper dbHelper;
     EventAdapter eventAdapter;
     SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);

        dbHelper = new DatabaseHelper(this);

        //create method
        findID();
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void displayData() {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+ TABLE2_NAME +"",null);
        ArrayList<EventModel>modelArrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String eName = cursor.getString(1);
            String eDesc = cursor.getString(2);
            String eLocation = cursor.getString(3);
            String sTime = cursor.getString(4);
            String eTime = cursor.getString(5);
            modelArrayList.add(new EventModel(id, eName, eDesc, eLocation, sTime, eTime));
        }
        cursor.close();
        eventAdapter = new EventAdapter(this, R.layout.single_event,modelArrayList, sqLiteDatabase);
        recyclerView.setAdapter(eventAdapter);
    }

    private void findID() {
        recyclerView = findViewById(R.id.rv);
    }
}
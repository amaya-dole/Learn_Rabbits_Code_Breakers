package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import static com.example.project.DBmain.TABLENAME;

public class DisplayData extends AppCompatActivity {
DBmain dBmain;
SQLiteDatabase sqLiteDatabase;
RecyclerView recyclerView;
MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        dBmain = new DBmain(this);
        //create method
        findid();
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void displayData() {
        sqLiteDatabase = dBmain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+ TABLENAME +"",null);
        ArrayList<Model> modelArrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String eName = cursor.getString(1);
            String eDescription = cursor.getString(2);
            String eLocation = cursor.getString(3);
            String sTime = cursor.getString(4);
            String eTime = cursor.getString(5);
            modelArrayList.add(new Model(id, eName, eDescription, eLocation, sTime, eTime));
        }
        cursor.close();
        myAdapter = new MyAdapter(this, R.layout.singledata,modelArrayList, sqLiteDatabase);
        recyclerView.setAdapter(myAdapter);
    }

    private void findid() {
        recyclerView = findViewById(R.id.rv);
    }

    public void openCreate(View view){
        Intent intent1 = new Intent(DisplayData.this, MainActivity.class);

        startActivity(intent1);
    }
}
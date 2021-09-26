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

public class ToMemorize extends AppCompatActivity {
    DatabaseMain databaseMain;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    FactorAdapter factorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_memorize);
        getSupportActionBar().hide();


        databaseMain = new DatabaseMain(this);
        //create method
        findid();
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void openHome(View view){
        Intent intent = new Intent(this, addFactors.class);
        startActivity(intent);
    }

    private void displayData() {
        sqLiteDatabase = databaseMain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select *from factors ",null);
        ArrayList<FactorsModel> factorsModelArrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String num1 = cursor.getString(1);
            String num2 = cursor.getString(2);
            String num3 = cursor.getString(3);
            factorsModelArrayList.add(new FactorsModel(id, num1,num2,num3));
        }
        cursor.close();
        factorAdapter = new FactorAdapter(this, R.layout.factorsdata, factorsModelArrayList,sqLiteDatabase);
        recyclerView.setAdapter(factorAdapter);
    }

    private void findid() {
        recyclerView = findViewById(R.id.recyclerView);
    }
}
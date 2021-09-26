package com.example.project;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseMain extends SQLiteOpenHelper {
    public static final String DBNAME = "multifactors.db";
    public static final String TABLENAME = "factors";
    public static final int VER= 1;

    String query;

    public DatabaseMain(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        query = "create table "+TABLENAME+"(id integer primary key, num1 text, num2 text, num3 text)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        query="drop table if exists "+TABLENAME+"";
        db.execSQL(query);
        onCreate(db);
    }
}


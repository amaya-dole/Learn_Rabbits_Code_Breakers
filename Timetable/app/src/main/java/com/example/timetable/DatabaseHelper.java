package com.example.timetable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG = "dbHelper";
    private static final String dbName = "abcDB.db";
    static final String TABLE2_NAME = "event";
    private static final String col1 = "_eventID";
    private static final String col2 = "eventName";
    private static final String col3 = "eventDesc";
    private static final String col4 = "eventLoc";
    private static final String col5 = "sTime";
    private static final String col6 = "eTime";
    String query;

    public DatabaseHelper(@Nullable Context context) {

        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        query = "CREATE TABLE " + TABLE2_NAME + " ("
                + col1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + col2 + " TEXT,"
                + col3 + " TEXT,"
                + col4 + " TEXT,"
                + col5 + " TIME,"
                + col6 + " TIME)";
        db.execSQL(query);

    }

    public void addNewEvent(String eventName, String eventDesc, String eventLoc, String sTime, String eTime) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(col2, eventName);
        values.put(col3, eventDesc);
        values.put(col4, eventLoc);
        values.put(col5, sTime);
        values.put(col6, eTime);

        // after adding all values we are passing
        // content values to our table.
        long eID = db.insert(TABLE2_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }



    public ArrayList<EventModel> readEvent() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorEvent = db.rawQuery("SELECT * FROM " + TABLE2_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<EventModel> eventModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorEvent.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                eventModalArrayList.add(new EventModel(
                        cursorEvent.getInt(1),
                        cursorEvent.getString(4),
                        cursorEvent.getString(2),
                        cursorEvent.getString(3),
                        cursorEvent.getString(5),
                        cursorEvent.getString(5)));
            } while (cursorEvent.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorEvent.close();
        return eventModalArrayList;
    }

    public void updateEvent(String eventName, String eventDesc, String eventLoc, String sTime, String eTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(col2, eventName);
        values.put(col3, eventDesc);
        values.put(col4, eventLoc);
        values.put(col5, sTime);
        values.put(col6, eTime);

        db.update(TABLE2_NAME, values, "name=?", new String[]{eventName});
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        query = "Drop table if exists " + TABLE2_NAME + "";
        db.execSQL(query);
        onCreate(db);
    }
}


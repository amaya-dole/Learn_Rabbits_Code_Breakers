package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.project.DBmain.TABLENAME;

public class MainActivity extends AppCompatActivity {
DBmain dbmain;
SQLiteDatabase sqLiteDatabase;
EditText eName, eDescription, eLocation, sTime, eTime;
Button submit, display,edit;
int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbmain = new DBmain(this);

        //create method
        findid();
        insertData();
        editData();
    }

    private void editData() {
        if(getIntent().getBundleExtra("eventdata") != null){
            Bundle bundle = getIntent().getBundleExtra("eventdata");
            id = bundle.getInt("id");
            eName.setText(bundle.getString("eName"));
            eDescription.setText(bundle.getString("eDescription"));
            eLocation.setText(bundle.getString("eLocation"));
            sTime.setText(bundle.getString("sTime"));
            eTime.setText(bundle.getString("eTime"));
            edit.setVisibility(View.VISIBLE);
            submit.setVisibility(View.GONE);
        }
    }

    private void insertData() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("eName", eName.getText().toString());
                cv.put("eDescription", eDescription.getText().toString());
                cv.put("eLocation", eLocation.getText().toString());
                cv.put("sTime", sTime.getText().toString());
                cv.put("eTime", eTime.getText().toString());
                sqLiteDatabase = dbmain.getWritableDatabase();
                Long recinsert = sqLiteDatabase.insert(TABLENAME, null, cv);
                if(recinsert != null){
                    Toast.makeText(MainActivity.this, "Event Created successfully!", Toast.LENGTH_SHORT).show();
                    //clear when click on create
                    eName.setText("");
                    eDescription.setText("");
                    eLocation.setText("");
                    sTime.setText("");
                    eTime.setText("");
                }else{
                    Toast.makeText(MainActivity.this, "Something went wrong. Try Again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //open display window when click on display button
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayData.class);
                startActivity(intent);
            }
        });

        //store updated data
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("eName", eName.getText().toString());
                cv.put("eDescription", eDescription.getText().toString());
                cv.put("eLocation", eLocation.getText().toString());
                cv.put("sTime", sTime.getText().toString());
                cv.put("eTime", eTime.getText().toString());

                sqLiteDatabase = dbmain.getReadableDatabase();
                long recedit = sqLiteDatabase.update(TABLENAME, cv, "id="+id, null);
                if(recedit != -1){
                    Toast.makeText(MainActivity.this, "Event Updated Successfully", Toast.LENGTH_SHORT).show();
                    submit.setVisibility(View.VISIBLE);
                    edit.setVisibility(View.GONE);
                }else{
                    Toast.makeText(MainActivity.this, "Something went wrong. Try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findid() {
        eName = (EditText)findViewById(R.id.eName);
        eDescription = (EditText)findViewById(R.id.eDescription);
        eLocation = (EditText)findViewById(R.id.eLocation);
        sTime = (EditText)findViewById(R.id.sTime);
        eTime = (EditText)findViewById(R.id.eTime);
        submit = (Button) findViewById(R.id.btn_create);
        display = (Button) findViewById(R.id.btn_read);
        edit = (Button)findViewById(R.id.btn_update);
    }
}
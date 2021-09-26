package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addFactors extends AppCompatActivity {
    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;

    EditText num1,num2,num3;
    Button save,view,edit,home;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_factors);

        Intent intent = getIntent();

        dBmain= new DBmain(this);
        //create method
        findid();
        insertData();
        editData();

    }

    public void openHome(View view){
        Intent intent = new Intent(this, Main_UI.class);
        startActivity(intent);
    }

    private void editData() {
        if(getIntent().getBundleExtra("factorsdata")!=null){
            Bundle bundle = getIntent().getBundleExtra("factorsdata");
            id = bundle.getInt("id");
            num1.setText(bundle.getString("num1"));
            num2.setText(bundle.getString("num2"));
            num3.setText(bundle.getString("num3"));
            edit.setVisibility(View.VISIBLE);
            save.setVisibility(View.GONE);
            home.setVisibility(View.GONE);

        }
    }

    private void insertData() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv= new ContentValues();
                cv.put("num1",num1.getText().toString());
                cv.put("num2",num2.getText().toString());
                cv.put("num3",num3.getText().toString());

                sqLiteDatabase = dBmain.getWritableDatabase();
                Long recinsert = sqLiteDatabase.insert("factors", null, cv);
                if(num1.length() <=0 || num2.length() <=0 || num3.length() <=0) {
                    Toast.makeText(addFactors.this, "Enter All Data", Toast.LENGTH_SHORT).show();
                }else if (recinsert!=null){
                    Toast.makeText(addFactors.this,"Successfully Inserted", Toast.LENGTH_SHORT).show();
                    //clear when click on save
                    num1.setText("");
                    num2.setText("");
                    num3.setText("");
                    edit.setVisibility(View.GONE);
                }else{
                    Toast.makeText(addFactors.this,"Please Recheck", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //display data
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addFactors.this, ToMemorize.class);
                startActivity(intent);
            }
        });

        //storing edited data
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                cv.put("num1" , num1.getText().toString());
                cv.put("num2" , num2.getText().toString());
                cv.put("num3" , num3.getText().toString());

                sqLiteDatabase = dBmain.getReadableDatabase();
                long recedit = sqLiteDatabase.update("factors", cv, "id="+id, null);
                if(recedit!=-1){
                    Toast.makeText(addFactors.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                    save.setVisibility(View.VISIBLE);
                    edit.setVisibility(View.GONE);
                    home.setVisibility(View.GONE);
                }else {
                    Toast.makeText(addFactors.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findid() {
        num1 = findViewById(R.id.val_num1);
        num2 = findViewById(R.id.val_num2);
        num3 = findViewById(R.id.val_num3);

        save = findViewById(R.id.btn_save);
        view = findViewById(R.id.btn_view);
        edit = findViewById(R.id.btn_edit);
        home = findViewById(R.id.btn_home);

    }
}

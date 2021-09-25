package com.example.timetable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.util.BitSet;

public class MainMain extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    EditText name, age;
    Button go;
    private ImageView profImg;

    public static final int CAMERA_REQUEST = 100;
    public static final int STORAGE_REQUEST = 101;

    String cameraPermission[];
    String storagePermission[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);

        databaseHelper = new DatabaseHelper(this);
        findId();
        insertData();

        profImg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                int profImg = 0;
                if(profImg == 0){
                    if(!checkCameraPermission()){
                        requestCameraPermission();
                    }else{
                        pickFromGallery();
                    }
                }else if (profImg == 1){
                    if(!checkStoragePermission()){
                        requestStoragePermission();
                    }else{
                        pickFromGallery();
                    }
                }
            }
        });
    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestStoragePermission() {
        requestPermissions(storagePermission, STORAGE_REQUEST);
    }

    private void pickFromGallery() {
        CropImage.activity().start(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCameraPermission() {
        requestPermissions(cameraPermission, CAMERA_REQUEST);
    }

    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==(PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==(PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    private void insertData() {
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("name",name.getText().toString());
                cv.put("age",age.getText().toString());
                cv.put("profImg",imageViewToBy(profImg));

                sqLiteDatabase = databaseHelper.getWritableDatabase();
                Long rec = sqLiteDatabase.insert("profile", null, cv);
                if(rec!=null){
                    Toast.makeText(MainMain.this, "Profile Created!", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    profImg.setImageResource(R.mipmap.ic_launcher_round);

                }else{
                    Toast.makeText(MainMain.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




    private byte[] imageViewToBy(ImageView profImg) {
        Bitmap bitmap = ((BitmapDrawable)profImg.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50, stream);
        byte[]bytes = stream.toByteArray();
        return bytes;
    }

    private void findId() {
        name = (EditText)findViewById(R.id.name);
        age = (EditText)findViewById(R.id.age);
        profImg = (ImageView) findViewById(R.id.profImg) ;
        go = (Button)findViewById(R.id.btn_go);
    }


    public void openNext2(View view){
        Intent intent = new Intent(this, Main_ui.class);

        String names =name.getText().toString();
        String ages = age.getText().toString();

        intent.putExtra("name", names);
        intent.putExtra("age", ages);

        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case CAMERA_REQUEST:{
                if(grantResults.length>0){
                    boolean camera_accepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storage_accepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if(camera_accepted && storage_accepted){
                        pickFromGallery();
                    }else{
                        Toast.makeText(this, "Please enable camera and storage permission.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST:{
                if(grantResults.length>0){
                    boolean storage_accepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(storage_accepted){
                        pickFromGallery();
                    }else{
                        Toast.makeText(this, "Please enable storage permission.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                Uri resultUri = result.getUri();
                Picasso.with(this).load(resultUri).into(profImg);
            }
        }
    }

    public void openNext(View view) {
        Intent intent = new Intent(this, Main_ui.class);

        startActivity(intent);
    }
}
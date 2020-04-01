package com.example.imagedemonrefactor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.imagedemonrefactor.controller.ImageController;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.graphics.Bitmap.createScaledBitmap;

public class MainActivity extends AppCompatActivity {
public ImageView imageView;
private ImageController ic;
public Button imageBtn;
Bitmap myBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        ic= new ImageController(this);
        //imageBtn = findViewById(R.id.resizeBtn);
    }

    public void photoRollPressed(View view){

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0); // 1. photoRoll 2. CameraRoll
    }


    public void resizeImage(View view){
        Bitmap resized = createScaledBitmap(myBitmap, 400, 400, true);
        imageView.setImageBitmap(resized);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        //1. check if the result is ok, else return
        if (resultCode != -1) return; // -1 means ok
        ic.handleImageReturn(requestCode, intent);

    }

    public void cameraRollPressed(View view){
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }
}

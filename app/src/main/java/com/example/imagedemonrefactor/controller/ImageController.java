package com.example.imagedemonrefactor.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;


import androidx.annotation.Nullable;

import com.example.imagedemonrefactor.MainActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImageController {
    private MainActivity mainActivity;

    public ImageController(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }
    public void handleImageReturn(int requestCode, @Nullable Intent intent) {
        if(requestCode == 0){
            Uri uri= intent.getData();


            try {
                InputStream is = mainActivity.getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(is);

                mainActivity.imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        if(requestCode == 1){
            Bitmap bitmap =(Bitmap) intent.getExtras().get("data");
            mainActivity.imageView.setImageBitmap(bitmap);
        }
    }

}

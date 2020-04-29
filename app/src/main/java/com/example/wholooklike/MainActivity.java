package com.example.wholooklike;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;

    public ImageView userPhoto;
    public ImageView analyzeResult;
    public TextView lookLikeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userPhoto = findViewById(R.id.userPhoto);
        lookLikeText = findViewById(R.id.lookLikeText);
        analyzeResult = findViewById(R.id.analyzeResult);
    }

    public void onClick(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            userPhoto.setImageBitmap(image);
            String imageName = this.getRandomImage();
            lookLikeText.setText("Você se parece com " + imageName);
            this.setImageAnalyzeResult(imageName);
        }
    }

    public String getRandomImage() {
        List<String> images = Arrays.asList("jim", "terry", "will", "rochell");
        Random random = new Random();
        return images.get(random.nextInt(images.size()));
    }

    public void setImageAnalyzeResult(String imageName){
        switch (imageName) {
            case "jim":
                analyzeResult.setImageDrawable(getResources().getDrawable(R.drawable.jim));
                 break;
            case "will":
                analyzeResult.setImageDrawable(getResources().getDrawable(R.drawable.will));
                break;
            case "rochell":
                analyzeResult.setImageDrawable(getResources().getDrawable(R.drawable.rochell));
                break;
            case "terry":
                analyzeResult.setImageDrawable(getResources().getDrawable(R.drawable.terry));
                break;
            case "caruso":
                analyzeResult.setImageDrawable(getResources().getDrawable(R.drawable.caruso));
                break;
        }
    }
}

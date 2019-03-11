package com.xitric.pictureapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private final int[] IMAGES = {
            R.drawable.start,
            R.drawable.move,
            R.drawable.left,
            R.drawable.right,
            R.drawable.jump
    };
    private int imageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setImage();
    }

    public void btnPrevious(View view) {
        imageIndex = ((imageIndex - 1) + IMAGES.length) % IMAGES.length;
        setImage();
    }

    public void btnNext(View view) {
        imageIndex = (imageIndex + 1) % IMAGES.length;
        setImage();
    }

    private void setImage() {
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(IMAGES[imageIndex]);
    }
}

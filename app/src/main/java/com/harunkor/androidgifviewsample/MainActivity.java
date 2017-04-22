package com.harunkor.androidgifviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gifImageView=(GifImageView)findViewById(R.id.imgview);
        gifImageView.setGifImageResource(R.drawable.jetgil);


        gifImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gifImageView.setVisibility(View.INVISIBLE);
            }
        });



    }
}

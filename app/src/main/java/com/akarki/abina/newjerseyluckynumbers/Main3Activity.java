package com.akarki.abina.newjerseyluckynumbers;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.github.chrisbanes.photoview.PhotoView;


public class Main3Activity extends AppCompatActivity {

    private PhotoView photoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button image1 = findViewById(R.id.image1);
        Button image2 = findViewById(R.id.image2);
        photoView = findViewById(R.id.photoview);
        RelativeLayout btnLayout = findViewById(R.id.btnLayout);


        if (getIntent().hasExtra(MainActivity.IMAGE_RES_ID_KEY1) && getIntent().hasExtra(MainActivity.IMAGE_RES_ID_KEY)) {
            image1.setText(getIntent().getStringExtra(MainActivity.IMG_NAME));
            image2.setText(getIntent().getStringExtra(MainActivity.IMG_NAME1));
            photoView.setImageResource(getIntent().getIntExtra(MainActivity.IMAGE_RES_ID_KEY, 0));

            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    photoView.setImageResource(getIntent().getIntExtra(MainActivity.IMAGE_RES_ID_KEY, 0));

                }
            });

            image2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    photoView.setImageResource(getIntent().getIntExtra(MainActivity.IMAGE_RES_ID_KEY1, 0));

                }
            });
        }
        else if(getIntent().hasExtra(MainActivity.IMAGE_RES_ID_KEY1)) {
            btnLayout.setBackgroundColor(Color.parseColor("#85C1E9"));
            image1.setText(getIntent().getStringExtra(MainActivity.IMG_NAME));
            image2.setVisibility(View.GONE);
            image2.setBackgroundColor(Color.parseColor("#85C1E9"));
            photoView.setImageResource(getIntent().getIntExtra(MainActivity.IMAGE_RES_ID_KEY1, 0));

            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.njlottery.com/en-us/playertools/hotcold.html"));
                    startActivity(intent);
                }
            });

        }
        else {
            image1.setVisibility(View.GONE);
            image1.setBackgroundColor(Color.parseColor("#85C1E9"));
            btnLayout.setBackgroundColor(Color.parseColor("#85C1E9"));
            image2.setVisibility(View.GONE);
            image2.setBackgroundColor(Color.parseColor("#85C1E9"));
            photoView.setImageResource(getIntent().getIntExtra(MainActivity.IMAGE_RES_ID_KEY, 0));
        }

    }
}

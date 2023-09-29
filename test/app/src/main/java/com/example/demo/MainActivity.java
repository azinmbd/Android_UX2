package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    final String TAG = "Gesture DEMO";
    boolean bigger = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtViewSample = findViewById(R.id.TextViewSample);
        ImageView imgViewSample = findViewById(R.id.imageViewSample);

        Log.d(TAG, "Started Gesture Demo");

        try {

        } catch (Exception ex){
            Log.e(TAG, "File error");

            ex.printStackTrace();
        }

        Drawable img = ResourcesCompat.getDrawable(getResources(),
                                                    R.drawable.border, getTheme());

        Objects.requireNonNull(img, "Somehow image is null");
        img.setBounds(0, 0, img.getIntrinsicWidth(), img.getIntrinsicHeight());

        txtViewSample.setCompoundDrawables(img, null,img, null);
        txtViewSample.setCompoundDrawablePadding(8);
        txtViewSample.setAlpha(1.0f);

        Button btnShowTextOrImage = findViewById(R.id.buttonShowTextOrImage);
        Button btnShowBoth = findViewById(R.id.button2);

        btnShowTextOrImage.setOnClickListener((View view) -> {
              if (btnShowTextOrImage.getText().toString().equals("Show Text")){
                  txtViewSample.setVisibility(View.VISIBLE);
                  imgViewSample.setVisibility(View.INVISIBLE);
                  btnShowTextOrImage.setText(getResources().getText(R.string.txtShowImage));
              } else{
                  imgViewSample.setVisibility(View.VISIBLE);
                  txtViewSample.setVisibility(View.GONE);
                  btnShowTextOrImage.setText(getResources().getText(R.string.txtShowText));
              }



        });

        btnShowBoth.setOnClickListener((View view) -> {
            txtViewSample.setVisibility(View.VISIBLE);
            imgViewSample.setVisibility(View.VISIBLE);

        });


        txtViewSample.setOnTouchListener(new CustomTouchListener(MainActivity.this){
            @Override
            public void onSwipeDown() {
                super.onSwipeDown();
            }

            @Override
            public void onSwipeUp() {
                super.onSwipeUp();
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
            }

            @Override
            public void onDoubleTapClick() {
                if (!bigger){
                    txtViewSample.setTextSize(
                            txtViewSample.getTextSize()/getResources().getDisplayMetrics().density + 10
                    );
                    bigger = true;
                }else{
                    txtViewSample.setTextSize(
                            txtViewSample.getTextSize()/getResources().getDisplayMetrics().density - 10
                    );
                    bigger = false;
                }
                super.onDoubleTapClick();
            }

            @Override
            public void onSingleClick() {
                if (txtViewSample.getCurrentTextColor() != ResourcesCompat.getColor(
                        getResources(),R.color.purple_200, getTheme()))
                {
                    txtViewSample.setTextColor(ResourcesCompat.getColor(
                            getResources(),R.color.purple_200, getTheme()));
                } else {
                    txtViewSample.setTextColor((Color.WHITE));
                    txtViewSample.setTextColor(Color.rgb(255,255,255));
                }
                super.onSingleClick();
            }

            @Override
            public void onLongClick() {
                // long click behavior for textview
                if (txtViewSample.getPaint().isStrikeThruText()){
                    txtViewSample.setPaintFlags(
                            txtViewSample.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG
                    );
                } else {
                    txtViewSample.setPaintFlags(
                            txtViewSample.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                super.onLongClick();
            }

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return super.onTouch(view, motionEvent);
            }
        });
    }
}
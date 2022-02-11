package com.example.mainelectro.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.mainelectro.R;

public class HelpActivity extends AppCompatActivity {

    private String urlContactUs = "https://handitech-france.fr/contactez-nous";
    private AppCompatButton buttonContactUs;
    private AppCompatButton buttonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        buttonContactUs = findViewById(R.id.buttonContactUs);
        buttonHome = findViewById(R.id.buttonHome);

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttonHome.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    buttonHome.setBackgroundColor(Color.parseColor("#E3B926"));
                    buttonHome.setTextColor(Color.parseColor("#3C3C3E"));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    buttonHome.setBackgroundColor(Color.parseColor("#3C3C3E"));
                    buttonHome.setTextColor(Color.parseColor("#FFFFFF"));
                }
                return false;
            }
        });
        buttonContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(urlContactUs));
                startActivity(i);
            }
        });
        buttonContactUs.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    buttonContactUs.setBackgroundColor(Color.parseColor("#E3B926"));
                    buttonContactUs.setTextColor(Color.parseColor("#3C3C3E"));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    buttonContactUs.setBackgroundColor(Color.parseColor("#3C3C3E"));
                    buttonContactUs.setTextColor(Color.parseColor("#FFFFFF"));
                }
                return false;
            }
        });
    }
}
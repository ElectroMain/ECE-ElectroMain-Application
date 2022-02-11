package com.example.mainelectro.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.mainelectro.R;

public class MainActivity extends AppCompatActivity {


    private ConstraintLayout constraintLayout;
    private AppCompatButton buttonDeuxCommandes;
    private AppCompatButton buttonQuatreCommandes;
    private AppCompatButton buttonCinqCommandes;
    private AppCompatButton buttonHuitCommandes;
    private  AppCompatButton buttonHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.mainLayout);
        buttonDeuxCommandes = findViewById(R.id.buttonModeDeuxCommandes);
        buttonQuatreCommandes = findViewById(R.id.buttonModeQuatreCommandes);
        buttonCinqCommandes = findViewById(R.id.buttonModeCinqCommandes);
        buttonHuitCommandes = findViewById(R.id.buttonModeHuitCommandes);
        buttonHelp = findViewById(R.id.buttonHelp);

        AnimationDrawable animationDrawble = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawble.setEnterFadeDuration(2500);
        animationDrawble.setExitFadeDuration(5000);
        animationDrawble.start();

        buttonHelp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent helpActivity = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(helpActivity);
            }
        });
        buttonHelp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    buttonHelp.setBackgroundColor(Color.parseColor("#6FB1E0"));
                    buttonHelp.setTextColor(Color.parseColor("#F8F1E7"));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    buttonHelp.setBackgroundColor(Color.parseColor("#E3B926"));
                    buttonHelp.setTextColor(Color.BLACK);
                }
                return false;
            }
        });
        buttonCinqCommandes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent JoystickActivity = new Intent(MainActivity.this, JoystickModeCinqCommandesActivity.class);
                startActivity(JoystickActivity);
            }
        });
        buttonCinqCommandes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    buttonCinqCommandes.setBackgroundColor(Color.parseColor("#6FB1E0"));
                    buttonCinqCommandes.setTextColor(Color.parseColor("#F8F1E7"));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    buttonCinqCommandes.setBackgroundColor(Color.parseColor("#F8F1E7"));
                    buttonCinqCommandes.setTextColor(Color.BLACK);
                }
                return false;
            }
        });
        buttonQuatreCommandes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent JoystickActivity = new Intent(MainActivity.this, JoystickModeQuatreCommandesActivity.class);
                startActivity(JoystickActivity);
            }
        });
        buttonQuatreCommandes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    buttonQuatreCommandes.setBackgroundColor(Color.parseColor("#6FB1E0"));
                    buttonQuatreCommandes.setTextColor(Color.parseColor("#F8F1E7"));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    buttonQuatreCommandes.setBackgroundColor(Color.parseColor("#F8F1E7"));
                    buttonQuatreCommandes.setTextColor(Color.BLACK);
                }
                return false;
            }
        });
        buttonDeuxCommandes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent JoystickActivity = new Intent(MainActivity.this, JoystickModeDeuxCommandesActivity.class);
                startActivity(JoystickActivity);
            }
        });
        buttonDeuxCommandes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    buttonDeuxCommandes.setBackgroundColor(Color.parseColor("#6FB1E0"));
                    buttonDeuxCommandes.setTextColor(Color.parseColor("#F8F1E7"));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    buttonDeuxCommandes.setBackgroundColor(Color.parseColor("#F8F1E7"));
                    buttonDeuxCommandes.setTextColor(Color.BLACK);
                }
                return false;
            }
        });
        buttonHuitCommandes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent JoystickActivity = new Intent(MainActivity.this, JoystickModeHuitCommandesActivity.class);
                startActivity(JoystickActivity);
            }
        });
        buttonHuitCommandes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    buttonHuitCommandes.setBackgroundColor(Color.parseColor("#6FB1E0"));
                    buttonHuitCommandes.setTextColor(Color.parseColor("#F8F1E7"));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    buttonHuitCommandes.setBackgroundColor(Color.parseColor("#F8F1E7"));
                    buttonHuitCommandes.setTextColor(Color.BLACK);
                }
                return false;
            }
        });

    }
}
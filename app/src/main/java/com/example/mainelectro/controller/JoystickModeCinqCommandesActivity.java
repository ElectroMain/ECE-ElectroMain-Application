package com.example.mainelectro.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.mainelectro.Model.BluetoothConnexion;
import com.example.mainelectro.R;

import java.util.UUID;


public class JoystickModeCinqCommandesActivity extends AppCompatActivity {

    private AppCompatButton buttonHome;
    private AppCompatButton buttonBluetoothSettings;
    private Switch switchModeLego3d;
    private AppCompatButton buttonPositionForward;
    private AppCompatButton buttonPositionBackward;
    private AppCompatButton buttonPositionRight;
    private AppCompatButton buttonPositionLeft;
    private AppCompatButton buttonPositionStop;
    private TextView textViewDeviceName;
    private TextView textViewSendValue;

    private static final int BLUETOOTH_CONNEXION_REQUEST_CODE = 12;

    private BluetoothConnexion bluetoothClient = null;
    private BluetoothDevice bTDevice = null;
    private String address;
    private UUID uUID;
    private int code = 0;
    //entier soustrait au code envoyé pour avoir la valeur demandée
    //ex: j'envoie '4' ==> code == 52
    //raison ==> inconnue
    private int correctionCode = 48;
    private boolean isModeLego3DChecked = false;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (BLUETOOTH_CONNEXION_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            bTDevice = (BluetoothDevice) data.getExtras().get(Bluetooth1Activity.BUNDLE_EXTRA_BLUETOOTH_DEVICE);
            address = bTDevice.getAddress();
            uUID = bTDevice.getUuids()[0].getUuid();
            System.out.println("UUID  :");
            System.out.println(uUID);
            bluetoothClient = new BluetoothConnexion( address, uUID );
            textViewDeviceName.setText( "Connected to " + bTDevice.getName() );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick_mode_cinq_commandes);

        buttonHome = findViewById(R.id.buttonHome);
        buttonBluetoothSettings = findViewById(R.id.buttonBluetoothSettings);
        switchModeLego3d = findViewById(R.id.switchModeLego3D);
        buttonPositionForward = findViewById(R.id.buttonPositionForward);
        buttonPositionBackward = findViewById(R.id.buttonPositionBackward);
        buttonPositionRight = findViewById(R.id.buttonPositionRight);
        buttonPositionLeft = findViewById(R.id.buttonPositionLeft);
        buttonPositionStop = findViewById(R.id.buttonPositionStop);
        textViewDeviceName = findViewById(R.id.textViewDeviceName);
        textViewSendValue = findViewById(R.id.textViewSendValue);

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                if(bluetoothClient!=null){
                    bluetoothClient.cancel();
                }
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
        buttonBluetoothSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bluetoothActivity = new Intent(JoystickModeCinqCommandesActivity.this, Bluetooth1Activity.class);
                startActivityForResult(bluetoothActivity, BLUETOOTH_CONNEXION_REQUEST_CODE);
            }
        });
        buttonBluetoothSettings.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    buttonBluetoothSettings.setBackgroundColor(Color.parseColor("#E3B926"));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    buttonBluetoothSettings.setBackgroundColor(Color.parseColor("#DCD1D1"));
                }
                return false;
            }
        });
        switchModeLego3d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    isModeLego3DChecked = true;
                    switchModeLego3d.setText("Mode Lego");
                }else {
                    isModeLego3DChecked = false;
                    switchModeLego3d.setText("Mode 3D");
                }
            }
        });
        buttonPositionStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( bluetoothClient == null ) return;
                System.out.println("code");
                code = '0';
                bluetoothClient.writeInt( code );
                textViewSendValue.setText(String.valueOf(code-correctionCode));
            }
        });
        buttonPositionForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( bluetoothClient == null ) return;
                if(isModeLego3DChecked){
                    code = '2';
                }else{
                    code = '1';
                }
                bluetoothClient.writeInt( code );
                textViewSendValue.setText(String.valueOf(code-correctionCode));
            }
        });
        buttonPositionBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( bluetoothClient == null ) return;
                if(isModeLego3DChecked){
                    code = '1';
                }else{
                    code = '2';
                }
                bluetoothClient.writeInt( code );
                textViewSendValue.setText(String.valueOf(code-correctionCode));
            }
        });
        buttonPositionLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( bluetoothClient == null ) return;
                code = '3';
                bluetoothClient.writeInt( code );
                textViewSendValue.setText(String.valueOf(code-correctionCode));
            }
        });
        buttonPositionRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( bluetoothClient == null ) return;
                code = '4';
                bluetoothClient.writeInt( code );
                textViewSendValue.setText(String.valueOf(code-correctionCode));
            }
        });
    }
}
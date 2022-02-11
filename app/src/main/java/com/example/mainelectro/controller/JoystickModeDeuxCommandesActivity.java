package com.example.mainelectro.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.bluetooth.BluetoothAdapter;
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

public class JoystickModeDeuxCommandesActivity extends AppCompatActivity {

    private AppCompatButton buttonHome;
    private AppCompatButton buttonBluetoothSettings;
    private Switch switchModeLego3d;
    private AppCompatButton buttonPositionForward;
    private AppCompatButton buttonPositionTurn;
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

    //Récupération du startActivityForResult ==> reçoi le BluetoothDevice sélectionné
    //appel connexion Bluetooth
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (BLUETOOTH_CONNEXION_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            bTDevice = (BluetoothDevice) data.getExtras().get(Bluetooth1Activity.BUNDLE_EXTRA_BLUETOOTH_DEVICE);
            address = bTDevice.getAddress();
            uUID = bTDevice.getUuids()[0].getUuid();
            bluetoothClient = new BluetoothConnexion( address, uUID );
            textViewDeviceName.setText( "Connected to " + bTDevice.getName() );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick_mode_deux_commandes);

        buttonHome = findViewById(R.id.buttonHome);
        buttonBluetoothSettings = findViewById(R.id.buttonBluetoothSettings);
        switchModeLego3d = findViewById(R.id.switchModeLego3D);
        buttonPositionForward = findViewById(R.id.buttonPositionForward);
        buttonPositionTurn = findViewById(R.id.buttonPositionTurn);
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
                Intent bluetoothActivity = new Intent(JoystickModeDeuxCommandesActivity.this, Bluetooth1Activity.class);
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
        buttonPositionForward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    buttonPositionForward.setBackgroundColor(Color.parseColor("#E3B926"));
                    if ( bluetoothClient == null ) return false;
                    if(isModeLego3DChecked){
                        code = '2';
                    }else{
                        code = '1';
                    }
                    bluetoothClient.writeInt( code );
                    textViewSendValue.setText(String.valueOf(code-correctionCode));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    buttonPositionForward.setBackgroundColor(Color.parseColor("#6FB1E0"));
                    if ( bluetoothClient == null ) return false;
                    code = '0';
                    bluetoothClient.writeInt( code );
                    textViewSendValue.setText(String.valueOf(code-correctionCode));
                }
                return true;
            }
        });
        buttonPositionTurn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    buttonPositionTurn.setBackgroundColor(Color.parseColor("#E3B926"));
                    if ( bluetoothClient == null ) return false;
                    code = '4';
                    System.out.println(code);
                    bluetoothClient.writeInt( code );
                    textViewSendValue.setText(String.valueOf(code-correctionCode));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    buttonPositionTurn.setBackgroundColor(Color.parseColor("#6FB1E0"));
                    if ( bluetoothClient == null ) return false;
                    code = '0';
                    bluetoothClient.writeInt( code );
                    textViewSendValue.setText(String.valueOf(code-correctionCode));
                }
                return true;
            }
        });
    }
}
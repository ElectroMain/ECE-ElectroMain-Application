package com.example.mainelectro.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mainelectro.R;

import java.util.ArrayList;
import java.util.Set;

public class Bluetooth1Activity extends AppCompatActivity {
    private Switch switchBluetooth;
    private Button buttonSearchDevices;
    private Button buttonBack;
    private ListView deviceList;
    private TextView textViewDeviceName;

    public static int REQUEST_ENABLE_BT = 1;
    public static final String BUNDLE_EXTRA_BLUETOOTH_DEVICE = "BUNDLE_EXTRA_BLUETOOTH_DEVICE";

    private BluetoothAdapter bluetoothAdapter = null;
    private ArrayList<BluetoothDevice> knownDevices = null;
    private TextView lblConnectedDevice;
    private String deviceUUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth1);

        switchBluetooth = findViewById(R.id.switchBluetooth);
        buttonSearchDevices = findViewById(R.id.buttonSearchDevices);
        buttonBack = findViewById(R.id.buttonBack);
        deviceList = findViewById(R.id.deviceList);
        lblConnectedDevice = findViewById(R.id.textViewDeviceName);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        textViewDeviceName = findViewById(R.id.textViewDeviceName);

        //Bouton Back, retour à l'activité de commande
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttonBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    buttonBack.setBackgroundColor(Color.parseColor("#E3B926"));
                    buttonBack.setTextColor(Color.parseColor("#3C3C3E"));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    buttonBack.setBackgroundColor(Color.parseColor("#3C3C3E"));
                    buttonBack.setTextColor(Color.parseColor("#FFFFFF"));
                }
                return false;
            }
        });

        // set le switch en fonction de l'état du bluetooth
        if(bluetoothAdapter.isEnabled() == true){
            switchBluetooth.setChecked(true);
        }
        // ON-OFF bluetooth
        switchBluetooth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (bluetoothAdapter == null) {
                    // Device doesn't support Bluetooth
                }
                else{
                    if(isChecked){
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                        switchBluetooth.setChecked(true);
                        deviceList.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "Bluetooth Turned ON", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        bluetoothAdapter.disable();
                        switchBluetooth.setChecked(false);
                        deviceList.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(),"Bluetooth Turned OFF", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });
        //if click sur un élément de la list try connexion Bluetooth
        deviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long rowId) {
                System.out.println("onItemClick");
                ArrayList<BluetoothDevice> knownDevices = new ArrayList<>(bluetoothAdapter.getBondedDevices());
                BluetoothDevice device = knownDevices.get( (int) rowId );
                textViewDeviceName.setText(device.getName());
                lblConnectedDevice.setText( "Connexion to " + device.getName() + "...");
                String address = ((TextView) view).getText().toString();
                System.out.println(address);
                deviceUUID = device.getUuids()[0].getUuid().toString();
                Intent intent = new Intent();
                intent.putExtra(BUNDLE_EXTRA_BLUETOOTH_DEVICE, device);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        //Affichage de la liste des devices appairés et paramétrage du style
        buttonSearchDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bluetoothAdapter.isEnabled()){
                    Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
                    if (pairedDevices.size() > 0) {
                        ArrayList<BluetoothDevice> knownDevices = new ArrayList<>(bluetoothAdapter.getBondedDevices());
                        ArrayAdapter<BluetoothDevice> adapter = new ArrayAdapter<BluetoothDevice>(Bluetooth1Activity.this, android.R.layout.simple_list_item_1, knownDevices){
                            public View getView(int position, View convertView, ViewGroup parent){
                                // Get the Item from ListView
                                View view = super.getView(position, convertView, parent);
                                view.setBackgroundColor(Color.WHITE);
                                // Initialize a TextView for ListView each Item
                                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                                // Set the text color of TextView (ListView Item)
                                tv.setTextColor(Color.BLACK);
                                tv.setText(getItem(position).getName());
                                // Generate ListView Item using TextView
                                return view;
                            }
                        };
                        deviceList.setAdapter(adapter);
                    }
                }
            }
        });
        //changement de couleur du bouton quand on le touche
        buttonSearchDevices.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    buttonSearchDevices.setBackgroundColor(Color.parseColor("#E3B926"));
                    buttonSearchDevices.setTextColor(Color.parseColor("#F8F1E7"));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    buttonSearchDevices.setBackgroundColor(Color.parseColor("#6FB1E0"));
                    buttonSearchDevices.setTextColor(Color.BLACK);
                }
                return false;
            }
        });
    }
}

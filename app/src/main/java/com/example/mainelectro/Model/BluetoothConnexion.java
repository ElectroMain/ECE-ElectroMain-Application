package com.example.mainelectro.Model;

import static android.content.ContentValues.TAG;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

public class BluetoothConnexion {
    private BluetoothAdapter myBluetooth = null;
    private BluetoothSocket btSocket = null;
    static UUID myUUID = null;

    //Reçoi une adresse un un UUID puis ouvre une connexion Bluetooth
    public BluetoothConnexion(String address, UUID uUID){
        try {
            if (btSocket == null) {
                myBluetooth = BluetoothAdapter.getDefaultAdapter();
                myUUID =  uUID;
                BluetoothDevice hc = myBluetooth.getRemoteDevice(address);
                btSocket = hc.createInsecureRfcommSocketToServiceRecord(myUUID);
                btSocket.connect();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Envoi le code par Bluetooth
    public void writeInt(int code){
        if (btSocket != null) {
            try {
                btSocket.getOutputStream().write(code);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Fermeture de la connexion Bluetooth
    public void cancel() {
        try {
            btSocket.close();
        } catch (IOException e) {
            Log.e(TAG, "Could not close the connect socket", e);
        }
    }
}


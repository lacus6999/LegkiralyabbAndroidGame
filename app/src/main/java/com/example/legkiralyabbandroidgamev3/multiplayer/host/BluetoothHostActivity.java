package com.example.legkiralyabbandroidgamev3.multiplayer.host;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.legkiralyabbandroidgamev3.R;

import java.io.IOException;
import java.util.UUID;

public class BluetoothHostActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    private AcceptThread acceptThread;

    public static BluetoothHostActivity instanceOfActivity;
    public BluetoothSocket socket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_host);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        init();
    }

    private void init() {
        if (bluetoothAdapter != null) {
            if (bluetoothAdapter.isEnabled()) {
                BluetoothDevice bluetoothDevice = bluetoothAdapter.getRemoteDevice(bluetoothAdapter.getAddress());
                acceptThread = new AcceptThread(bluetoothDevice.getName());
                acceptThread.start();
            }
        }
    }

    private class AcceptThread extends Thread {
        private final BluetoothServerSocket mmServerSocket;

        public AcceptThread(String name) {
            BluetoothServerSocket tmp = null;
            try {
                tmp = bluetoothAdapter.listenUsingRfcommWithServiceRecord(name, UUID.fromString("23b94798-106e-11eb-adc1-0242ac120002"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            mmServerSocket = tmp;
        }

        public void run() {
            BluetoothSocket socket = null;
            while (true) {
                try {
                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }

                if (socket != null) {
                    manageMyConnectedSocket(socket);
                    try {
                        mmServerSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void manageMyConnectedSocket(BluetoothSocket socket) {

        this.socket = socket;
        instanceOfActivity = this;

        startActivity(new Intent(BluetoothHostActivity.this, HostGameActivity.class));
    }

}
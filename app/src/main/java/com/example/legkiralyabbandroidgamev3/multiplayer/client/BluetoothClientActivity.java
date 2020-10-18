package com.example.legkiralyabbandroidgamev3.multiplayer.client;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.legkiralyabbandroidgamev3.R;
import com.example.legkiralyabbandroidgamev3.multiplayer.BluetoothTextView;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class BluetoothClientActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    private ConnectThread connectThread;

    public static BluetoothClientActivity instanceOfActivity;
    public BluetoothSocket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_multiplayer_join);

        init();
    }

    private void init() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        TextView textView = findViewById(R.id.paired_device);
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        if (!pairedDevices.isEmpty()) {
            LinearLayout bluetooth_available_devices = findViewById(R.id.bluetooth_available_devices);
            for (BluetoothDevice bluetoothDevice : pairedDevices) {
                bluetooth_available_devices.addView(getTextView(bluetoothDevice.getName(), bluetoothDevice.getAddress()));
            }
        } else {
            textView.setText("No paired device. Pair a device first, then come back.");
        }
    }

    private TextView getTextView(final String name, String address) {
        final BluetoothTextView textView = new BluetoothTextView(this, name, address);
        textView.setText(name);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(20, 20, 20, 20);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothTextView clickedView = (BluetoothTextView) v;
                connectThread = new ConnectThread(bluetoothAdapter.getRemoteDevice(clickedView.getMacAddress()));
                connectThread.start();
            }
        });
        return textView;
    }


    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            BluetoothSocket tmp = null;
            mmDevice = device;

            try {
                tmp = device.createRfcommSocketToServiceRecord(UUID.fromString("23b94798-106e-11eb-adc1-0242ac120002"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            mmSocket = tmp;
        }

        public void run() {
            bluetoothAdapter.cancelDiscovery();

            try {
                mmSocket.connect();
            } catch (IOException connectException) {
                try {
                    mmSocket.close();
                } catch (IOException closeException) {
                    closeException.printStackTrace();
                }
                return;
            }

            manageMyConnectedSocket(mmSocket);
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void manageMyConnectedSocket(BluetoothSocket socket) {
        this.socket = socket;
        instanceOfActivity = this;

        startActivity(new Intent(BluetoothClientActivity.this, ClientGameActivity.class));
    }


}
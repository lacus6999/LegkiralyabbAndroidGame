package com.example.legkiralyabbandroidgamev3.multiplayer;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.legkiralyabbandroidgamev3.R;
import com.example.legkiralyabbandroidgamev3.game.GameActivity;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class BluetoothJoinActivity extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter;
    private ConnectThread connectThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_multiplayer_join);

        init();
    }

    private void init() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null) {
            if (bluetoothAdapter.isEnabled()) {
                Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
                if (!pairedDevices.isEmpty()) {
                    LinearLayout bluetooth_available_devices = findViewById(R.id.bluetooth_available_devices);

                    for (BluetoothDevice bluetoothDevice : pairedDevices) {
                        bluetooth_available_devices.addView(getTextView(bluetoothDevice.getName(), bluetoothDevice.getAddress()));
                    }
                } else {
                    TextView textView = findViewById(R.id.paired_device);
                    textView.setText("No paired device.");
                }
            }
            //setTextView();
        } else {
            //setTextView("Bluetooth is not accessible on your device.");
        }
    }

    private TextView getTextView(final String name, String address) {
        final BluetoothTextView textView = new BluetoothTextView(this, name, address);
        textView.setText(name);
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
        connectThread.cancel();
        startActivity(new Intent(BluetoothJoinActivity.this, GameActivity.class));
    }


}
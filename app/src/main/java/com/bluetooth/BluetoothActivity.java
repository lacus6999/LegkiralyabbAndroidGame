package com.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;

import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bluetooth);

        init();
    }

    private void init() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter != null){
            if(bluetoothAdapter.isEnabled()){
                Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();


                LinearLayout bluetoothAvailableDevices = findViewById(R.id.bluetooth_available_devices);
                TextView textView = new TextView(this);
                textView.setText("kacsa");
                bluetoothAvailableDevices.addView(textView);
            }
        }


    }
}
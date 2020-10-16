package com.example.legkiralyabbandroidgamev3.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.legkiralyabbandroidgamev3.R;
import com.example.legkiralyabbandroidgamev3.game.GameActivity;

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
        if (bluetoothAdapter != null) {
            if (bluetoothAdapter.isEnabled()) {
                Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
                if (!pairedDevices.isEmpty()) {
                    LinearLayout bluetooth_available_devices = findViewById(R.id.bluetooth_available_devices);

                    for(BluetoothDevice bluetoothDevice : pairedDevices) {
                            bluetooth_available_devices.addView(getTextView(bluetoothDevice.getName(), bluetoothDevice.getAddress()));
                    }

                    final Button button = findViewById(R.id.bluetooth_continue);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            startActivity(new Intent(BluetoothActivity.this, GameActivity.class));
                        }
                    });
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
                System.out.println(textView.getName());
            }
        });
        return textView;
    }

}
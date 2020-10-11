package com.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.MainActivity;
import com.example.game.R;
import com.game.GameActivity;

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
                if(!pairedDevices.isEmpty()){
                    TextView textView = findViewById(R.id.paired_device);
                    BluetoothDevice bluetoothDevice = pairedDevices.iterator().next();
                    textView.setText(bluetoothDevice.getName());

                    final Button button = findViewById(R.id.bluetooth_continue);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            startActivity(new Intent(BluetoothActivity.this, GameActivity.class));
                        }
                    });
                }
                else {
                    TextView textView = findViewById(R.id.paired_device);
                    textView.setText("No paired device.");
                }
            }
            //setTextView();
        } else {
            //setTextView("Bluetooth is not accessible on your device.");
        }
    }
}
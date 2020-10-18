package com.example.legkiralyabbandroidgamev3;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.legkiralyabbandroidgamev3.multiplayer.MultiplayerGameActivity;
import com.example.legkiralyabbandroidgamev3.singleplayer.GameActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.play_singleplayer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });
        findViewById(R.id.play_multiplayer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if(bluetoothAdapter.isEnabled())
                    startActivity(new Intent(MainActivity.this, MultiplayerGameActivity.class));
                else {
                    sendToast("Turn on Bluetooth first!");
                }
            }
        });
    }

    private void sendToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

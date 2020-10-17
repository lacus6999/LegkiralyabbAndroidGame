package com.example.legkiralyabbandroidgamev3.multiplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.legkiralyabbandroidgamev3.R;
import com.example.legkiralyabbandroidgamev3.multiplayer.client.BluetoothClientActivity;
import com.example.legkiralyabbandroidgamev3.multiplayer.host.BluetoothHostActivity;

public class MultiplayerGameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);

        findViewById(R.id.multiplayer_textview_host).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MultiplayerGameActivity.this, BluetoothHostActivity.class));
            }
        });
        findViewById(R.id.multiplayer_textview_join).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MultiplayerGameActivity.this, BluetoothClientActivity.class));
            }
        });

    }
}
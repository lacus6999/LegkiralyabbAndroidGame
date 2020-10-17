package com.example.legkiralyabbandroidgamev3.multiplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.legkiralyabbandroidgamev3.BluetoothHostActivity;
import com.example.legkiralyabbandroidgamev3.R;

public class MultiPlayerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);

        findViewById(R.id.multiplayer_textview_host).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MultiPlayerActivity.this, BluetoothHostActivity.class));
            }
        });
        findViewById(R.id.multiplayer_textview_join).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MultiPlayerActivity.this, BluetoothJoinActivity.class));
            }
        });

    }
}
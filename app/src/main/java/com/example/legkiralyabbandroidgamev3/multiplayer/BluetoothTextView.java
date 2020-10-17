package com.example.legkiralyabbandroidgamev3.multiplayer;

import android.content.Context;

import androidx.appcompat.widget.AppCompatTextView;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class BluetoothTextView extends AppCompatTextView {
    public BluetoothTextView(Context context, String name, String macAddress) {
        super(context);

        this.name = name;
        this.macAddress = macAddress;
    }

    private String name;
    private String macAddress;

}

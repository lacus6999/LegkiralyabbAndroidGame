package com.bluetooth;

import android.content.Context;

import androidx.appcompat.widget.AppCompatTextView;

public class BluetoothTextView extends AppCompatTextView {
    public BluetoothTextView(Context context, String name, String macAddress) {
        super(context);

        this.name = name;
        this.macAddress = macAddress;
    }

    private String name;
    private String macAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}

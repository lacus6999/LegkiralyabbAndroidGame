package com.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.game.R;


public class Tile {

    private int id;
    private int x;
    private int y;
    private Bitmap image;

    Tile(int id, int x, int y, Resources res) {
        this.id = id;
        this.x = x;
        this.y = y;
        image = BitmapFactory.decodeResource(res, R.drawable.tile);
        image = Bitmap.createScaledBitmap(image, 30, 30, false);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Bitmap getImage() {
        return image;
    }
}

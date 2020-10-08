package com.game;

import android.graphics.Bitmap;


public class Tile {

    private int id;
    private int x;
    private int y;
    private final int tileSize;
    private Bitmap shownImage;
    private Bitmap backSideImage;
    private Bitmap image;
    public boolean isFlipped = false;

    Tile(int id, int tileSize) {
        this.id = id;
        this.tileSize = tileSize;
        image = Bitmap.createBitmap(tileSize, tileSize, Bitmap.Config.ARGB_8888);
        image.eraseColor(android.graphics.Color.GREEN);
    }

    public void flip() {
        if (shownImage.equals(image)) {
            isFlipped = false;
            shownImage = backSideImage;
        }
        else {
            isFlipped = true;
            shownImage = image;
        }

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

    public void setBackSideImage(Bitmap backSideImage) {
        this.backSideImage = backSideImage;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Bitmap getShownImage() {
        return shownImage;
    }

    public void setShownImage(Bitmap backSideImage) {
        this.shownImage = backSideImage;
    }

}

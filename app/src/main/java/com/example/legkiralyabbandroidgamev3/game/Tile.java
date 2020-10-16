package com.example.legkiralyabbandroidgamev3.game;

import android.graphics.Bitmap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tile {

    private int id;
    private int x;
    private int y;
    private final int tileSize;
    private Bitmap shownImage;
    private Bitmap backSideImage;
    private Bitmap image;
    public boolean isFlipped = false;

    Tile(int id, int tileSize, Bitmap frontSideImage) {
        this.id = id;
        this.tileSize = tileSize;
        this.image = frontSideImage;
//        image = Bitmap.createBitmap(tileSize, tileSize, Bitmap.Config.ARGB_8888);
//        image.eraseColor(android.graphics.Color.GREEN);
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

}

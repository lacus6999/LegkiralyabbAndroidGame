package com.example.legkiralyabbandroidgamev3.gamefiles;

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

    public Tile(int id, int tileSize, Bitmap frontSideImage) {
        this.id = id;
        this.tileSize = tileSize;
        this.image = frontSideImage;
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

package com.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.game.R;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Images {

    private List<Bitmap> cardImages;
    private Bitmap backgroundImage;
    private Bitmap backSideImage;
    private int tileSize = 150;

    public Images(Resources res) {
        backgroundImage = getDecodedResource(res, R.drawable.background1, 831, 1280);
        backSideImage = getDecodedResource(res, R.drawable.card_background, 800, tileSize);
        cardImages = Arrays.asList(
                getDecodedResource(res, R.drawable.img_01, 800, tileSize),
                getDecodedResource(res, R.drawable.img_02, 800, tileSize),
                getDecodedResource(res, R.drawable.img_03, 800, tileSize),
                getDecodedResource(res, R.drawable.img_04, 800, tileSize),
                getDecodedResource(res, R.drawable.img_05, 800, tileSize),
                getDecodedResource(res, R.drawable.img_06, 800, tileSize),
                getDecodedResource(res, R.drawable.img_07, 800, tileSize),
                getDecodedResource(res, R.drawable.img_08, 800, tileSize),
                getDecodedResource(res, R.drawable.img_09, 800, tileSize),
                getDecodedResource(res, R.drawable.img_10, 800, tileSize)
        );

    }

    private Bitmap getDecodedResource(Resources res, int img, int originalWidth, int width) {
        return BitmapFactory.decodeResource(res, img, getBitmapOptions(originalWidth, width));
    }

    public BitmapFactory.Options getBitmapOptions(int srcWidth, int targetWidth) {
        BitmapFactory.Options mBitmapOptions = new BitmapFactory.Options();
        mBitmapOptions.inScaled = true;
        mBitmapOptions.inSampleSize = 4;
        mBitmapOptions.inDensity = srcWidth;
        mBitmapOptions.inTargetDensity = targetWidth * mBitmapOptions.inSampleSize;
        return mBitmapOptions;
    }

}

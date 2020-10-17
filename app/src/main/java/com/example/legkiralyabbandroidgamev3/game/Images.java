package com.example.legkiralyabbandroidgamev3.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.example.legkiralyabbandroidgamev3.R;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Images {

    private List<Bitmap> cardImagesPeople;
    private List<Bitmap> cardImagesScience;
    private List<Bitmap> cardImagesEveryday;
    private List<Bitmap> cardImagesAnimal;

    private Bitmap backgroundImage;
    private Bitmap backSideImage;
    private int tileSize = 150;

    public Images(Resources res) {
        backgroundImage = getDecodedResource(res, R.drawable.background1, 831, 1280);
        backSideImage = getDecodedResource(res, R.drawable.card_background, 800, tileSize);
        cardImagesEveryday = Arrays.asList(
                getDecodedResource(res, R.drawable.img_01, 800, tileSize),
                getDecodedResource(res, R.drawable.img_02, 800, tileSize),
                getDecodedResource(res, R.drawable.img_03, 800, tileSize),
                getDecodedResource(res, R.drawable.img_04, 800, tileSize),
                getDecodedResource(res, R.drawable.img_05, 800, tileSize),
                getDecodedResource(res, R.drawable.img_06, 800, tileSize),
                getDecodedResource(res, R.drawable.img_07, 800, tileSize),
                getDecodedResource(res, R.drawable.img_08, 800, tileSize),
                getDecodedResource(res, R.drawable.img_09, 800, tileSize),
                getDecodedResource(res, R.drawable.img_10, 800, tileSize),
                getDecodedResource(res, R.drawable.img_11, 800, tileSize),
                getDecodedResource(res, R.drawable.img_12, 800, tileSize),
                getDecodedResource(res, R.drawable.img_13, 800, tileSize),
                getDecodedResource(res, R.drawable.img_14, 800, tileSize),
                getDecodedResource(res, R.drawable.img_15, 800, tileSize),
                getDecodedResource(res, R.drawable.img_16, 800, tileSize),
                getDecodedResource(res, R.drawable.img_17, 800, tileSize),
                getDecodedResource(res, R.drawable.img_18, 800, tileSize),
                getDecodedResource(res, R.drawable.img_19, 800, tileSize),
                getDecodedResource(res, R.drawable.img_20, 800, tileSize)

        );

        cardImagesPeople = Arrays.asList(
                getDecodedResource(res, R.drawable.people_01, 800, tileSize),
                getDecodedResource(res, R.drawable.people_02, 800, tileSize),
                getDecodedResource(res, R.drawable.people_03, 800, tileSize),
                getDecodedResource(res, R.drawable.people_04, 800, tileSize),
                getDecodedResource(res, R.drawable.people_05, 800, tileSize),
                getDecodedResource(res, R.drawable.people_06, 800, tileSize),
                getDecodedResource(res, R.drawable.people_07, 800, tileSize),
                getDecodedResource(res, R.drawable.people_08, 800, tileSize),
                getDecodedResource(res, R.drawable.people_09, 800, tileSize),
                getDecodedResource(res, R.drawable.people_10, 800, tileSize),
                getDecodedResource(res, R.drawable.people_12, 800, tileSize),
                getDecodedResource(res, R.drawable.people_13, 800, tileSize),
                getDecodedResource(res, R.drawable.people_14, 800, tileSize),
                getDecodedResource(res, R.drawable.people_15, 800, tileSize),
                getDecodedResource(res, R.drawable.people_16, 800, tileSize),
                getDecodedResource(res, R.drawable.people_17, 800, tileSize)

        );

        cardImagesAnimal = Arrays.asList(
                getDecodedResource(res, R.drawable.ani_01, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_02, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_03, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_04, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_05, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_06, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_07, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_08, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_09, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_10, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_11, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_12, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_13, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_14, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_15, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_16, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_17, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_18, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_19, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_20, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_21, 800, tileSize),
                getDecodedResource(res, R.drawable.ani_22, 800, tileSize)


        );

        cardImagesScience = Arrays.asList(
                getDecodedResource(res, R.drawable.sci_01, 800, tileSize),
                getDecodedResource(res, R.drawable.sci_02, 800, tileSize),
                getDecodedResource(res, R.drawable.sci_03, 800, tileSize),
                getDecodedResource(res, R.drawable.sci_04, 800, tileSize),
                getDecodedResource(res, R.drawable.sci_05, 800, tileSize),
                getDecodedResource(res, R.drawable.sci_06, 800, tileSize),
                getDecodedResource(res, R.drawable.sci_07, 800, tileSize),
                getDecodedResource(res, R.drawable.sci_08, 800, tileSize),
                getDecodedResource(res, R.drawable.sci_09, 800, tileSize),
                getDecodedResource(res, R.drawable.sci_10, 800, tileSize),
                getDecodedResource(res, R.drawable.sci_11, 800, tileSize),
                getDecodedResource(res, R.drawable.sci_12, 800, tileSize),
                getDecodedResource(res, R.drawable.sci_13, 800, tileSize),
                getDecodedResource(res, R.drawable.sci_14, 800, tileSize),
                getDecodedResource(res, R.drawable.sci_15, 800, tileSize),
                getDecodedResource(res, R.drawable.sci_16, 800, tileSize)

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

package com.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.game.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;

    private boolean isPlaying;
    private List<Tile> tiles;
    private Paint paint;

    private int tileAmount = 15;
    private Bitmap background;
    private Bitmap backSideImage;
    private int tileSize = 150;

    private List<Tile> tilePair = new ArrayList<>();

    public GameView(Context context) {
        super(context);

        tiles = new ArrayList<>();
        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);
        setupTiles();
        setupBackground();

    }

    private void setupBackground() {
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background, getBitmapOptions(3264, 1280));//Bitmap.createBitmap(1280, 1920, Bitmap.Config.ARGB_8888);
    }

    private void setupTiles() {
        for (int i = 0; i < tileAmount; i++) {
            tiles.add(new Tile(i, tileSize));
            tiles.add(new Tile(i, tileSize));
        }
        backSideImage = BitmapFactory.decodeResource(getResources(), R.drawable.tile, getBitmapOptions(512, tileSize));

        for (Tile tile : tiles) {
            tile.setBackSideImage(backSideImage);
            tile.setShownImage(backSideImage);
        }

        Collections.shuffle(tiles);

        int rowCount = 1;
        int iterator = 1;

        int distance = tileSize + 10;

        for (int i = 0; i < tiles.size(); i++) {

            if (iterator * distance + tileSize >= 1000) {
                iterator = 1;
                rowCount++;
            }
            tiles.get(i).setX(iterator * distance);
            tiles.get(i).setY(rowCount * distance);
            iterator++;
        }
    }

    private BitmapFactory.Options getBitmapOptions(int srcWidth, int targetWidth) {
        BitmapFactory.Options mBitmapOptions = new BitmapFactory.Options();
        mBitmapOptions.inScaled = true;
        mBitmapOptions.inSampleSize = 4;
        mBitmapOptions.inDensity = srcWidth;
        mBitmapOptions.inTargetDensity = targetWidth * mBitmapOptions.inSampleSize;
        return mBitmapOptions;
    }


    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep(17);
        }
    }

    private void update() {
        if (tilePair.size() == 2) {
            if (tilePair.get(0).getId() == tilePair.get(1).getId()) {
//TODO score implementation
            } else {
                sleep(500);
                tilePair.get(0).flip();
                tilePair.get(1).flip();
            }
            tilePair.clear();
        }
    }

    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();

            canvas.drawBitmap(background, 0, 0, paint);

            for (Tile tile : tiles) {
                canvas.drawBitmap(tile.getShownImage(), tile.getX(), tile.getY(), paint);
            }
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void sleep(int millis) {
        try {
            thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (action == MotionEvent.ACTION_DOWN) {
            for (Tile tile : tiles) {
                if (x >= tile.getX() && x < (tile.getX() + tile.getShownImage().getWidth())
                        && y >= tile.getY() && y < (tile.getY() + tile.getShownImage().getHeight())) {
                    if (!tile.isFlipped) {
                        tile.flip();
                        tilePair.add(tile);
                    }
                }
            }
        }
        return true;
    }


    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.example.legkiralyabbandroidgamev3.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;

    private boolean isPlaying;
    private List<Tile> tiles;
    private Paint paint;

    private int tileAmount = 10;
    private Bitmap background;
    private int tileSize;

    private Images images;

    private List<Tile> tilePair = new ArrayList<>();

    public GameView(Context context) {
        super(context);

        images = new Images(getResources());
        tileSize = images.getTileSize();
        tiles = new ArrayList<>();
        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);
        setupTiles();
        setupBackground();

    }

    private void setupBackground() {
        background = images.getBackgroundImage();
    }

    private void setupTiles() {
        for (int i = 0; i < tileAmount; i++) {

            tiles.add(new Tile(i, tileSize, images.getCardImagesEveryday().get(i)));
            tiles.add(new Tile(i, tileSize, images.getCardImagesEveryday().get(i)));

            //TODO : ami itt van kell majd

//            if(){
//                tiles.add(new Tile(i, tileSize, images.getCardImagesPeople().get(i)));
//                tiles.add(new Tile(i, tileSize, images.getCardImagesPeople().get(i)));
//            }else if (){
//                tiles.add(new Tile(i, tileSize, images.getCardImagesAnimal().get(i)));
//                tiles.add(new Tile(i, tileSize, images.getCardImagesAnimal().get(i)));
//            }else if(){
//                tiles.add(new Tile(i, tileSize, images.getCardImagesEveryday().get(i)));
//                tiles.add(new Tile(i, tileSize, images.getCardImagesEveryday().get(i)));
//            }else if(){
//                tiles.add(new Tile(i, tileSize, images.getCardImagesScience().get(i)));
//                tiles.add(new Tile(i, tileSize, images.getCardImagesScience().get(i)));
//            }

        }
        for (Tile tile : tiles) {
            tile.setBackSideImage(images.getBackSideImage());
            tile.setShownImage(images.getBackSideImage());
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


    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep(17);
        }
    }

    private void update() {

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
                    if (!tile.isFlipped && tilePair.size() < 2) {
                        tile.flip();
                        tilePair.add(tile);

                        if (tilePair.size() == 2) {
                            handleCards();
                        }
                    }
                }
            }
        }
        return true;
    }

    private void handleCards() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (tilePair.get(0).getId() == tilePair.get(1).getId()) {
                    //TODO SCORE
                } else {
                    tilePair.get(0).flip();
                    tilePair.get(1).flip();
                }
                tilePair.clear();
            }
        }, 1000);
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

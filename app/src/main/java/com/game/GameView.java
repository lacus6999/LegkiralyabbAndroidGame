package com.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying;
    private List<Tile> tiles;
    private Paint paint;
    private Tile tile;

    public GameView(Context context) {
        super(context);

        tiles = new ArrayList<>();
        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);
//        for (int i = 0; i < 15; i++) {
//            Tile tile = new Tile(i, getResources());
//            tile.setId(i);
//            tiles.add(tile);
//            tiles.add(tile);
//        }
        tile = new Tile(1, 0, 0, getResources());

    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep();
        }
    }

    private void update() {

    }

    private void draw() {
        if (getHolder().getSurface().isValid()) {
            System.out.println("asdsadsadsdsdadsadsasdadsadsa");
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(tile.getImage(), tile.getX(), tile.getY(), paint);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void sleep() {
        try {
            thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

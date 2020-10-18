package com.example.legkiralyabbandroidgamev3.multiplayer.client;

import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.widget.Toast;

import com.example.legkiralyabbandroidgamev3.gamefiles.Images;
import com.example.legkiralyabbandroidgamev3.gamefiles.Tile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientGameView extends SurfaceView implements Runnable {

    private Thread thread;
    private Handler handler = new Handler();
    private ConnectedThread connectedThread;

    private boolean isPlaying;
    private List<Tile> tiles;
    private Paint paint;

    private int tileAmount = 10;
    private Bitmap background;
    private int tileSize;
    List<Integer> tileIds = new ArrayList<>();

    private Images images;

    private List<Tile> tilePair = new ArrayList<>();

    private BluetoothSocket joinedSocket;

    public ClientGameView(Context context) {
        super(context);

        joinedSocket = BluetoothClientActivity.instanceOfActivity.socket;
        connectedThread = new ConnectedThread(joinedSocket);
        connectedThread.start();

        images = new Images(getResources());
        tileSize = images.getTileSize();
        tiles = new ArrayList<>();
        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);
        setupBackground();


        if(joinedSocket != null){
            Toast.makeText(getContext(), "IM A CLIENT!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupBackground() {
        background = images.getBackgroundImage();
    }

    private void setupTiles() {

        tileIds.add(1);
        for (int i = 0; i < tileAmount; i++) {

            tiles.add(new Tile(tileIds.get(i), tileSize, images.getCardImagesEveryday().get(i)));

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
                if (tilePair.get(0).getId() == -tilePair.get(1).getId()) {
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

    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private byte[] mmBuffer; // mmBuffer store for the stream

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams; using temp objects because
            // member streams are final.
            try {
                tmpIn = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            mmBuffer = new byte[1024];
            int numBytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs.
            while (true) {
                try {
                    // Read from the InputStream.
                    if(tileIds.size() <= tileAmount * 2) {
                        numBytes = mmInStream.read(mmBuffer);
                        tileIds.add(ByteBuffer.wrap(mmBuffer).getInt());
                        System.out.println("KASCSSACASCACASCASadsasdsaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaSAC " + tileIds.size() + " " + ByteBuffer.wrap(mmBuffer).getInt());
                        if(tileIds.size() == tileAmount * 2){
                            setupTiles();
                        }
                    }else {
                        numBytes = mmInStream.read(mmBuffer);
                        System.out.println(numBytes + "KACSAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA GOT MESSAGE");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }

        // Call this from the main activity to send data to the remote device.
        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Call this method from the main activity to shut down the connection.
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private interface MessageConstants {
        public static final int MESSAGE_READ = 0;
        public static final int MESSAGE_WRITE = 1;
        public static final int MESSAGE_TOAST = 2;

        // ... (Add other message types here as needed.)
    }
}

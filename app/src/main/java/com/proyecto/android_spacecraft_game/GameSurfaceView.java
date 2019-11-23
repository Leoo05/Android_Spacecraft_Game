package com.proyecto.android_spacecraft_game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class GameSurfaceView extends SurfaceView implements Runnable {

    private boolean startCheking;
    private boolean isPlaying;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder holder;
    private Thread gameplayThread = null;

    /**
     * Contructor
     * @param context
     */
    public GameSurfaceView(Context context, float screenWith, float screenHeight) {
        super(context);
    }

    /**
     * Method implemented from runnable interface
     */
    @Override
    public void run() {
        while (isPlaying) {
            updateInfo();
            paintFrame();
        }
    }

    private void updateInfo() {

    }

    private void paintFrame() {
        if (holder.getSurface().isValid()){
            canvas = holder.lockCanvas();
            canvas.drawColor(Color.CYAN);
//            canvas.drawText(gameManager.getScore(), gameManager.getX(), gameManager.getY(), paint);
//            canvas.drawBitmap(icecreamCar.getSpriteIcecreamCar(),icecreamCar.getPositionX(),icecreamCar.getPositionY(),paint);
            holder.unlockCanvasAndPost(canvas);
        }
    }


    public void pause() {
        isPlaying = false;
        try {
            gameplayThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public void resume() {
        isPlaying = true;
        gameplayThread = new Thread(this);
        gameplayThread.start();
    }

    /**
     * Detect the action of the touch event
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                System.out.println("TOUCH UP - STOP JUMPING");
                break;
            case MotionEvent.ACTION_DOWN:
                System.out.println("TOUCH DOWN - JUMP");
                break;
        }
        return true;
    }

}
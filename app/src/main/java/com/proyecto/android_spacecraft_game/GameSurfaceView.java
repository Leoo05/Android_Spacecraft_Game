package com.proyecto.android_spacecraft_game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageButton;

import java.util.Random;

public class GameSurfaceView extends SurfaceView implements Runnable {

    private boolean startCheking;
    private boolean isPlaying;
    private final float screenWidth;
    private final float screenHeight;
    private Paint paint;
    private Meteor m1;
    private Meteor m2;
    private Meteor m3;
    private Meteor m4;
    private EnemyShip e1;
    private EnemyShip e2;
    private EnemyShot enemyShot;
    private EnemyShot enemyShot2;
    private PlayerShot playerShot;

    private Canvas canvas;
    private SurfaceHolder holder;
    private SpaceShip player;
    private Thread gameplayThread = null;

    /**
     * Contructor
     * @param context
     */
    public GameSurfaceView(Context context, float screenWidth, float screenHeight) {
        super(context);
        this.screenHeight=screenHeight;
        this.screenWidth=screenWidth;
        player = new SpaceShip(context, screenWidth,screenHeight);
        m1=new Meteor(context,screenWidth,screenHeight);
        m2=new Meteor(context,screenWidth,screenHeight);
        m3=new Meteor(context,screenWidth,screenHeight);
        m4=new Meteor(context,screenWidth,screenHeight);
        e1 = new EnemyShip(context,screenWidth,screenHeight);
        e2 = new EnemyShip(context,screenWidth,screenHeight);
        enemyShot = new EnemyShot(context,screenWidth,screenHeight,e1);
        enemyShot2 = new EnemyShot(context,screenWidth,screenHeight,e2);
        holder = getHolder();
        paint= new Paint();
        startCheking=false;
        isPlaying=true;
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
        player.updateInfo();
        m1.updateInfo();
        m2.updateInfo();
        m3.updateInfo();
        m4.updateInfo();
        e1.updateInfo();
        e2.updateInfo();
        enemyShot.updateInfo();
        enemyShot2.updateInfo();
        if(playerShot==null){

        }else{
            playerShot.updateInfo();
        }

    }

    private void paintFrame() {
        if (holder.getSurface().isValid()){
            canvas = holder.lockCanvas();
            canvas.drawColor(Color.CYAN);
            canvas.drawBitmap(player.getSpriteSpaceShip(),player.getPositionX(),player.getPositionY(),paint);
            canvas.drawBitmap(m1.getSpriteMeteor(),m1.getPositionX(),m1.getPositionY(),paint);
            canvas.drawBitmap(m2.getSpriteMeteor(),m2.getPositionX(),m2.getPositionY(),paint);
            canvas.drawBitmap(m3.getSpriteMeteor(),m3.getPositionX(),m3.getPositionY(),paint);
            canvas.drawBitmap(m4.getSpriteMeteor(),m4.getPositionX(),m4.getPositionY(),paint);
            canvas.drawBitmap(e1.getSpriteEnemyShip(),e1.getPositionX(),e1.getPositionY(),paint);
            canvas.drawBitmap(e2.getSpriteEnemyShip(),e2.getPositionX(),e2.getPositionY(),paint);
            canvas.drawBitmap(enemyShot.getSpriteEnemyShot(),enemyShot.getPositionX(),enemyShot.getPositionY(),paint);
            canvas.drawBitmap(enemyShot2.getSpriteEnemyShot(),enemyShot2.getPositionX(),enemyShot2.getPositionY(),paint);
//            canvas.drawText(gameManager.getScore(), gameManager.getX(), gameManager.getY(), paint);
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
                player.setJumping(false);
                startCheking=true;
                break;
            case MotionEvent.ACTION_DOWN:
                System.out.println("TOUCH DOWN - JUMP");
                player.setJumping(true);
                break;
            case MotionEvent.ACTION_BUTTON_PRESS:
                System.out.println("SHOOT");
                playerShot = new PlayerShot(getContext(),screenWidth,screenHeight,player);
                canvas.drawBitmap(playerShot.getSpritePlayerShot(),playerShot.getPositionX(),enemyShot2.getPositionY(),paint);
        }
        return true;
    }

}

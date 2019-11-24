package com.proyecto.android_spacecraft_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class EnemyShot {

    public static final float INIT_X = 100;
    public static final float INIT_Y = 100;
    public static final int SPRITE_SIZE_WIDTH = 25;
    public static final int SPRITE_SIZE_HEIGTH = 5;
    public static final float GRAVITY_FORCE = 10;
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;
    private EnemyShip enemyShip;
    private float maxY;
    private float maxX;

    private float speed = 0;
    private float positionX;
    private float positionY;
    private Bitmap spriteEnemyShot;
    private boolean isJumping;


    public EnemyShot(Context context, float screenWidth, float screenHeigth, EnemyShip enemyShip) {
        this.enemyShip = enemyShip;
        speed = 20;
        isJumping = false;
        //Getting bitmap from resource
        Bitmap originalBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bal_enemigo2);
        spriteEnemyShot = Bitmap.createScaledBitmap(originalBitmap, SPRITE_SIZE_WIDTH, SPRITE_SIZE_HEIGTH, false);
        positionX = enemyShip.getPositionX() + spriteEnemyShot.getWidth();
        positionY = enemyShip.getPositionY() + (enemyShip.getSpriteEnemyShip().getHeight() / 2);
        this.maxX = screenWidth;
        this.maxY = screenHeigth - spriteEnemyShot.getHeight();
    }

    public EnemyShot(Context context, float initialX, float initialY, float screenWidth, float screenHeigth) {
        speed = 1;
        positionX = initialX;
        positionY = initialY;
        Bitmap originalBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bal_enemigo2);
        spriteEnemyShot = Bitmap.createScaledBitmap(originalBitmap, SPRITE_SIZE_WIDTH, SPRITE_SIZE_HEIGTH, false);
        this.maxX = screenWidth - (spriteEnemyShot.getWidth() / 2);
        positionY = enemyShip.getPositionY() + (enemyShip.getSpriteEnemyShip().getHeight() / 2);
    }

    public static float getInitX() {
        return INIT_X;
    }

    public static float getInitY() {
        return INIT_Y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public Bitmap getSpriteEnemyShot() {
        return spriteEnemyShot;
    }

    public void setSpriteEnemyShot(Bitmap spriteEnemyShot) {
        this.spriteEnemyShot = spriteEnemyShot;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    /**
     * Control the position and behaviour of the icecream car
     */
    public void updateInfo() {
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }
        positionX -= speed;
        if (positionX < 0) {
            positionX = enemyShip.getPositionX();
        }
        positionY = enemyShip.getPositionY() + (enemyShip.getSpriteEnemyShip().getHeight() / 2);
    }

    public boolean checkPlayerColision(SpaceShip player){
        if((positionY >= player.getPositionY() && positionY <= player.getPositionY() + player.getSpriteSpaceShip().getHeight()) ||
                (positionY + spriteEnemyShot.getHeight() >= player.getPositionY() && positionY + spriteEnemyShot.getHeight() <= player.getPositionY() + player.getSpriteSpaceShip().getHeight())){
            if(positionX >= player.getPositionX() && player.getPositionX() + positionX <= player.getSpriteSpaceShip().getWidth()){
                return true;
            }
        }
        return false;
    }
}

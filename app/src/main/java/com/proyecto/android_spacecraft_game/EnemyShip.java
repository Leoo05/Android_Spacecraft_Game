package com.proyecto.android_spacecraft_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import java.util.Random;

public class EnemyShip {

    public static final float INIT_X = 100;
    public static final int SPRITE_SIZE_WIDTH = 100;
    public static final int SPRITE_SIZE_HEIGTH = 100;
    private final int MIN_SPEED = 5;
    private final int MAX_SPEED = 9;

    private float maxY;
    private float maxX;

    private float speed = 0;
    private float positionX;
    private float positionY;
    private Bitmap spriteEnemyShip;
    private boolean isAlive;


    public EnemyShip(Context context, float screenWidth, float screenHeigth) {
        speed = 0.09F;
        isAlive = true;
        //Getting bitmap from resource
        Bitmap originalBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemigo2);
        spriteEnemyShip = Bitmap.createScaledBitmap(originalBitmap, SPRITE_SIZE_WIDTH, SPRITE_SIZE_HEIGTH, false);
        this.maxX = screenWidth;
        this.maxY = screenHeigth - spriteEnemyShip.getHeight();
        positionX = this.maxX + spriteEnemyShip.getWidth();
        Random rnd = new Random();
        positionY = (float) rnd.nextInt((int) maxY);
    }

    public EnemyShip(Context context, float initialX, float initialY, float screenWidth, float screenHeigth) {
        speed = 0.09F;
        Bitmap originalBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemigo2);
        spriteEnemyShip = Bitmap.createScaledBitmap(originalBitmap, SPRITE_SIZE_WIDTH, SPRITE_SIZE_HEIGTH, false);
        this.positionX = initialX;
        this.positionY = initialY;
        this.isAlive = true;
        this.maxX = screenWidth;
        this.maxY = screenHeigth - SPRITE_SIZE_HEIGTH;
    }

    public static float getInitX() {
        return INIT_X;
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

    public Bitmap getSpriteEnemyShip() {
        return spriteEnemyShip;
    }

    public void setSpriteEnemyShip(Bitmap spriteEnemyShip) {
        this.spriteEnemyShip = spriteEnemyShip;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    /**
     * Control the position and behaviour of the Enemy Ship
     */
    public void updateInfo() {
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        if (speed < MIN_SPEED || speed == 0) {
            speed = MIN_SPEED;
        }
        this.positionX -= speed;
        if (positionX < 0) {
            Random rnd = new Random();
            positionX = maxX + SPRITE_SIZE_WIDTH;
            positionY = (float) rnd.nextInt((int) maxY);
            speed = rnd.nextInt(8);
        }
    }

    public void destroyShip(){
        Random rnd = new Random();
        positionX = maxX + SPRITE_SIZE_WIDTH;
        positionY = (float) rnd.nextInt((int) maxY);
        speed = rnd.nextInt(8);
    }

    public boolean checkPlayerColision(SpaceShip player) {
        if ((positionY >= player.getPositionY() && positionY <= player.getPositionY() + player.getSpriteSpaceShip().getHeight()) ||
                (positionY + SPRITE_SIZE_HEIGTH >= player.getPositionY() && positionY + SPRITE_SIZE_HEIGTH <= player.getPositionY() + player.getSpriteSpaceShip().getHeight())) {
            if (positionX >= player.getPositionX() && player.getPositionX() + positionX <= player.getSpriteSpaceShip().getWidth()) {
                return true;
            }
        }
        return false;
    }

}
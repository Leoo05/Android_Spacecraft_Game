package com.proyecto.android_spacecraft_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PlayerShot {

    public static final float INIT_X =100;
    public static final float INIT_Y =100;
    public static final int SPRITE_SIZE_WIDTH =25;
    public static final int SPRITE_SIZE_HEIGTH=5;
    public static final float GRAVITY_FORCE=10;
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 50;
    private SpaceShip spaceShip;
    private float maxY;
    private float maxX;

    private float speed = 0;
    private float positionX;
    private float positionY;
    private Bitmap spritePlayerShot;
    private boolean isActive = false;


    public PlayerShot (Context context, float screenWidth, float screenHeigth, SpaceShip spaceShip){
        this.spaceShip =spaceShip;
        speed = 50;
        //Getting bitmap from resource
        Bitmap originalBitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.bala_personaje);
        spritePlayerShot = Bitmap.createScaledBitmap(originalBitmap, SPRITE_SIZE_WIDTH, SPRITE_SIZE_HEIGTH, false);
        positionX = spaceShip.getPositionX()+ spritePlayerShot.getWidth();
        positionY = spaceShip.getPositionY()+ (spaceShip.getSpriteSpaceShip().getHeight() / 2);
        this.maxX = screenWidth;
        this.maxY = screenHeigth - spritePlayerShot.getHeight();
    }

    public PlayerShot (Context context, float initialX, float initialY, float screenWidth, float screenHeigth){
        speed = 1;
        positionX = initialX;
        positionY = initialY;
        Bitmap originalBitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.bala_personaje);
        spritePlayerShot = Bitmap.createScaledBitmap(originalBitmap, SPRITE_SIZE_WIDTH, SPRITE_SIZE_HEIGTH, false);
        this.maxX = screenWidth - (spritePlayerShot.getWidth()/2);
        this.maxY = screenHeigth - spritePlayerShot.getHeight();

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

    public Bitmap getSpritePlayerShot() {
        return spritePlayerShot;
    }

    public void setSpritePlayerShot(Bitmap spritePlayerShot) {
        this.spritePlayerShot = spritePlayerShot;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean jumping) {
        isActive = jumping;
    }

    /**
     * Control the position and behaviour of the icecream car
     */
    public void updateInfo () {
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }
        if(isActive){
            positionX += speed;
        }
        else{
            positionY=spaceShip.getPositionY()+ (spaceShip.getSpriteSpaceShip().getHeight() / 2);
        }
        if(positionX>maxX){
            positionX=spaceShip.getPositionX();
            setActive(false);
        }
    }

    public boolean checkMeteorColition(Meteor meteor){
        if((positionY >= meteor.getPositionY() && positionY <= meteor.getPositionY() + meteor.getSpriteMeteor().getHeight()) ||
                (positionY + spritePlayerShot.getHeight() >= meteor.getPositionY() && positionY + spritePlayerShot.getHeight() <= meteor.getPositionY() + meteor.getSpriteMeteor().getHeight())){
            if(positionX >= meteor.getPositionX() && meteor.getPositionX() + positionX <= meteor.getSpriteMeteor().getWidth()){
                return true;
            }
        }
        return false;
    }

    public boolean checkEnemyShipColition(EnemyShip enemyShip){
        if((positionY >= enemyShip.getPositionY() && positionY <= enemyShip.getPositionY() + enemyShip.getSpriteEnemyShip().getHeight()) ||
                (positionY + spritePlayerShot.getHeight() >= enemyShip.getPositionY() && positionY + spritePlayerShot.getHeight() <= enemyShip.getPositionY() + enemyShip.getSpriteEnemyShip().getHeight())){
            if(positionX >= enemyShip.getPositionX() && enemyShip.getPositionX() + positionX <= enemyShip.getSpriteEnemyShip().getWidth()){
                return true;
            }
        }
        return false;
    }
}

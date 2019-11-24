package com.proyecto.android_spacecraft_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Meteor {

    public static final float INIT_X =100;
    public static final int SPRITE_SIZE_WIDTH =50;
    public static final int SPRITE_SIZE_HEIGTH=50;
    private final int MIN_SPEED = 5;
    private final int MAX_SPEED = 16;

    private float maxY;
    private float maxX;

    private float speed = 0;
    private float positionX;
    private float positionY;
    private Bitmap spriteMeteor;
    private boolean isJumping;


    public Meteor(Context context, float screenWidth, float screenHeigth){

        speed = 0.09F;
        isJumping = false;
        //Getting bitmap from resource
        Bitmap originalBitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.meteoro);
        spriteMeteor = Bitmap.createScaledBitmap(originalBitmap, SPRITE_SIZE_WIDTH, SPRITE_SIZE_HEIGTH, false);
        this.maxX = screenWidth;
        this.maxY = screenHeigth - spriteMeteor.getHeight();
        positionX = this.maxX + spriteMeteor.getWidth();
        Random rnd = new Random();
        positionY =(float) rnd.nextInt((int) maxY);

    }

    public Meteor (Context context, float initialX, float initialY, float screenWidth, float screenHeigth){

        speed = 0.09F;
        Bitmap originalBitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.meteoro);
        spriteMeteor = Bitmap.createScaledBitmap(originalBitmap, SPRITE_SIZE_WIDTH, SPRITE_SIZE_HEIGTH, false);
        this.positionX=initialX;
        this.positionY=initialY;
        this.maxX = screenWidth + spriteMeteor.getWidth();
        this.maxY = screenHeigth - spriteMeteor.getHeight();

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

    public Bitmap getSpriteMeteor() {
        return spriteMeteor;
    }

    public void setSpriteMeteor(Bitmap spriteMeteor) {
        this.spriteMeteor = spriteMeteor;
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
    public void updateInfo () {
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        if (speed < MIN_SPEED || speed ==0) {
            speed = MIN_SPEED;
        }
        this.positionX -= speed;
        if (positionX < 0) {
            Random rnd = new Random();
            positionX = maxX+SPRITE_SIZE_WIDTH;
            positionY =(float) rnd.nextInt((int) maxY);
            speed=rnd.nextInt(17);
        }
        if (positionY < 0) {
            positionY = 0;
        }
        if (positionY > maxY) {
            positionY = maxY;
        }
    }

    public boolean checkPlayerColision(SpaceShip player){
        if((positionY >= player.getPositionY() && positionY <= player.getPositionY() + player.getSpriteSpaceShip().getHeight()) ||
                (positionY + spriteMeteor.getHeight() >= player.getPositionY() && positionY + spriteMeteor.getHeight() <= player.getPositionY() + player.getSpriteSpaceShip().getHeight())){
            if(positionX >= player.getPositionX() && player.getPositionX() + positionX <= player.getSpriteSpaceShip().getWidth()){
                return true;
            }
        }
        return false;
    }
}

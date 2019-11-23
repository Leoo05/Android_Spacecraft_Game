package com.proyecto.android_spacecraft_game;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class GamePlay extends AppCompatActivity {

    private GameSurfaceView gameSurfaceView;
    private ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_game_play);
        setContentView(R.layout.activity_gameplay);
        // Force the screen to use the landscape orintation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Display display = getWindowManager().getDefaultDisplay();
        Point screenSize = new Point();
        button = new ImageButton(this);
        button.setImageResource(R.drawable.shoot_button);
        //button.setX();
        //button.setY();
        button.setTranslationX(screenSize.x-button.getWidth());
        button.setTranslationY(screenSize.y-button.getHeight());
        display.getRealSize(screenSize);
        gameSurfaceView = new GameSurfaceView(this, screenSize.x, screenSize.y);
        setContentView(gameSurfaceView);
        addContentView(button,new ViewGroup.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT ));
    }


    @Override
    protected void onPause() {
        super.onPause();
        gameSurfaceView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameSurfaceView.resume();
    }


}

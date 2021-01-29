package com.example.zpo_14_game;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

/**
 * A Main class responsible for setting everything up when the App is launched
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hides all the unnecessary decorations (like status bar and so on ...)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Turns the title bar on top of the phone screen off
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //A new DisplayMetrics object responsible for handling the phone size dimensions
        DisplayMetrics dm = new DisplayMetrics();

        //Get the display dimensions and assign them to the proper fields in the Constants class
        getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        Constants.SCREEN_WIDTH  = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        //Set the content view to the new instance of GamePanel class - a custom SurfaceView class
        setContentView(new GameView(this));
    }
}
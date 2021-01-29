package com.example.zpo_14_game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;


/**
 * A SurfaceView extension class made for the purpose of overriding default app cycle methods
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    //An instance of self created Thread class
    private MainThread thread;
    private final SceneManager manager;


    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        Constants.CURRENT_CONTEXT = context;
        manager = new SceneManager();
        setFocusable(true);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        Constants.INIT_TIME = System.currentTimeMillis();
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
//        boolean retry = true;
//        while(retry) {
//            try {
//                thread.setRunning(false);
//                thread.join();
//            } catch(Exception e) {e.printStackTrace();}
//            retry = false;
//        }
    }

    public void update() {
        manager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        manager.draw(canvas);
    }
}
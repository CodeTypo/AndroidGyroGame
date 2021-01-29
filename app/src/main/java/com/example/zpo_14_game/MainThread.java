package com.example.zpo_14_game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Extends standard Thread class which is a thread of execution in a program
 * This class enhances the basic thread class especially with overridden run() method
 * which handles writing and displaying 
 */
public class MainThread extends Thread {

    public static final int MAX_FPS = 30;
    public static Canvas canvas;

    private final SurfaceHolder   surfaceHolder;
    private boolean               running;
    private final GamePanel             gamePanel;

    public void setRunning(boolean running) {
        this.running = running;
    }

    public MainThread (SurfaceHolder surfaceHolder, GamePanel gamePanel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run(){
        long startTime;
        long waitTime;
        long timeMilis;
        long targetTime = 1000/MAX_FPS;
        long totalTime  = 0;
        int frameCount  = 0;


        while(running){

            startTime = System.nanoTime();
            canvas = null;

            try{
                canvas = this.surfaceHolder.lockCanvas();

                synchronized (surfaceHolder){

                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);

                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(canvas != null){
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            timeMilis = (System.nanoTime()  - startTime)/1000000;
            waitTime = targetTime - timeMilis;

            try{
                if(waitTime > 0)
                    sleep(waitTime);
            } catch (Exception e){
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount ++;

            if(frameCount == MAX_FPS){
                double averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                Log.d("GAMEDEBUG", String.valueOf(averageFPS));

            } //end of if block

        } //end of while(running) method

    } //end of public void run() method

} //End of MainThread class

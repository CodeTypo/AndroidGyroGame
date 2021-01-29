package com.example.zpo_14_game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * A class responsible for handling the movement animations of the player-controlled object sprite
 */

public class Animation {

    private final float     frameTime;          // A duration of every single frame in the animation
    private final Bitmap[]  frames;             // An array of bitmap objects used as anim. frames.
    private boolean         isRunning = false;  // A boolean informing if animation is curr running
    private long            lastFrame;          // A timestamp of lastly displayed frame
    private int             currentFrame;         // An index of current Bitmap that is being displayed

    //Default constructor
    public Animation(Bitmap[] frames, float animTime) {
        //Assigning a set of frames to the class field
        this.frames = frames;
        //Determining the time of each frame in the animation
        frameTime = animTime/frames.length;
    }

    //A boolean getter to determine whether there is already some animation running
    public boolean isRunning() {
        return isRunning;
    }

    //A method that plays the animation
    public void play() {
        isRunning = true;                       //This one is obvious
        currentFrame = 0;                         //This one as well, we start the animation from 0
        lastFrame = System.currentTimeMillis(); //The lastly displayed frame is set to curr. sys. time
    }

    //A method that does what it says
    public void stop() {
        isRunning = false;
    }

    // A draw method, responsible for drawing the animation to the canvas
    public void draw(Canvas canvas, Rect currentPosition) {
        //If the animation is not running
        if(!isRunning)
            return;         //The method does nothing
        //Calling the method that scales the sprite so that it fits the rectangular player hitbox
        scaleSprite(currentPosition);

        //The scaled sprite is being painted on the canvas
        canvas.drawBitmap(frames[currentFrame], null, currentPosition, new Paint());
    }

    //A method that is responsible for scaling the sprite so that it matches the hitbox and keeps ratio
    private void scaleSprite(Rect rect) {
        //At first, it calculates the sprite width to height ratio
        double widthToHeightRatio = (double)(frames[currentFrame].getWidth())
                                    /frames[currentFrame].getHeight();
        //if the rectangle is wider than it's high
        if(rect.width() > rect.height())
            //It is being rescaled
            rect.left = (int) (rect.right - (rect.height() * widthToHeightRatio));
        else
            //The same applies to the other situation
            rect.top = rect.bottom - (int)(rect.width() * (1/widthToHeightRatio));
    }

    //The method that updates the animation each time it needs to be updated
    public void update() {
        //If it's not running there is no need to update
        if(!isRunning)
            return;

        //If the current frame's time has been exceeded
        if(System.currentTimeMillis() - lastFrame > frameTime*1000) {
            //We move to displaying another
            currentFrame++;
            //If the current frame is the end of animation frame set we start from the beginning (0)
            currentFrame = (currentFrame >= frames.length) ? 0 : currentFrame;
            //setting current system time as the lastly displayed frame timestamp
            lastFrame = System.currentTimeMillis();
        }
    }
}// End of Animation class

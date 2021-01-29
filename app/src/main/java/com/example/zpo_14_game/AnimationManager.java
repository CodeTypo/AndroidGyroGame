package com.example.zpo_14_game;

import android.graphics.Canvas;
import android.graphics.Rect;

//A class that manages the animation displaying logic
public class AnimationManager {

    // a table containing a set of all the animations used in the project
    private final Animation[] animations;
    // An index pointing to the current variation of the animation that needs to be runned
    private int chosenAnimation = 0;

    public AnimationManager(Animation[] animations) {
        this.animations = animations;
    }

    //A method responsible for running the animation which index is passed as the parameter
    public void runAnim(int index) {
        //iterating through the animation set
        for(int i = 0; i < animations.length; i++) {
            //If we find the animation that matches the index passed
            if(i == index) {
                //And it is not currently running
                if(!animations[index].isRunning())
                    //It is being started
                    animations[i].play();
            } else
                //If there is no such animation, animations are being stopped
                animations[i].stop();
        }
        //assigning the value passed as index to the chosen animation class field
        chosenAnimation = index;
    }

    //A manager draw method requiring a canvas to draw the animation onto, and a Rect that describes
    //the precise location for the animation to be drawn
    public void draw(Canvas canvas, Rect target) {
        if(animations[chosenAnimation].isRunning())
            animations[chosenAnimation].draw(canvas, target);
    }

    //A method that tells the actually running animation to update itself
    public void update() {
        if(animations[chosenAnimation].isRunning())
            animations[chosenAnimation].update();
    }
}
package com.example.zpo_14_game;

import android.graphics.Canvas;
import android.graphics.Rect;

//A class that manages the animation displaying logic, setting animation direction is implemented here
public class AnimationManager {
    //
    private final Animation[] animations;
    private int animationIndex = 0;

    public AnimationManager(Animation[] animations) {
        this.animations = animations;
    }

    public void playAnim(int index) {
        for(int i = 0; i < animations.length; i++) {
            if(i == index) {
                if(!animations[index].isRunning())
                    animations[i].play();
            } else
                animations[i].stop();
        }
        animationIndex = index;
    }

    public void draw(Canvas canvas, Rect rect) {
        if(animations[animationIndex].isRunning())
            animations[animationIndex].draw(canvas, rect);
    }

    public void update() {
        if(animations[animationIndex].isRunning())
            animations[animationIndex].update();
    }
}
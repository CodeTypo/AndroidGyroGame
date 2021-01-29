package com.example.zpo_14_game;

import android.graphics.Canvas;

/**
 * A simple interface implemented by all the objects that appear in our game, 
 * Every object that appears should have implemented a draw method, that draws it on the canvas
 * And an update method, that updates it parameters (like location or so), every time it needs to be
 * updated according to the max fps count
 */
public interface GameObject {
    void draw(Canvas canvas);
    void update();
}

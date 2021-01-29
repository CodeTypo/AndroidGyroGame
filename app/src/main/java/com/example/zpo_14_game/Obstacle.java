package com.example.zpo_14_game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


/**
 * A class that represents single Obstacle that appears in the game
 */
public class Obstacle implements GameObject {

    private final Rect leftWing;
    private final Rect rightWing;
    private int color;

    public Rect getLeftWing() {
        return leftWing;
    }
    
    public void incrementY(float y){
        leftWing.top +=y;
        leftWing.bottom +=y;
        rightWing.top +=y;
        rightWing.bottom +=y;
    }

    public Obstacle(int rectHeight, int color, int startX, int startY, int playerGap){
        this.color = color;
        leftWing = new Rect(0, startY, startX, startY + rectHeight);
        rightWing = new Rect(startX + playerGap, startY, Constants.SCREEN_WIDTH, startY+rectHeight);
    }

    public boolean playerCollide(PlayerModel player){
        return Rect.intersects(leftWing, player.getRectangle()) || Rect.intersects(rightWing, player.getRectangle());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(leftWing,paint);
        canvas.drawRect(rightWing,paint);
    }

    @Override
    public void update() {

    }
}

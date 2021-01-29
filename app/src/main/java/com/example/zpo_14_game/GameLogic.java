package com.example.zpo_14_game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * A class that contains most of the game backend logic
 */
public class GameLogic {
    
    //An object representing the interactive player model / hitbox
    private final PlayerModel player;

    //An object representing the player location on the screen
    private final Point playerPoint;

    //A class that handles managing the obstacles
    private final ObstacleManager obstacleManager;

    //a boolean that indicates if the game is over
    private boolean gameOver = false;

    //A class responsible for handling the steering with sensors
    private final SensorSteering sensorSteering;

    private long frameTime;

    public GameLogic() {
        //Creating a new player hitbox
        player      = new PlayerModel(new Rect(100, 100, 200, 200));
        //Creating a new point responsible for handling the player's position on the screen
        playerPoint = new Point(Constants.SCREEN_WIDTH/2, 3*Constants.SCREEN_HEIGHT/4);
        //synchronising the player hitbox with its position
        player.update(playerPoint);
        //Creating a new instance of Obstacle Manager class and setting its base parameters
        obstacleManager = new ObstacleManager(Constants.PLAYER_GAP,
                                    Constants.DISTANCE_BETWEEN_OBSTACLES,
                                    Constants.OBSTACLES_HEIGHT);

        //Creating a new SensorSteering class and calling its register method to register sensors.
        sensorSteering = new SensorSteering();
        sensorSteering.register();

        //Setting frame time to the current system time in ms
        frameTime = System.currentTimeMillis();
    }

    //A method that draws everything at the beginning of the game
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        player.draw(canvas);
        obstacleManager.draw(canvas);

        //If game is over, displays a text message
        if(gameOver) {
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.MAGENTA);
            drawCenterText(canvas, paint);
        }
    }

    //A method responsible for updating everything on the screen every frametime
    public void update() {
        if(!gameOver) {     //If the game is not over yet
            if(frameTime < Constants.INIT_TIME)     //And the initialisation time has come
                frameTime = Constants.INIT_TIME;
            int timePassed = (int)(System.currentTimeMillis() - frameTime);
            frameTime = System.currentTimeMillis();
            if(sensorSteering.getOrientation() != null && sensorSteering.getStartOrientation() != null) {
                float verticalMovement  = sensorSteering.getOrientation()[1] - sensorSteering.getStartOrientation()[1];
                float sidewaysMovement  = sensorSteering.getOrientation()[2] - sensorSteering.getStartOrientation()[2];

                float xSpeed = 3 * sidewaysMovement * Constants.SCREEN_WIDTH/1000f;
                float ySpeed =     verticalMovement * Constants.SCREEN_HEIGHT/1000f;

                playerPoint.x += Math.abs(xSpeed*timePassed) > 5 ? xSpeed*timePassed : 0;
                playerPoint.y -= Math.abs(ySpeed*timePassed) > 5 ? ySpeed*timePassed : 0;
            }

            //The app is not allowing the user to go off the screen, everything happens in bounds
            if(playerPoint.x < 0)
                playerPoint.x = 0;
            else if(playerPoint.x > Constants.SCREEN_WIDTH)
                playerPoint.x = Constants.SCREEN_WIDTH;
            if(playerPoint.y < 0)
                playerPoint.y = 0;
            else if(playerPoint.y > Constants.SCREEN_HEIGHT)
                playerPoint.y = Constants.SCREEN_HEIGHT;

            //Updating the hitbox to follow the point
            player.update(playerPoint);
            //updating the obstacles
            obstacleManager.update();
            //If there is a collision the game is over
            if(obstacleManager.playerCollide(player)) {
                gameOver = true;
            }
        }
    }

    //Drawing the Game Over message
    private void drawCenterText(Canvas canvas, Paint paint) {
        final Rect r = new Rect();
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds("Game Over", 0, "Game Over".length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText("Game Over", x, y, paint);
    }
}
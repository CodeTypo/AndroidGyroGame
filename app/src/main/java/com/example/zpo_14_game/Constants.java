package com.example.zpo_14_game;

import android.content.Context;
import android.graphics.Color;

/**
 * A class designed to keep all the created constants used in the app in one place.
 */
public class Constants {
    //Screen dimensions constants
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    //Gameplay difficulty
    public static int PLAYER_GAP = 300 ;
    public static int OBSTACLES_HEIGHT = 35;
    public static int DISTANCE_BETWEEN_OBSTACLES;
    public static int OBSTACLE_COLOR = Color.RED ;

    //Application context
    public static Context CURRENT_CONTEXT;

    public static long INIT_TIME;
}
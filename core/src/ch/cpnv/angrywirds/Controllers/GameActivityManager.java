package ch.cpnv.angrywirds.Controllers;


import java.util.Stack;

import ch.cpnv.angrywirds.Activities.GameActivity;

public abstract class GameActivityManager {

    public static final Integer START_POINTS = 50;
    public static Integer points = START_POINTS;

    public static final float START_TIME = 120;
    public static float time = START_TIME;

    public static Integer VOC_ID;
    public static Integer ASSIGNMENTS_ID;

    public static Stack<GameActivity> activities = new Stack<GameActivity>();

    public GameActivityManager() {
    }

    public static void push(GameActivity activity){
        activities.push(activity);
    }

    public static void pop(){
        activities.pop();
    }

    public static void render(){
        activities.peek().render();
    }

}



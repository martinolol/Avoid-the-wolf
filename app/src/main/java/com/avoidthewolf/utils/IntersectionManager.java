package com.avoidthewolf.utils;

import android.util.Log;

public class IntersectionManager {

    public IntersectionManager(){

    }

    public static boolean isPointInCircle(Position pointPos, Position circlePos, float circleRadius){
        Log.d("IntersectionManager", "pointPos(" + pointPos.x + ";" + pointPos.y + ")");
        Log.d("IntersectionManager", "circlePos(" + circlePos.x + ";" + circlePos.y + ")");
        Log.d("IntersectionManager", "circleRadius : " + circleRadius);

        if(Distance.getDistance(pointPos, circlePos) <= circleRadius){
            return true;
        }
        return false;
    }

    public static boolean isCircleIntersectCircle(Position circle1pos, Position circle2pos, float circlesRadius){
        if(Distance.getDistance(circle1pos, circle2pos) <= (circlesRadius * 2)){
            return true;
        }
        return false;
    }
}

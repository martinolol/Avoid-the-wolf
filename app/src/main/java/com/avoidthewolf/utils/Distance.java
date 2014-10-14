package com.avoidthewolf.utils;

public class Distance {

    public Distance(){

    }

    public static float getDistance(Position p1, Position p2){
        return (float)Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}

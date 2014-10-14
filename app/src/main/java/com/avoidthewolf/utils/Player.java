package com.avoidthewolf.utils;

public class Player {

    private Position pos;
    private boolean isWolf;

    public Player(){
        pos = new Position();
    }

    public Player(float x, float y){
        pos = new Position(x, y);
    }

    public void setPos(float x, float y){
        pos.x = x;
        pos.y = y;
    }

    public Position getPos(){
        return pos;
    }

    public float getPosX(){
        return pos.x;
    }

    public float getPosY(){
        return pos.y;
    }

    public void setWolf(boolean isWolf){
        this.isWolf = isWolf;
    }

    public boolean isWolf(){
        return isWolf;
    }
}

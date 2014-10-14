package com.avoidthewolf.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.avoidthewolf.R;
import com.avoidthewolf.utils.IntersectionManager;
import com.avoidthewolf.utils.Player;
import com.avoidthewolf.utils.Position;

public class GameView extends View {

    private TouchManager touchManager;

    private float characterRadius;

    private Paint paintPlayer;
    private Paint paintOpponent;

    private Player player;
    private Player opponent;

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        touchManager = new TouchManager();

        characterRadius = 0;
        player = new Player();
        opponent = new Player();

        player.setWolf(false);
        opponent.setWolf(true);

        paintPlayer = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintOpponent = new Paint(Paint.ANTI_ALIAS_FLAG);

        paintPlayer.setColor(player.isWolf() ? Color.RED : Color.BLUE);
        paintOpponent.setColor(opponent.isWolf() ? Color.RED : Color.BLUE);

    }

    @Override
    public void onDraw(Canvas canvas){
        if(characterRadius == 0){
            characterRadius = canvas.getWidth() / 15;
            player.setPos(canvas.getWidth() / 2, canvas.getHeight() - (canvas.getHeight() / 4));
            opponent.setPos(canvas.getWidth() / 2, canvas.getHeight() / 4);
        }

        canvas.drawCircle(player.getPosX(), player.getPosY(), characterRadius, paintPlayer);
        canvas.drawCircle(opponent.getPosX(), opponent.getPosY(), characterRadius, paintOpponent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        return touchManager.onTouch(event);
    }

    private class TouchManager{

        public TouchManager(){

        }

        public boolean onTouch(MotionEvent event){

            if(event.getAction() == MotionEvent.ACTION_DOWN){
                if(!IntersectionManager.isPointInCircle(new Position(event.getX(), event.getY()), player.getPos(), characterRadius)){
                    return false;
                }

            }else if(event.getAction() == MotionEvent.ACTION_MOVE){

                player.setPos(event.getX(), event.getY());
                invalidate();

            }else if(event.getAction() == MotionEvent.ACTION_UP){
                return false;
            }

            return true;
        }

    }


}

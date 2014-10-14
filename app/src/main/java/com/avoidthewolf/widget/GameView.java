package com.avoidthewolf.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.avoidthewolf.R;
import com.avoidthewolf.listener.GameListener;
import com.avoidthewolf.utils.Dimension;
import com.avoidthewolf.utils.IntersectionManager;
import com.avoidthewolf.utils.Player;
import com.avoidthewolf.utils.Position;

public class GameView extends View {

    private TouchManager touchManager;

    private GameListener gameListener;

    private float characterRadius;

    private Paint paintPlayer;
    private Paint paintOpponent;

    private Player player;
    private Player opponent;

    private Dimension screenDimension;

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

    public void setGameListener(GameListener gameListener){
        this.gameListener = gameListener;
    }

    @Override
    public void onDraw(Canvas canvas){
        if(characterRadius == 0){
            characterRadius = canvas.getWidth() / 15;
            player.setPos(canvas.getWidth() / 2, canvas.getHeight() - (canvas.getHeight() / 4));
            opponent.setPos(canvas.getWidth() / 2, canvas.getHeight() / 4);
            screenDimension = new Dimension(canvas.getWidth(), canvas.getHeight());
        }

        canvas.drawCircle(player.getPosX(), player.getPosY(), characterRadius, paintPlayer);
        canvas.drawCircle(opponent.getPosX(), opponent.getPosY(), characterRadius, paintOpponent);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){

        final int width = MeasureSpec.getSize(widthMeasureSpec);
        final int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    public void movePlayer(float posX, float posY){
        player.setPos(posX, posY);
        invalidate();
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

                final Position touchedPos = new Position(event.getX(), event.getY());
                if(touchedPos.x - characterRadius < 0){
                    touchedPos.x = characterRadius;

                }else if(touchedPos.x + characterRadius > screenDimension.width){
                    touchedPos.x = screenDimension.width - characterRadius;
                }

                if(touchedPos.y - characterRadius < 0){
                    touchedPos.y = characterRadius;

                }else if(touchedPos.y + characterRadius > screenDimension.height){
                    touchedPos.y = screenDimension.height - characterRadius;
                }

                if(gameListener != null){
                    gameListener.onPlayerMoved(touchedPos);
                }

            }else if(event.getAction() == MotionEvent.ACTION_UP){
                return false;
            }

            return true;
        }

    }


}

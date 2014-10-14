package com.avoidthewolf;

import android.app.Activity;
import android.os.Bundle;

import com.avoidthewolf.listener.GameListener;
import com.avoidthewolf.utils.Position;
import com.avoidthewolf.widget.GameView;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class MainActivity extends Activity implements GameListener {

    private GameView gameView;

    private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameView = (GameView)findViewById(R.id.gameView);
        
        gameView.setGameListener(this);

        firebase = new Firebase("https://avoid-the-wolf.firebaseio.com");
        firebase.addValueEventListener(firebaseValueEvent);
    }

    private ValueEventListener firebaseValueEvent = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            gameView.movePlayer(new Float((Double)dataSnapshot.child("playerPosX").getValue()), new Float((Double)dataSnapshot.child("playerPosY").getValue()));
        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    };

    @Override
    public void onPlayerMoved(Position playerPos) {
        firebase.child("playerPosX").setValue(playerPos.x);
        firebase.child("playerPosY").setValue(playerPos.y);
    }
}

package com.avoidthewolf.listener;

import com.avoidthewolf.utils.Position;

public interface GameListener {
    public abstract void onPlayerMoved(Position playerPos);
}

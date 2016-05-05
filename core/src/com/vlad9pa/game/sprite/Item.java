package com.vlad9pa.game.sprite;

import com.badlogic.gdx.math.Rectangle;

public abstract class Item {

    public abstract void move(float dt);
    public abstract boolean collide(Rectangle player);
    public abstract void dispose();
}

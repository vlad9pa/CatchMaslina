package com.vlad9pa.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by vlad9pa on 5/5/16.
 */
public class Maslina extends Item {

    private Texture Img;
    private Rectangle Bound;
    private Vector2 Pos;

    public Maslina(float x,float y) {
        Img = new Texture("maslina.png");
        Pos = new Vector2(x,y);
        Bound = new Rectangle(Pos.x, Pos.y, Img.getWidth(), Img.getHeight());
    }


    @Override
    public void move(float dt) {
        Pos.y -= dt*Gdx.graphics.getDeltaTime();
        Bound.setPosition(Pos.x,Pos.y);

    }

    @Override
    public boolean collide(Rectangle player) {
        return Bound.overlaps(player) ? true : false;
    }

    @Override
    public void dispose() {
        Img.dispose();
    }

    public Texture getImg() {
        return Img;
    }

    public Vector2 getPos() {
        return Pos;
    }

    public void setPos(float x) {
        Pos.x = x;
    }
}

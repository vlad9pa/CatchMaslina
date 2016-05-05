package com.vlad9pa.game.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by vlad9pa on 5/5/16.
 */
public abstract class State {
    protected OrthographicCamera camera;
    protected Vector2 vector2;

    protected GameStateManager gsm;

    public  State(GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
        vector2 = new Vector2();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}

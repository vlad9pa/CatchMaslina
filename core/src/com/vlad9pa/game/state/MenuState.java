package com.vlad9pa.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vlad9pa.game.catchMaslina;

/**
 * Created by vlad9pa on 5/5/16.
 */
public class MenuState extends State {

    private Texture background;
    private OrthographicCamera camera;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, catchMaslina.WIDTH,catchMaslina.HEIGHT);
        background = new Texture("menu.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new playState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background,camera.position.x-(camera.viewportWidth/2),0);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}

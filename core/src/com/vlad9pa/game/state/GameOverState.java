package com.vlad9pa.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vlad9pa.game.catchMaslina;
import com.vlad9pa.game.sprite.Bandit;


public class GameOverState extends State {

    private Texture img;
    private BitmapFont txt;
    private OrthographicCamera camera;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        img = new Texture("gameover.png");
        txt = new BitmapFont();
        txt.getData().setScale((float) 0.4);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, catchMaslina.WIDTH,catchMaslina.HEIGHT);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(img,camera.position.x-(camera.viewportWidth/2),0);
        txt.draw(sb, "Olives:"+Bandit.counter, 64,32);
        sb.end();

    }

    @Override
    public void dispose() {
        img.dispose();
        txt.dispose();
    }
}

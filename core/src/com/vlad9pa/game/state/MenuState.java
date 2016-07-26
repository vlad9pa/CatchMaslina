package com.vlad9pa.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vlad9pa.game.Button;
import com.vlad9pa.game.catchMaslina;

/**
 * Created by vlad9pa on 5/5/16.
 */
public class MenuState extends State {

    private Texture background;
    private Button button;
    private OrthographicCamera camera;
    private double wC,hC;

    public MenuState(GameStateManager gsm) {
        super(gsm);


        wC = 128.0/Gdx.graphics.getWidth();
        hC = 64.0/Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, catchMaslina.WIDTH,catchMaslina.HEIGHT);
        background = new Texture("background.png");



        button = new Button(64,32);

        System.out.println(button.getPosition().y-button.getImg().getHeight()/2);

    }

    @Override
    protected void handleInput() {
        button.buttonDown(Gdx.input.getX()*wC,Gdx.input.getY()*hC);
        if(button.buttonUp()){
            gsm.set(new playState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        wC = 128.0/Gdx.graphics.getWidth();
        hC = 64.0/Gdx.graphics.getHeight();
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background,camera.position.x-(camera.viewportWidth/2),0);
        sb.draw(button.getImg(),button.getPosition().x-button.getImg().getWidth()/2,
                button.getPosition().y-button.getImg().getHeight()/2);
        sb.end();
    }

    @Override
    public void dispose() {
        button.dispose();
        background.dispose();
    }
}

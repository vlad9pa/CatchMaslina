package com.vlad9pa.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.vlad9pa.game.catchMaslina;
import com.vlad9pa.game.sprite.Bandit;
import com.vlad9pa.game.sprite.Maslina;
import com.vlad9pa.game.sprite.Olive;

public class playState extends State {

    private Maslina[] maslins;
    private Olive[] olives;
    private Bandit bandit;
    private Texture background;
    private Music music;
    private BitmapFont txtCounter;
    private double screenCoef;
    private Sound sound;

    public playState(GameStateManager gsm) {
        super(gsm);

        screenCoef = 128.0/Gdx.graphics.getWidth();

        bandit.counter = 0;

        sound = Gdx.audio.newSound(Gdx.files.internal("maslina.mp3"));

        txtCounter = new BitmapFont();
        txtCounter.getData().setScale((float) 0.4);

        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.play();

        background = new Texture("background.png");

        bandit = new Bandit(64,2);

        maslins = new Maslina[3];
        olives = new Olive[6];

        for(int i =0; i < maslins.length-1; i++){
            maslins[i] = new Maslina(MathUtils.random(0,128),MathUtils.random(64,256));
        }
        for(int i =0; i < olives.length-1;i++){
            olives[i] = new Olive(MathUtils.random(0,128),MathUtils.random(64,128));

        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()){
            if(Math.abs(Gdx.input.getX()*screenCoef - bandit.getPos().x) < 2){
                //doing nothing ;)
            }
            else if (Gdx.input.getX()*screenCoef  > bandit.getPos().x) {
                bandit.move(+35);
            }
            else if(Gdx.input.getX()*screenCoef < bandit.getPos().x ){
                bandit.move(-35);
            }
        }
    }

    @Override
    public void update(float dt) {
        screenCoef = 128.0/Gdx.graphics.getWidth();
        handleInput();
        for(int i =0; i < maslins.length-1; i++){
            maslins[i].move(35);
            if(maslins[i].getPos().y< 0){
                maslins[i].push();
            }
            if(maslins[i].collide(bandit.getBound())){
                sound.play();
                maslins[i].push();
                gsm.set(new GameOverState(gsm));
            }
        }
        for(int i =0; i < olives.length-1;i++){
            olives[i].move(35);
            if(olives[i].getPos().y < 0){
                olives[i].push();
            }
            if(olives[i].collide(bandit.getBound())){
                bandit.catchOlive();
                olives[i].push();
            }
        }
    }


    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0);
        sb.draw(bandit.getImg(),bandit.getPos().x,bandit.getPos().y);
        txtCounter.draw(sb,"Olives:"+ Bandit.counter,2,60);
        for(int i =0; i < maslins.length-1; i++){
            sb.draw(maslins[i].getImg(),maslins[i].getPos().x,maslins[i].getPos().y);
        }
        for(int i =0; i < olives.length-1;i++){
            sb.draw(olives[i].getImg(),olives[i].getPos().x,olives[i].getPos().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        bandit.dispose();
        music.dispose();
        for(int i =0; i < maslins.length-1; i++){
            maslins[i].dispose();
        }
        for(int i =0; i < olives.length-1;i++){
            olives[i].dispose();
        }
        txtCounter.dispose();
    }
}

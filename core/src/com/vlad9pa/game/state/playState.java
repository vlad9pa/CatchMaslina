package com.vlad9pa.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.vlad9pa.game.sprite.Bandit;
import com.vlad9pa.game.sprite.Maslina;
import com.vlad9pa.game.sprite.Olive;

/**
 * Created by vlad9pa on 5/5/16.
 */
public class playState extends State {

    private Array<Maslina> maslins;
    private Array<Olive> olives;
    private Bandit bandit;
    private Texture background;
    private Music music;
    private BitmapFont txtCounter;

    public playState(GameStateManager gsm) {
        super(gsm);

        bandit.counter = 0;

        txtCounter = new BitmapFont();
        txtCounter.getData().setScale((float) 1.2);

        background = new Texture("background.png");
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.play();

        bandit = new Bandit(200,10);

        maslins = new Array<Maslina>();
        olives = new Array<Olive>();

        Maslina maslina = new Maslina(MathUtils.random(0,800),MathUtils.random(480,500));
        maslins.add(maslina);
        Olive olive = new Olive(MathUtils.random(0,800),MathUtils.random(480,500));
        olives.add(olive);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()){
            if(Math.abs(Gdx.input.getX() - bandit.getPos().x) < 32){
                //doing nothing ;)
            }
            else if (Gdx.input.getX()  > bandit.getPos().x) {
                bandit.move(+200);
            }
            else if(Gdx.input.getX() < bandit.getPos().x ){
                bandit.move(-200);
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        for(int i =0; i < maslins.size-1; i++){
            maslins.get(i).move(200);
            if(maslins.get(i).getPos().x -64 < 0){
                maslins.removeIndex(i);
            }
            if(maslins.get(i).collide(bandit.getBound())){
                maslins.removeIndex(i);
                bandit.getSound().play();
                gsm.set(new GameOverState(gsm));
            }
        }
        for(int i =0; i < olives.size-1;i++){
            olives.get(i).move(200);
            if(olives.get(i).getPos().x - 64 < 0){
                olives.removeIndex(i);
            }
            if(olives.get(i).collide(bandit.getBound())){
                bandit.catchOlive();
                olives.removeIndex(i);
            }
        }

    }


    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0);
        sb.draw(bandit.getImg(),bandit.getPos().x,bandit.getPos().y);
        txtCounter.draw(sb,"Olives:"+bandit.counter,10,450);
        for(Maslina maslina : maslins){
            sb.draw(maslina.getImg(),maslina.getPos().x,maslina.getPos().y);
        }
        for(Olive olive: olives){
            sb.draw(olive.getImg(),olive.getPos().x,olive.getPos().y);
        }
        sb.end();

        if(MathUtils.random(0, 500) == 2){
            Maslina maslina = new Maslina(MathUtils.random(0,800),MathUtils.random(480,500));
            maslins.add(maslina);
        }
        if(MathUtils.random(0,35) == 9){
            Olive olive = new Olive(MathUtils.random(0,800),MathUtils.random(480,500));
            olives.add(olive);
        }
    }

    @Override
    public void dispose() {
        bandit.dispose();
        background.dispose();
        music.dispose();
        txtCounter.dispose();
        for(Olive olive: olives){
            olive.dispose();
        }
        for(Maslina maslina: maslins){
            maslina.dispose();
        }
    }
}

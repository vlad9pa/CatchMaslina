package com.vlad9pa.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by vlad9pa on 5/5/16.
 */
public class Bandit extends Item{

    public static int counter = 0;

    private Texture img;
    private Vector2 pos;
    private Rectangle bound;
    private Sound sound;

    public Bandit(float x,float y) {
        img = new Texture("bandit.png");
        pos = new Vector2(x,y);
        bound = new Rectangle(x,y,img.getWidth(),img.getHeight());
        sound = Gdx.audio.newSound(Gdx.files.internal("maslina.mp3"));
    }

    public void catchOlive(){
        counter++;
    }

    public void catchMaslina(){
        sound.play();
    }

    public void dispose(){
        img.dispose();
        sound.dispose();
    }

    public Texture getImg() {
        return img;
    }

    public void move(float dt){
        pos.x += dt*Gdx.graphics.getDeltaTime();
        bound.setPosition(pos.x,pos.y);
    }

    @Override
    public boolean collide(Rectangle player) {
        return false;
    }

    public Vector2 getPos() {
        return pos;
    }

    public Sound getSound() {
        return sound;
    }

    public Rectangle getBound() {
        return bound;
    }
}

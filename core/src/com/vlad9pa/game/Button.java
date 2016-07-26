package com.vlad9pa.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Button {

    private Texture Button, button_pressed, image;
    private boolean down, touched;
    private Vector2 position;

    public Button(float x, float y){


        down = false;
        touched = false;

        position = new Vector2(x,y);


        Button = new Texture("Button.png");
        button_pressed = new Texture("Button_press.png");
        image = Button;

    }

    public Button(Texture unpressed, Texture pressed, float x, float y){
        this.Button = unpressed;
        this.button_pressed = pressed;

        position = new Vector2(x,y);

    }

    public boolean buttonDown(double x, double y){
        if(Gdx.input.isTouched() && (x > position.x - image.getWidth()/2
                && x < position.x + image.getWidth()/2
        && (y > position.y - image.getHeight()/2 && y < position.y + image.getHeight()/2))){

            image = button_pressed;

            down = true;

            return true;
        }
        else{
            if(down){
                touched = true;

                down = false;

            }
            return false;
        }
    }

    public boolean buttonUp(){
        if(touched){
            image = Button;
            return true;
        }
        else {
            return false;
        }
    }


    public Vector2 getPosition(){ return position; }

    public Texture getImg(){
        return image;
    }

    public void dispose(){
        button_pressed.dispose();
        Button.dispose();
        image.dispose();
    }
}

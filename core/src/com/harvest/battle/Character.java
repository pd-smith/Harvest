package com.harvest.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Patty on 2/14/2016.
 */
public class Character {
    Sprite back_right,front_right, front_left, back_left,curSprite;
    boolean inFront;

    public Character(String backRight,String backLeft, String frontRight, String frontLeft){
        back_right = new Sprite(new Texture(Gdx.files.internal(backRight)));
        front_right = new Sprite(new Texture(Gdx.files.internal(frontRight)));
        back_left = new Sprite(new Texture(Gdx.files.internal(backLeft)));
        front_left = new Sprite(new Texture(Gdx.files.internal(frontLeft)));
        curSprite = back_right;
        inFront = true;
    }

    public Sprite setSprite(int degrees){

        int actual = Math.abs(degrees)%360;
        if(actual < 180){
            if(actual <90){
                curSprite = front_right;
                inFront = false;
                return front_right;
            }else{

                curSprite = front_left;
                inFront = false;
                return front_left;
            }

        }else{
            if(actual < 280){
                curSprite = back_left;
                inFront = true;
                return back_left;
            }
        }
        curSprite = back_right;
        inFront = true;
        return back_right;
    }

    public Sprite getCurrentSprite(){
        return curSprite;
    }

    public boolean isForeground(){
        return inFront;
    }
}

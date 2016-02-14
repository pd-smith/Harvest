package com.harvest.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Patty on 2/14/2016.
 */
public class Character {
    Sprite back_right,front_right, front_left, back_left,curSprite;

    public Character(String backRight,String backLeft, String frontRight, String frontLeft){
        back_right = new Sprite(new Texture(Gdx.files.internal(backRight)));
        front_right = new Sprite(new Texture(Gdx.files.internal(frontRight)));
        back_left = new Sprite(new Texture(Gdx.files.internal(backLeft)));
        front_left = new Sprite(new Texture(Gdx.files.internal(frontLeft)));
        curSprite = back_right;
    }

    public Sprite setSprite(int degrees){
        int actual = degrees%360;
        if(actual < 180){
            if(actual <90){
                curSprite = front_right;
                return front_right;
            }else{

                curSprite = front_left;
                return front_left;
            }

        }else{
            if(actual < 260){
                curSprite = back_left;
                return back_left;
            }
        }
        curSprite = back_right;
        return back_right;
    }

    public Sprite getCurrentSprite(){
        return curSprite;
    }
}

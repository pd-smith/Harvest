package com.harvest.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Patty on 2/14/2016.
 */
public class Character {
    float anim_speed = .20f;
    Vector2 pos;
    TextureRegion[] back_right,front_right, front_left, back_left;
    TextureRegion currentFrame;
    Animation br_idle, fr_idle, fl_idle, bl_idle, animCurr;
    boolean inFront;
    float stateTime;

    public Character(String backRight,String backLeft, String frontRight, String frontLeft){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("Battle/Character/issac.atlas"));
        back_right =  new TextureRegion[3];
        back_right[0] = atlas.findRegion("br",1);
        back_right[1] = atlas.findRegion("br",2);
        back_right[2] = atlas.findRegion("br",3);
        front_right = new TextureRegion[3];
        front_right[0] = atlas.findRegion("fr",1);
        front_right[1] = atlas.findRegion("fr",2);
        front_right[2] = atlas.findRegion("fr",3);
        back_left = new TextureRegion[3];
        back_left[0] = atlas.findRegion("bl",1);
        back_left[1] = atlas.findRegion("bl",2);
        back_left[2] = atlas.findRegion("bl",3);
        front_left = new TextureRegion[3];
        front_left[0] = atlas.findRegion("fl",1);
        front_left[1] = atlas.findRegion("fl",2);
        front_left[2] = atlas.findRegion("fl",3);

        br_idle = new Animation(anim_speed, back_right);
        br_idle.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        bl_idle = new Animation(anim_speed, back_left);
        bl_idle.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        fr_idle = new Animation(anim_speed, front_right);
        fr_idle.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        fl_idle = new Animation(anim_speed, front_left);
        fl_idle.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        animCurr = br_idle;
        currentFrame = animCurr.getKeyFrame(1);
        inFront = true;
        stateTime = 0f;
        pos = new Vector2();
    }

    public TextureRegion setSprite(int degrees){

        int actual = Math.abs(degrees)%360;
        if(actual < 180){
            if(actual <90){
                animCurr = fr_idle;
                inFront = false;
                return animCurr.getKeyFrame(stateTime);
            }else{

                animCurr = fl_idle;
                inFront = false;
                return animCurr.getKeyFrame(stateTime);
            }

        }else{
            if(actual < 270){
                animCurr = bl_idle;
                inFront = true;
                return animCurr.getKeyFrame(stateTime);
            }
        }
        animCurr = br_idle;
        inFront = true;
        return animCurr.getKeyFrame(stateTime);
    }

    public TextureRegion getCurrentTexture(){
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = animCurr.getKeyFrame(stateTime, true);
        System.out.println(currentFrame);
        return currentFrame;
    }

    public void setPos(Vector2 curPos){
        pos = curPos;
    }

    public Vector2 getPos(){
        return pos;
    }

    public boolean isForeground(){
        return inFront;
    }
}

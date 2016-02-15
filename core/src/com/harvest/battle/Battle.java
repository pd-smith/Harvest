package com.harvest.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Patty on 2/14/2016.
 */
public class Battle {

    Party party;
    Sprite background;
    Sprite background2;

    public Battle(){
        party = new Party();
        background = new Sprite(new Texture(Gdx.files.internal("Battle/Battle_Background.gif")));
        background.setPosition(0,0);
        background2 = new Sprite(new Texture(Gdx.files.internal("Battle/Battle_Background.gif")));
        background.setPosition(-1280,0);

    }

    public Vector2 getPositionByDegrees(int degrees){
        double radians = degrees * (BattleVars.PI/180);
        double x = BattleVars.RADIUS_X * Math.cos(radians);
        double y = BattleVars.RADIUS_Y * Math.sin(radians);
        return new Vector2(BattleVars.CENTER_X + (float)x, BattleVars.CENTER_Y + (float)y);
    }

    public Party getParty(){
        return party;
    }

    public Sprite getBackground(){
        return background;
    }
    public Sprite getBackground2(){
        return background2;
    }

    public void moveBackground(){
        background.setX(background.getX()+3.55f);
        if(background.getX() >= 1280 + 1280%3.55){
            background.setX(-1280);
        }
        background2.setX(background2.getX()+3.55f);
        if(background2.getX() >= 1280 + 1280%3.55){
            background2.setX(-1280);
        }

    }
}

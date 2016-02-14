package com.harvest.battle;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Patty on 2/14/2016.
 */
public class Battle {

    Party party;

    public Battle(){
        party = new Party();
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
}

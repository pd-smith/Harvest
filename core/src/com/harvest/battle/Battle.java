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
    boolean movement;
    int partyDegrees, enemyDegrees, degreesToMove, movementDirection, currentDegrees;

    public Battle(){
        party = new Party();
        background = new Sprite(new Texture(Gdx.files.internal("Battle/Battle_Background.gif")));
        background.setPosition(0 + 7,0);
        partyDegrees = 0;
        enemyDegrees = 180;
        background2 = new Sprite(new Texture(Gdx.files.internal("Battle/Battle_Background.gif")));
        background.setPosition(-1280+ 7,0);
        movement = true;
        currentDegrees = 180;
        moveToDegrees(BattleVars.POSITION_PARTY);

    }

    public Vector2 getPositionByDegrees(int degrees){
        double radians = degrees * (BattleVars.PI/180);
        double x = BattleVars.RADIUS_X * Math.cos(radians);
        double y = BattleVars.RADIUS_Y * Math.sin(radians);
        return new Vector2(BattleVars.CENTER_X + (float)x, BattleVars.CENTER_Y + (float)y);
    }

    public void update(){
        handleRotation();
    }

    private void moveToDegrees(int degrees){
        int difference =degrees + currentDegrees;
        int diffNorth = 360-degrees + currentDegrees;
        int direction = BattleVars.ROTATE_CLOCKWISE;
        if(diffNorth < difference){
            direction = BattleVars.ROTATE_COUNTER_CLOCKWISE;
            rotateDegrees(diffNorth,direction);
        }else {
            rotateDegrees(Math.abs(difference), direction);
        }
    }

    private void handleRotation(){
        if(movement){
            if(++currentDegrees > degreesToMove){
                movement = false;
                currentDegrees =0;
                degreesToMove = 0;
            }
            rotate(movementDirection);
        }
    }

    private void rotateDegrees(int degrees, int direction){
        degreesToMove = degrees;
        currentDegrees = 0;
        movementDirection = direction;
        movement = true;
    }

    private void rotate(int direction){
        moveBackground(-direction);
        moveCharacters(direction);
    }

    private void moveCharacters(int direction){
        if(direction > 1){
            direction = 1;
        }

        if(direction < -1){
            direction = -1;
        }

        partyDegrees += direction;
        enemyDegrees += direction;

        if(partyDegrees > 360){
            partyDegrees = 0;
        }
        if(partyDegrees < 0){
            partyDegrees = 360;
        }
        if(enemyDegrees > 360){
            enemyDegrees = 0;
        }
        if(enemyDegrees < 0){
            enemyDegrees = 360;
        }
    }

    private void moveBackground(int direction){
        if(direction > 1){
            direction = 1;
        }
        if(direction < -1){
            direction = -1;
        }
        background.setX(background.getX()+7f * direction);
        if(background.getX() >= 1280 - 1280%7f){
            background.setX(-1280 + 7f);
        }
        if(background.getX() <= -1280 + 1280%7f){
            background.setX(1280 - 7f);
        }
        background2.setX(background2.getX()+7f * direction);
        if(background2.getX() <= -1280 + 1280%7f){
            background2.setX(1280 - 7f);
        }
        if(background2.getX() >= 1280 - 1280%7f){
            background2.setX(-1280 + 7f);
        }

    }

    public int getPartyDegrees(){
        return partyDegrees;
    }

    public int getEnemyDegrees(){
        return enemyDegrees;
    }

    public int getSize(int degrees){
        return (Math.abs(90 - degrees) - ((degrees/270)*((degrees%270)))*2)/4;
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
}

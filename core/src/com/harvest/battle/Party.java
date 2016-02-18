package com.harvest.battle;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Patty on 2/14/2016.
 */
public class Party {
    Character party;
    Character enemy;


    public Party(){
        party = new Character("Battle/Character/Isaac_Back_Right.png","Battle/Character/Isaac_Back_Left.png","Battle/Character/Isaac_Front_Right.png","Battle/Character/Isaac_Front_Left.png");
        enemy = new Character("Battle/Character/Ivan_Staff_Back_Right.png","Battle/Character/Ivan_Staff_Back_Left.png","Battle/Character/Ivan_Staff_Front_Right.png","Battle/Character/Ivan_Staff_Front_Left.png");
    }

    public void setPosition(Vector2 newPosition){
        party.setPos(newPosition);
    }

    public Character getCharacter(){
        return party;
    }

    public Character getEnemy(){
        return enemy;
    }


}

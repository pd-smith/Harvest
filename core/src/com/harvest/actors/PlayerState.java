package com.harvest.actors;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.harvest.game.GameVars;
import com.harvest.game.SaveSnapshot;

/**
 * Created by Patty on 2/1/2016.
 */
public class PlayerState {
    //Wrapper class for all important player information
    int maxCarry, maxFatigue, curFatigue, strength, endurance, intelligence, charm;
    String name;
    Inventory inventory;
    Wallet wallet;

    public PlayerState(String playerName){
        name = playerName; //TODO: cleanup player names

        maxCarry = 5; // have to figure out what this is tied to (stats or equips)

        maxFatigue = 5; // again, not sure what this is going to be tied to (static or increasing with endurance)
        curFatigue = maxFatigue;

        strength = PlayerVars.STAT_START_VALUE;
        endurance = PlayerVars.STAT_START_VALUE;
        intelligence = PlayerVars.STAT_START_VALUE;
        charm = PlayerVars.STAT_START_VALUE;

        inventory = new Inventory(this);
        wallet = new Wallet(PlayerVars.STARTING_MONEY_DOLLARS * PlayerVars.CASH_VALUE);
    }

    /**
     * Add to current fatigue levels.
     * @param amount
     * @return if you recover at least 1 fatigue, return true else false
     */

    public boolean addFatigue(int amount){

        if(curFatigue == maxFatigue){
            return false;
        }

        if((curFatigue+=amount) > maxFatigue){
            curFatigue = maxFatigue;
        }
        return true;
    }

    /**
     * Subtract a given amount of fatigue from current levels.
     * @param amount
     * @return if you have fatigue left over after subtraction
     */

    public boolean subFatigue(int amount){
        if((curFatigue -= amount) <= 0){
            return false;
        }

        return true;
    }

    public boolean increaseStrength(){
        if(++strength > PlayerVars.STAT_MAX_VALUE){
            strength = PlayerVars.STAT_MAX_VALUE;
            return false;
        }
        return true;
    }

    public boolean increaseEndurance(){
        if(++endurance > PlayerVars.STAT_MAX_VALUE){
            endurance = PlayerVars.STAT_MAX_VALUE;
            return false;
        }
        return true;
    }

    public boolean increaseIntelligence(){
        if(++intelligence > PlayerVars.STAT_MAX_VALUE){
            intelligence = PlayerVars.STAT_MAX_VALUE;
            return false;
        }
        return true;
    }

    public boolean increaseCharm(){
        if(++charm > PlayerVars.STAT_MAX_VALUE){
            charm = PlayerVars.STAT_MAX_VALUE;
            return false;
        }
        return true;
    }


    public int getFatigue(){
        return curFatigue;
    }

    public int getStrength(){
        return strength;
    }

    public int getEndurance(){
        return endurance;
    }

    public int getIntelligence(){
        return intelligence;
    }

    public int getCharm(){
        return charm;
    }

    public String getName(){
        return name;
    }

    public Wallet getWallet(){
        return wallet;
    }

    public void loadState(SaveSnapshot snap){
        this.name = snap.getName();
        this.strength = snap.getStrength();
    }

}

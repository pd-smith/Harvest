package com.harvest.game;

/**
 * Created by Patty on 2/5/2016.
 */
public class SaveSnapshot {
    int maxFatigue, curFatigue, strength, endurance, intelligence, charm;
    String name;


    public String getName(){
        return name;
    }

    public int getStrength(){
        return strength;
    }

    public int getMaxFatigue(){
        return maxFatigue;
    }

    public int getFatigue(){
        return curFatigue;
    }

    public int getEndurance(){
        return endurance;
    }

    public int getIntelligence(){
        return intelligence;
    }

    public int getCharm() {
        return charm;
    }
}

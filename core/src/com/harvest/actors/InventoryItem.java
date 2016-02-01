package com.harvest.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Patty on 2/1/2016.
 */
public class InventoryItem {
    //One general class for items now, probably going to convert to abstract later on


    int weight,value;
    String name;
    Sprite sprite;


    public InventoryItem(String itemName, int itemWeight, int itemValue, String path){
        weight = itemWeight;
        value = itemValue;
        name = itemName;
        sprite = new Sprite(new Texture(Gdx.files.internal(path)));
    }

    public String getName(){
        return name;
    }

    public int getWeight(){
        return weight;
    }

    public Sprite getSprite(){
        return sprite;
    }

    public int getValue(){
        return value;
    }

    public void setWeight(int newWeight){
        weight = newWeight;
    }

    public void setValue(int newValue){
        value = newValue;
    }

}

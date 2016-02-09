package com.harvest.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.harvest.hud_elements.HUDVars;

/**
 * Created by Patty on 2/1/2016.
 */
public class InventoryItem {
    //One general class for items now, probably going to convert to abstract later on
    final int WIDTH = 20;
    final int HEIGHT = 20;

    int weight,value;
    String name;
    Sprite sprite;
    Sprite sprBG;
    Sprite sprHighlight;


    public InventoryItem(String itemName, int itemWeight, int itemValue, String path){
        weight = itemWeight;
        value = itemValue;
        name = itemName;
        sprite = new Sprite(new Texture(Gdx.files.internal(path)));
        sprBG = new Sprite(new Texture(Gdx.files.internal(HUDVars.INVENTORY_BACKGROUND_PATH)));
        sprHighlight = new Sprite(new Texture(Gdx.files.internal(HUDVars.INVENTORY_HIGHLIGHT_PATH)));

        sprite.setSize(WIDTH,HEIGHT);

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

    public Sprite getBackground(){
        return sprBG;
    }

    public Sprite getHighlight(){
        return sprHighlight;
    }

}

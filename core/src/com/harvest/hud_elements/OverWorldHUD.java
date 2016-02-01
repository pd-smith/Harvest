package com.harvest.hud_elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.harvest.game.GameDriver;

import java.util.ArrayList;

/**
 * Created by Patty on 1/25/2016.
 */
public class OverWorldHUD{

    ArrayList<Sprite> hudElements;
    GameDriver game;
    boolean hideStats;
    DayCycle dayCycleClock;
    int updateCount;


    public OverWorldHUD(GameDriver game){
        this.game = game;
        hudElements = new ArrayList<Sprite>();
        dayCycleClock = new DayCycle();
        buildDefaultHUD();
        updateCount = 0;
        hideStats = true;
    }

    public void addElement(Sprite sprite){
        //Should we tuple these with a toggle boolean?

        hudElements.add(sprite);
    }

    public ArrayList<Sprite> getHudElements(){
        return hudElements;
    }

    public void buildDefaultHUD(){
        Sprite sprite = new Sprite(new Texture(Gdx.files.internal("hud_top_right.png")));
        sprite.setX(game.GAME_WIDTH - sprite.getWidth());
        sprite.setY(0);
        hudElements.add(sprite);

        sprite = new Sprite(new Texture(Gdx.files.internal("Stats_HUD.png")));
        sprite.setX(200);
        sprite.setY(100); //change with Constants later


        hudElements.add(sprite);
   }

    public void update(){
        if(HUDVars.CLOCK_UPDATE == ++updateCount){
            dayCycleClock.update();
            updateCount = 0;
        }
    }

    public void toggleStatsHide(){
        hideStats = !hideStats; //maybe replace with hide/show later. Don't like using toggle
    }

    public DayCycle getClock() {
        return dayCycleClock;
    }


}

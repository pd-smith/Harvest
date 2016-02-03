package com.harvest.hud_elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    HUDFonts fonts;
    StatsCard statsCard;
    PauseCard pauseCard;


    public OverWorldHUD(GameDriver game){
        this.game = game;
        hudElements = new ArrayList<Sprite>();
        dayCycleClock = new DayCycle();
        buildDefaultHUD();
        updateCount = 0;
        hideStats = true;
        fonts = new HUDFonts();
        statsCard = new StatsCard();
        pauseCard = new PauseCard();
    }

    public void addElement(Sprite sprite){
        //Should we tuple these with a toggle boolean?

        hudElements.add(sprite);
    }

    public ArrayList<Sprite> getHudElements(){
        return hudElements;
    }

    public void buildDefaultHUD(){
        Sprite sprite = new Sprite(new Texture(Gdx.files.internal(HUDVars.OVERLAY_CLOCK_BACKGROUND_PATH)));
        sprite.setX(game.GAME_WIDTH - sprite.getWidth());
        sprite.setY(0);
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

    public HUDFonts getHUDFonts(){
        return fonts;
    }

    public StatsCard getStatsCard(){
        return statsCard;
    }

    public PauseCard getPauseCard(){
        return pauseCard;
    }


}

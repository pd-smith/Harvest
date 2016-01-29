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
    DayCycle dayCycleClock;


    public OverWorldHUD(GameDriver game){
        this.game = game;
        hudElements = new ArrayList<Sprite>();
        dayCycleClock = new DayCycle();
        buildDefaultHUD();
    }

    public void addElement(Sprite sprite){
        hudElements.add(sprite);
    }

    public ArrayList<Sprite> getHudElements(){
        return hudElements;
    }

    public void buildDefaultHUD(){
        Sprite sprite = new Sprite(new Texture(Gdx.files.internal("hud_top_right.png")));
        sprite.setX(game.GAME_WIDTH - sprite.getWidth());
        sprite.setY(game.GAME_HEIGHT - sprite.getHeight());
        hudElements.add(sprite);
        sprite = new Sprite(new Texture(Gdx.files.internal("test_weather.png")));
        sprite.setX(game.GAME_WIDTH - sprite.getWidth());
        sprite.setY(game.GAME_HEIGHT - sprite.getHeight());
        hudElements.add(sprite);
    }

    public DayCycle getClock() {
        return dayCycleClock;
    }
}

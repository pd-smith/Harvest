package com.harvest.hud_elements;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by Patty on 2/2/2016.
 */
public class HUDFonts {

    BitmapFont clockFont;
    BitmapFont statsFont;



    public HUDFonts(){
        clockFont = new BitmapFont();
        statsFont = new BitmapFont();
    }


    public BitmapFont getClockFont(){
        return clockFont;
    }

    public BitmapFont getStatsFont(){
        return statsFont;
    }
}

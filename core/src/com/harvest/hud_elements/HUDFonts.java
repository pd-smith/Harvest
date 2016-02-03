package com.harvest.hud_elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by Patty on 2/2/2016.
 */
public class HUDFonts {

    BitmapFont clockFont;
    BitmapFont statsFont;
    BitmapFont statNameFont;



    public HUDFonts(){
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(HUDVars.FONT_UI_PATH));
        FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameters.size = 12;
        clockFont = fontGenerator.generateFont(parameters);

        parameters.size = 20;
        statsFont = fontGenerator.generateFont(parameters);

        parameters.size = 50;
        statNameFont = fontGenerator.generateFont(parameters);
        fontGenerator.dispose();

    }


    public BitmapFont getClockFont(){
        return clockFont;
    }

    public BitmapFont getStatsFont(){
        return statsFont;
    }

    public BitmapFont getStatNameFont() {
        return statNameFont;
    }
}

package com.harvest.hud_elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.harvest.game.GameDriver;

/**
 * Created by Patty on 2/3/2016.
 */
public class PauseCard {
    final int TOTAL_OPTIONS = 3, MAIN_MENU_INDEX = 0, OPTIONS_INDEX = 1, SAVE_INDEX = 2;

    Texture greyedOut;
    GameDriver game;
    int index;
    boolean show;
    Sprite main_menu, options, save, highlight;

    public PauseCard(GameDriver game){
        int paddingY = 10;
        this.game = game;
        index = 0;
        greyedOut = new Texture(Gdx.files.internal(HUDVars.PAUSE_GREY_PATH));
        main_menu = new Sprite(new Texture(Gdx.files.internal(HUDVars.PAUSE_ITEM_MAIN_MENU_PATH)));
        main_menu.setPosition(500,450);
        options = new Sprite(new Texture(Gdx.files.internal(HUDVars.PAUSE_ITEM_OPTIONS_PATH)));
        options.setPosition(500,450 - (options.getHeight() + paddingY));
        save = new Sprite(new Texture(Gdx.files.internal(HUDVars.PAUSE_ITEM_SAVE_PATH)));
        save.setPosition(500,450 - (save.getHeight() + paddingY) * 2);
        highlight = new Sprite(new Texture(Gdx.files.internal(HUDVars.PAUSE_ITEM_HIGHLIGHT_PATH)));
        highlight.setPosition(main_menu.getX(),main_menu.getY());
        show = false;
    }

    public Texture getGreyedOut(){
        return greyedOut;
    }

    public Sprite getMainMenu(){
        return main_menu;
    }

    public Sprite getOptions(){
        return options;
    }

    public Sprite getSave(){
        return save;
    }

    public Sprite getHighlight(){
        return highlight;
    }

    public void moveOptionDown(){
        if(++ index >= TOTAL_OPTIONS){
            index = 0;
        }
        updateHighlight(index);
    }

    public void moveOptionsUp(){
        if(-- index < 0){
            index = TOTAL_OPTIONS - 1;
        }
        updateHighlight(index);
    }

    public void updateHighlight(int index){
        switch (index){
            case MAIN_MENU_INDEX:
                highlight.setPosition(main_menu.getX(),main_menu.getY());
                break;
            case OPTIONS_INDEX:
                highlight.setPosition(options.getX(),options.getY());
                break;
            case SAVE_INDEX:
                highlight.setPosition(save.getX(),save.getY());
                break;
        }
    }

    public void onSelect(){
        switch(index){
            case MAIN_MENU_INDEX:
                //System.out.println("Prompt if you want to save, then go to main menu");
                game.loadGame();
                break;
            case OPTIONS_INDEX:
                System.out.println("Go to Options Menu.");
                break;
            case SAVE_INDEX:
                game.saveGame();
                System.out.println("Go to Save Function.");
                break;
        }
    }

    public boolean canShow(){
        return show;
    }

    public void showPause(){
        show = true;
    }

    public void hidePause(){
        show = false;
    }
}

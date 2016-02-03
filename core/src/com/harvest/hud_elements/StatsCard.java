package com.harvest.hud_elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Patty on 2/3/2016.
 */
public class StatsCard {
    public Vector2 pos_card,pos_str,pos_int,pos_end,pos_chm,pos_name,pos_mny;
    boolean show;
    Sprite card;

    public StatsCard(){
        int screenHeight = 720, screenWidth = 1280;
        int nameLeft = 80,nameCeiling = 630;
        int statSpacingY = -25, statPaddingX = 900, statPaddingY = -500;


        show = false;
        card = new Sprite(new Texture(Gdx.files.internal(HUDVars.STATS_OVERLAY_PATH)));
        pos_card = new Vector2(0,0);
        pos_name = new Vector2(nameLeft,nameCeiling);
        pos_str = new Vector2(statPaddingX,screenHeight + (statSpacingY) + statPaddingY);
        pos_int = new Vector2(statPaddingX,screenHeight + (statSpacingY * 2) + statPaddingY);
        pos_end = new Vector2(statPaddingX,screenHeight + (statSpacingY * 3) + statPaddingY);
        pos_chm = new Vector2(statPaddingX,screenHeight + (statSpacingY * 4) + statPaddingY);
        pos_mny = new Vector2(statPaddingX,screenHeight + (statSpacingY * 6) + statPaddingY);

        card.setPosition(pos_card.x,pos_card.y);

    }

    public boolean canShow(){
        return show;
    }

    public void showStats(){
        show = true;
    }

    public void hideStats(){
        show = false;
    }

    public Sprite getCard(){
        return card;
    }

}

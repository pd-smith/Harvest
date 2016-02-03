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
        int topLeft = 210,topCeiling = 585, statSpacingY = -50;
        show = false;
        card = new Sprite(new Texture(Gdx.files.internal("Stats_HUD.png")));
        pos_card = new Vector2(200,100);
        pos_name = new Vector2(topLeft,topCeiling);
        pos_str = new Vector2(topLeft,topCeiling + (statSpacingY));
        pos_int = new Vector2(topLeft,topCeiling + (statSpacingY * 2));
        pos_end = new Vector2(topLeft,topCeiling + (statSpacingY * 3));
        pos_chm = new Vector2(topLeft,topCeiling + (statSpacingY * 4));
        pos_mny = new Vector2(topLeft,topCeiling + (statSpacingY * 6));

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

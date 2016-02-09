package com.harvest.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.harvest.actors.InventoryItem;
import com.harvest.actors.PlayerOverWorld;
import com.harvest.actors.PlayerState;
import com.harvest.environment.overworld.Map;
import com.harvest.game.GameDriver;
import com.harvest.hud_elements.HUDFonts;
import com.harvest.hud_elements.HUDVars;
import com.harvest.hud_elements.OverWorldHUD;
import com.sun.jmx.snmp.SnmpPduRequestType;


/**
 * Created by Patty on 1/23/2016.
 */
public class SceneOverWorld implements Screen{

    OverWorldHUD hud;
    SpriteBatch hudbatch;
    GameDriver _game;
    Stage stage;
    PlayerOverWorld player;
    Sound bgMusic;
    public World world;
    Map map;
    HUDFonts fonts; // maybe put this a little higher up

    //this is messy, but can only be cleaned after we solidify 'direction' of code

    public SceneOverWorld(GameDriver game){
        _game = game;
        hudbatch = new SpriteBatch();
        world = new World(new Vector2(), true);
        stage = new Stage(_game.viewport);
        Gdx.input.setInputProcessor(stage);

        map = new Map("Maps/Test/housemap.tmx",new int[]{0},new int[]{1},new int[]{2});
        hud = new OverWorldHUD(game);
        player = new PlayerOverWorld(this, map, hud);
        //stage.addActor(new Rock(this));
        player.setX(512f); // multiple of tile width-1
        player.setY(1200f);
        stage.addActor(player);

        bgMusic = Gdx.audio.newSound(Gdx.files.internal("Audio/Music/04-spring-theme.mp3"));
        //bgMusic.play(.5f);
        //bgMusic.loop(.5f);

    }



    public void update(){
        if(!hud.getPauseCard().canShow()) {
            hud.update();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();

        map.getRenderer().setView(_game.cam);
        map.getRenderer().render(map.getBackground());
        map.getRenderer().render(map.getCollision());

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        map.getRenderer().render(map.getForeground());

        hudbatch.begin();

        //DRAW TIME OF DAY TINT

        hudbatch.draw(hud.getClock().getTimeOfDayTint(), 0, 0);

        //DRAW CLOCK
        for(Sprite sprite: hud.getHudElements()){
            hudbatch.draw(sprite,sprite.getX(),sprite.getY());
        }

        if(hud.getClock() != null){
            hud.getHUDFonts().getClockFont().draw(hudbatch,hud.getClock().getCurrentTime(),hud.getClock().getPosition().x,hud.getClock().getPosition().y);
            hud.getHUDFonts().getClockFont().draw(hudbatch,hud.getClock().getTimeOfDay(),hud.getClock().getPosition().x + 125,hud.getClock().getPosition().y);

            hud.getHUDFonts().getClockFont().draw(hudbatch,hud.getClock().getMonthName(),hud.getClock().getPosition().x,hud.getClock().getPosition().y+12);
            hud.getHUDFonts().getClockFont().draw(hudbatch,hud.getClock().getDay() + "" ,hud.getClock().getPosition().x + 45,hud.getClock().getPosition().y+12);
            hud.getHUDFonts().getClockFont().draw(hudbatch,hud.getClock().getDayOfWeek(),hud.getClock().getPosition().x + 125,hud.getClock().getPosition().y+12);

        }

        //DRAW STATS CARD
        PlayerState tempState = player.getPlayerState();
        if(hud.getStatsCard().canShow()) {
            hudbatch.draw(hud.getStatsCard().getCard(), hud.getStatsCard().pos_card.x,hud.getStatsCard().pos_card.y);
            hud.getHUDFonts().getStatNameFont().draw(hudbatch, tempState.getName(), hud.getStatsCard().pos_name.x, hud.getStatsCard().pos_name.y);
            hud.getHUDFonts().getStatsFont().draw(hudbatch, "Strength: " + tempState.getStrength(), hud.getStatsCard().pos_str.x, hud.getStatsCard().pos_str.y);
            hud.getHUDFonts().getStatsFont().draw(hudbatch, "Endurance: " + tempState.getEndurance(), hud.getStatsCard().pos_end.x, hud.getStatsCard().pos_end.y);
            hud.getHUDFonts().getStatsFont().draw(hudbatch, "Intelligence: " + tempState.getIntelligence(), hud.getStatsCard().pos_int.x, hud.getStatsCard().pos_int.y);
            hud.getHUDFonts().getStatsFont().draw(hudbatch, "Charm: " + tempState.getCharm(), hud.getStatsCard().pos_chm.x, hud.getStatsCard().pos_chm.y);
            hud.getHUDFonts().getStatsFont().draw(hudbatch, tempState.getWallet().getWalletAmount(), hud.getStatsCard().pos_mny.x, hud.getStatsCard().pos_mny.y);
            float pathag= (HUDVars.INVENTORY_ITEM_BACKGROUND_SIZE- HUDVars.INVENTORY_ITEM_SIZE)/2;
            int itemIndex = 0;
            for(InventoryItem item: tempState.getInventory().getList()){
                hudbatch.draw(item.getBackground(), HUDVars.INVENTORY_START_X + (itemIndex%HUDVars.INVENTORY_ROW_SIZE * (HUDVars.INVENTORY_ITEM_SIZE + HUDVars.INVENTORY_ITEM_PADDING_X)),HUDVars.INVENTORY_START_Y - (itemIndex/HUDVars.INVENTORY_ROW_SIZE * (HUDVars.INVENTORY_ITEM_SIZE + HUDVars.INVENTORY_ITEM_PADDING_Y)),HUDVars.INVENTORY_ITEM_BACKGROUND_SIZE, HUDVars.INVENTORY_ITEM_BACKGROUND_SIZE);
                hudbatch.draw(item.getSprite(), HUDVars.INVENTORY_START_X + pathag + (itemIndex%HUDVars.INVENTORY_ROW_SIZE * (HUDVars.INVENTORY_ITEM_SIZE + HUDVars.INVENTORY_ITEM_PADDING_X)),HUDVars.INVENTORY_START_Y + pathag - (itemIndex/HUDVars.INVENTORY_ROW_SIZE * (HUDVars.INVENTORY_ITEM_SIZE + HUDVars.INVENTORY_ITEM_PADDING_Y)),HUDVars.INVENTORY_ITEM_SIZE, HUDVars.INVENTORY_ITEM_SIZE);
                itemIndex ++;
            }

        }

        //DRAW PAUSE MENU
        if(hud.getPauseCard().canShow()){
            hudbatch.draw(hud.getPauseCard().getGreyedOut(), 0,0);
            hudbatch.draw(hud.getPauseCard().getMainMenu(),hud.getPauseCard().getMainMenu().getX(),hud.getPauseCard().getMainMenu().getY());
            hudbatch.draw(hud.getPauseCard().getOptions(),hud.getPauseCard().getOptions().getX(),hud.getPauseCard().getOptions().getY());
            hudbatch.draw(hud.getPauseCard().getSave(),hud.getPauseCard().getSave().getX(),hud.getPauseCard().getSave().getY());
            hudbatch.draw(hud.getPauseCard().getHighlight(),hud.getPauseCard().getHighlight().getX(),hud.getPauseCard().getHighlight().getY());
        }

        hudbatch.end();
        _game.cam.position.set(player.getX(),player.getY(),0);
        _game.cam.update();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        bgMusic.dispose();
    }

    public PlayerOverWorld getPlayer(){
        return player;
    }
}

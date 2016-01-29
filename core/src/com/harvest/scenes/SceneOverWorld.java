package com.harvest.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.harvest.actors.PlayerOverWorld;
import com.harvest.environment.overworld.Map;
import com.harvest.game.GameDriver;
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
    Image background;
    Sound bgMusic;
    public World world;
    Box2DDebugRenderer debugRenderer;
    Map map;

    //this is messy, but can only be cleaned after we solidify 'direction' of code

    public SceneOverWorld(GameDriver game){
        _game = game;
        hudbatch = new SpriteBatch();
        world = new World(new Vector2(), true);
        stage = new Stage(_game.viewport);
        Gdx.input.setInputProcessor(stage);

        map = new Map("housemap.tmx",new int[]{0},new int[]{1},new int[]{2});
        player = new PlayerOverWorld(this, map);
        //stage.addActor(new Rock(this));
        player.setX(512f); // multiple of tile width-1
        player.setY(1200f);
        stage.addActor(player);

        bgMusic = Gdx.audio.newSound(Gdx.files.internal("04-spring-theme.mp3"));
        bgMusic.play(.5f);
        bgMusic.loop(.5f);

        hud = new OverWorldHUD(game);





    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        map.getRenderer().setView(_game.cam);
        map.getRenderer().render(map.getBackground());
        map.getRenderer().render(map.getCollision());

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        map.getRenderer().render(map.getForeground());

        hudbatch.begin();
        for(Sprite sprite: hud.getHudElements()){
            hudbatch.draw(sprite,sprite.getX(),sprite.getY());
        }
        if(hud.getClock() != null){
            hud.getClock().getFont().draw(hudbatch,hud.getClock().getCurrentTime(),hud.getClock().getPosition().x,hud.getClock().getPosition().y);
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
}

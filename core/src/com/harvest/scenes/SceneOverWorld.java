package com.harvest.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.harvest.actors.PlayerOverWorld;
import com.harvest.environment.overworld.Map;
import com.harvest.environment.overworld.Rock;
import com.harvest.game.GameDriver;


/**
 * Created by Patty on 1/23/2016.
 */
public class SceneOverWorld implements Screen{

    GameDriver _game;
    Stage stage;
    PlayerOverWorld player;
    Image background;
    Sound bgMusic;
    public World world;
    Box2DDebugRenderer debugRenderer;
    Map map;

    public SceneOverWorld(GameDriver game){
        _game = game;
        world = new World(new Vector2(), true);
        stage = new Stage(_game.viewport);
        Gdx.input.setInputProcessor(stage);
        //background = new Image(new Texture(Gdx.files.internal("farm.png")));
        //stage.addActor(background);
        player = new PlayerOverWorld(this);
        //stage.addActor(new Rock(this));
        player.setX(500f);
        player.setY(1400f);
        stage.addActor(player);

        bgMusic = Gdx.audio.newSound(Gdx.files.internal("04-spring-theme.mp3"));
        bgMusic.play(.5f);
        bgMusic.loop(.5f);
        debugRenderer = new Box2DDebugRenderer();

        map = new Map("housemap.tmx");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(1/60f, 6, 2);

        map.getRenderer().setView(_game.cam);
        map.getRenderer().render();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();



        debugRenderer.render(world, _game.debugCam.combined);
        _game.debugCam.update();
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

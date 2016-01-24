package com.harvest.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.harvest.actors.PlayerOverWorld;
import com.harvest.game.GameDriver;


/**
 * Created by Patty on 1/23/2016.
 */
public class SceneMenu implements Screen{

    GameDriver _game;
    Stage stage;
    PlayerOverWorld player;
    Image background;


    public SceneMenu(GameDriver game){
        _game = game;
        stage = new Stage(_game.viewport);
        Gdx.input.setInputProcessor(stage);
        background = new Image(new Texture(Gdx.files.internal("farm.png")));
        stage.addActor(background);
        player = new PlayerOverWorld();
        stage.addActor(player);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

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

    }
}

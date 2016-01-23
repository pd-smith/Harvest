package com.harvest.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.harvest.actors.PlayerOverWorld;
import com.harvest.game.GameDriver;

/**
 * Created by Patty on 1/23/2016.
 */
public class SceneMenu implements Screen{

    GameDriver _game;
    Stage stage;


    public SceneMenu(GameDriver game){
        _game = game;
        stage = new Stage(game.viewport);
        stage.addActor(new PlayerOverWorld());
        Gdx.input.setInputProcessor(stage);
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

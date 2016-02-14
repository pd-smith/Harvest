package com.harvest.scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.harvest.battle.Battle;
import com.harvest.game.GameDriver;

/**
 * Created by Patty on 2/12/16.
 */
public class SceneBattle implements Screen {
    SpriteBatch battleBatch;
    GameDriver game;
    Stage stage;
    public World world;
    Battle battle;
    int degrees = 0;


    public SceneBattle(GameDriver currentGame){
        game = currentGame;
        battleBatch = new SpriteBatch();
        world = new World(new Vector2(), true);
        stage = new Stage(game.viewport);
        Gdx.input.setInputProcessor(stage);

        battle = new Battle();
    }

    public void update(){
        battle.getParty().getCharacter().setSprite(degrees);
        battle.getParty().getEnemy().setSprite((degrees-180));
        Vector2 posEnemy = battle.getPositionByDegrees((degrees-180));
        Vector2 pos = battle.getPositionByDegrees(degrees++);

        battle.getParty().getCharacter().getCurrentSprite().setPosition(pos.x,pos.y);
        battle.getParty().getEnemy().getCurrentSprite().setPosition(posEnemy.x,posEnemy.y);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update();
        battleBatch.begin();
        battleBatch.draw(battle.getParty().getCharacter().getCurrentSprite(),battle.getParty().getCharacter().getCurrentSprite().getX(),battle.getParty().getCharacter().getCurrentSprite().getY());
        battleBatch.draw(battle.getParty().getEnemy().getCurrentSprite(),battle.getParty().getEnemy().getCurrentSprite().getX(),battle.getParty().getEnemy().getCurrentSprite().getY());
        battleBatch.end();
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

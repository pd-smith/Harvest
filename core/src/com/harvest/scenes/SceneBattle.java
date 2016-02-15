package com.harvest.scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.harvest.battle.Battle;
import com.harvest.battle.BattleVars;
import com.harvest.game.GameDriver;

/**
 * Created by Patty on 2/12/16.
 */
public class SceneBattle implements Screen {
    SpriteBatch battleBatch;
    GameDriver game;
    Stage stage;
    Sound bgMusic;
    public World world;
    Battle battle;
    int degrees = 0,degrees2 = 180, sizeDeductionChar = degrees - 180, sizeDeductionEnem = degrees2 - 180;



    public SceneBattle(GameDriver currentGame){
        game = currentGame;
        battleBatch = new SpriteBatch();
        world = new World(new Vector2(), true);
        stage = new Stage(game.viewport);
        Gdx.input.setInputProcessor(stage);

        battle = new Battle();
        bgMusic = Gdx.audio.newSound(Gdx.files.internal("Audio/Music/battle.mp3"));
        bgMusic.play(.5f);
        bgMusic.loop(.5f);
    }

    public void update(){
        battle.getParty().getCharacter().setSprite(degrees);
        battle.getParty().getEnemy().setSprite((degrees2));
        Vector2 posEnemy = battle.getPositionByDegrees((degrees2));
        Vector2 pos = battle.getPositionByDegrees(degrees);
        degrees -= 1;
        degrees2 -= 1;
        if(degrees > 360){
            degrees = 0;
        }
        if(degrees < 0){
            degrees = 360;
        }
        if(degrees2 > 360){
            degrees2 = 0;
        }
        if(degrees2 < 0){
            degrees2 = 360;
        }
        battle.getParty().getCharacter().getCurrentSprite().setPosition(pos.x,pos.y);
        battle.getParty().getEnemy().getCurrentSprite().setPosition(posEnemy.x,posEnemy.y);
        battle.moveBackground();
        sizeDeductionChar = (Math.abs(90 - degrees) - ((degrees/270)*((degrees%270)))*2)/4;
        sizeDeductionEnem = (Math.abs(90 - degrees2) - ((degrees2/270)*((degrees2%270)))*2)/4;
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
        battleBatch.draw(battle.getBackground(),battle.getBackground().getX(),battle.getBackground().getY(),1280,720);
        battleBatch.draw(battle.getBackground2(),battle.getBackground2().getX(),battle.getBackground2().getY(),1280,720);

        drawBackground();
        drawForeground();

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

    public void drawForeground(){
        if(battle.getParty().getCharacter().isForeground())
            battleBatch.draw(battle.getParty().getCharacter().getCurrentSprite(),battle.getParty().getCharacter().getCurrentSprite().getX(),battle.getParty().getCharacter().getCurrentSprite().getY(), BattleVars.CHARACTER_SIZE + sizeDeductionChar,BattleVars.CHARACTER_SIZE + sizeDeductionChar);
        if(battle.getParty().getEnemy().isForeground())
            battleBatch.draw(battle.getParty().getEnemy().getCurrentSprite(),battle.getParty().getEnemy().getCurrentSprite().getX(),battle.getParty().getEnemy().getCurrentSprite().getY(),BattleVars.CHARACTER_SIZE + sizeDeductionEnem,BattleVars.CHARACTER_SIZE + sizeDeductionEnem);
    }

    public void drawBackground() {
        if(!battle.getParty().getCharacter().isForeground())
            battleBatch.draw(battle.getParty().getCharacter().getCurrentSprite(), battle.getParty().getCharacter().getCurrentSprite().getX(), battle.getParty().getCharacter().getCurrentSprite().getY(), BattleVars.CHARACTER_SIZE + sizeDeductionChar, BattleVars.CHARACTER_SIZE + sizeDeductionChar);
        if(!battle.getParty().getEnemy().isForeground())
            battleBatch.draw(battle.getParty().getEnemy().getCurrentSprite(), battle.getParty().getEnemy().getCurrentSprite().getX(), battle.getParty().getEnemy().getCurrentSprite().getY(), BattleVars.CHARACTER_SIZE + sizeDeductionEnem, BattleVars.CHARACTER_SIZE + sizeDeductionEnem);
    }
}

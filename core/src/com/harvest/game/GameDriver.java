package com.harvest.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.harvest.actors.PlayerOverWorld;
import com.harvest.actors.PlayerState;
import com.harvest.effects.Transition;
import com.harvest.scenes.SceneBattle;
import com.harvest.scenes.SceneOverWorld;

public class GameDriver extends Game {
	SceneOverWorld menu;
	SceneBattle battle;
	Transition transition;
	public OrthographicCamera cam;


	public final float GAME_WIDTH = 1280;
	public final float GAME_HEIGHT = 720;
	PlayerOverWorld player;
	public Viewport viewport;
	
	@Override
	public void create () {

		float h = Gdx.graphics.getHeight();
		float w = Gdx.graphics.getWidth();
		float aspectRatio = (h/w);
		cam = new OrthographicCamera();
		viewport = new FitViewport(GAME_WIDTH * aspectRatio,GAME_HEIGHT * aspectRatio,cam);
		viewport.apply();
		transition = new Transition(this);
		cam.position.set(GAME_WIDTH/2,GAME_HEIGHT/2,0);
		battle = new SceneBattle(this);

		menu = new SceneOverWorld(this);
		setMainMenu();
		player = menu.getPlayer();
	}

	public void setMainMenu(){
		setScreen(menu);
	}

	public void setBattle(){
		battle.enterBattle();
		setScreen(battle);
	}

	public void saveGame(){
		FileHandle fileHandle = Gdx.files.local(GameVars.SAVE_FILE_PATH);
		Json json = new Json();
		SaveSnapshot save = new SaveSnapshot();
		//<add all values to snapshot that you want to save>
		save.name = player.getPlayerState().getName();
		save.strength = player.getPlayerState().getStrength();
		save.intelligence = player.getPlayerState().getIntelligence();
		save.endurance = player.getPlayerState().getEndurance();
		save.charm = player.getPlayerState().getCharm();
		save.curFatigue = player.getPlayerState().getFatigue();
		save.maxFatigue = player.getPlayerState().getMaxFatigue();
		//</add all values to snapshot that you want to save>
		System.out.println("Saving.....");
		fileHandle.writeString(json.toJson(save), false);
		System.out.println("Save Complete!");

	}

	public void loadGame(){
		FileHandle fileHandle = Gdx.files.local(GameVars.SAVE_FILE_PATH);
		System.out.println("Loading....");
		Json json = new Json();
		String output = fileHandle.readString();
		player.getPlayerState().loadState(json.fromJson(SaveSnapshot.class,output));
		System.out.println("Load Complete!");
	}

	public Transition getTransition(){
		return transition;
	}
}

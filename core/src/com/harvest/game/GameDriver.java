package com.harvest.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.harvest.scenes.SceneMenu;

public class GameDriver extends Game {
	SpriteBatch batch;
	SceneMenu menu;
	Texture img;
	public OrthographicCamera cam;

	final float GAME_WIDTH = 1280;
	final float GAME_HEIGHT = 720;

	public Viewport viewport;
	
	@Override
	public void create () {

		float h = Gdx.graphics.getHeight();
		float w = Gdx.graphics.getWidth();
		float aspectRatio = (h/w);

		cam = new OrthographicCamera();
		viewport = new FitViewport(GAME_WIDTH * aspectRatio,GAME_HEIGHT * aspectRatio,cam);
		viewport.apply();

		cam.position.set(GAME_WIDTH/2,GAME_HEIGHT/2,0);
		//img = new Texture("badlogic.jpg");

		menu = new SceneMenu(this);


		setMainMenu();
	}

	public void setMainMenu(){
		setScreen(menu);
	}
}

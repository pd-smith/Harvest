package com.harvest.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.harvest.game.GameDriver;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Harvest";
		config.height = 720;
		config.width = 1280;
		//config.resizable = false;

		System.out.println("Here");
		new LwjglApplication(new GameDriver(), config);
	}
}

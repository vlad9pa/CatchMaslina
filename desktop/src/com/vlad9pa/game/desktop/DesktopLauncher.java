package com.vlad9pa.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.vlad9pa.game.catchMaslina;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = catchMaslina.TITLE;
		config.height = catchMaslina.HEIGHT;
		config.width = catchMaslina.WIDTH;
		new LwjglApplication(new catchMaslina(), config);
	}
}

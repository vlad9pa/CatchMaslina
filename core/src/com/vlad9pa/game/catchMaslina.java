package com.vlad9pa.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vlad9pa.game.sprite.Maslina;
import com.vlad9pa.game.sprite.Olive;
import com.vlad9pa.game.state.GameStateManager;
import com.vlad9pa.game.state.MenuState;

public class catchMaslina extends ApplicationAdapter {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;
	public static final String TITLE = "CatchMaslina";

	private GameStateManager gsm;

	private SpriteBatch sb;
	
	@Override
	public void create () {
		gsm = new GameStateManager();
		sb = new SpriteBatch();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));

	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(sb);
	}
}

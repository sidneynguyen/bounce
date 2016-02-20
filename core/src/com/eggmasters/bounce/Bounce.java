package com.eggmasters.bounce;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eggmasters.bounce.states.GameStateManager;
import com.eggmasters.bounce.states.MenuGameState;

public class Bounce extends ApplicationAdapter {
	public static final int WIDTH = 1080;
	public static final int HEIGHT = 1920;

	private GameStateManager gameStateManager;
	private SpriteBatch spriteBatch;

	@Override
	public void create() {

		gameStateManager = new GameStateManager();
		gameStateManager.push(new MenuGameState(gameStateManager));
		spriteBatch = new SpriteBatch();
		Gdx.gl.glClearColor(0, 0, 1, 1);
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(spriteBatch);
	}

	@Override
	public void dispose() {
		super.dispose();
		gameStateManager.dispose();
		System.out.println("Bounce disposed");
	}


}

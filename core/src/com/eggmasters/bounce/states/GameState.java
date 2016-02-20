/*
 * Carnage Games
 * December 18, 2015
 * State.java
 *
 * Game state
 */

package com.eggmasters.bounce.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.eggmasters.bounce.Bounce;

/**
 * An abstract class representing a game GameState.
 */
public abstract class GameState {

    protected GameStateManager gsm;
    protected OrthographicCamera cam;
    protected Viewport viewport;

    /**
     * Constructs a game GameState with a camera.
     * @param gsm GameStateManager to update game States
     */
    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        viewport = new StretchViewport(Bounce.WIDTH, Bounce.HEIGHT, cam);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();

} // End of public class GameState

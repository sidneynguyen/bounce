/*
 * Carnage Games
 * December 18, 2015
 * MenuState.java
 *
 * Main menu
 */

package com.eggmasters.bounce.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A class creating the main menu of the game.
 */
public class MenuGameState extends GameState {

    private static final float HIGHSCORE_TEXT_X = 0.1f;
    private static final float HIGHSCORE_TEXT_Y = 0.1f;

    private Texture background;
    private Texture playBtn;
    private int highScore;
    private BitmapFont highScoreText;
    private Preferences hs;

    /**
     * Constructs a MenuGameState that initializes the menu graphics and camera.
     * @param gsm GameStateManager
     */
    public MenuGameState(GameStateManager gsm) {
        super(gsm);
        //cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background = new Texture("background.png");
        playBtn = new Texture("playBtn.png");
        hs = Gdx.app.getPreferences("highscore");
        highScore = hs.getInteger("highscore", 0);
        highScoreText = new BitmapFont();
        highScoreText.setColor(Color.BLUE);
        highScoreText.getData().scale(5);
    }

    /**
     * Creates a new PlayGameState if the screen is tapped.
     */
    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.push(new PlayGameState(gsm));
        }
    }

    /**
     * Calls the handleInput method.
     * @param dt
     */
    @Override
    public void update(float dt) {
        highScore = hs.getInteger("highscore", 0);
        handleInput();
    }

    /**
     * Renders the background and playBtn.
     * @param sb SpriteBatch for rendering
     */
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, -background.getWidth() / 2, -background.getHeight() / 2);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth() / 2, cam.position.y - playBtn.getHeight() / 2);
        //highScoreText.draw(sb, "High Score " + highScore, (int) (cam.viewportWidth * HIGHSCORE_TEXT_X),
                           //(int) (cam.viewportHeight * HIGHSCORE_TEXT_Y));
        sb.end();
    }

    /**
     * Dispose background and playBtn assets.
     */
    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        highScoreText.dispose();
        System.out.println("MenuGameState disposed");
    }
} // End of public class MenuGameState extends GameState

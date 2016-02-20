/*
 * Carnage Games
 * December 18, 2015
 * PlayState.java
 *
 * Game runner
 */

package com.eggmasters.bounce.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.eggmasters.bounce.sprites.Ball;
import com.eggmasters.bounce.sprites.Platform;
import com.eggmasters.bounce.sprites.SidePlatform;
import com.eggmasters.bounce.sprites.TopPlatform;


/**
 * A class running the main game and controlling game mechanics.
 */
public class PlayGameState extends GameState {

    private static final int BALL_POSITION_X = -128;
    private static final int BALL_POSITION_Y = 500;
    private static final int BALL_GRAVITY_Y = -10;
    private static final int BALL_VELOCITY_X = 500;
    private static final int BALL_ACCELERATION_Y = -30;

    private static final int PLATFORM_POSITION_X = 0;
    private static final int PLATFORM_POSITION_Y = -512;
    private static final int PLATFORM_VELOCITY_X = 1000;

    private static final float BALL_PLAT_MULT = -0.8f;
    private static final float BALL_TOP_MULT = -0.2f;


    private static final float SCORE_TEXT_X = 0.1f;
    private static final float SCORE_TEXT_Y = 0.1f;

    private Texture background;
    private Ball ball;
    private Platform platform;
    private TopPlatform top;
    private SidePlatform left;
    private SidePlatform right;
    private BitmapFont scoreText;
    int score;
    private Sound bounceSound;

    /**
     * Constructs a PlayGameState that initializes the game graphics and camera.
     * @param gsm GameStateManager
     */
    public PlayGameState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.png");
        ball = new Ball(BALL_POSITION_X, BALL_POSITION_Y, BALL_GRAVITY_Y, BALL_VELOCITY_X, BALL_ACCELERATION_Y);
        platform = new Platform(PLATFORM_POSITION_X, PLATFORM_POSITION_Y, PLATFORM_VELOCITY_X);
        top = new TopPlatform(new Vector2(cam.position.x - background.getWidth() / 2, cam.position.y + background.getHeight() / 2 - 32));
        left = new SidePlatform(new Vector2(cam.position.x - background.getWidth() / 2, cam.position.y - background.getHeight() / 2));
        right = new SidePlatform(new Vector2(cam.position.x + background.getWidth() / 2 - 32, cam.position.y - background.getHeight() / 2));
        scoreText = new BitmapFont();
        scoreText.setColor(Color.BLUE);
        scoreText.getData().scale(5);
        bounceSound = Gdx.audio.newSound(Gdx.files.internal("coin.wav"));
    }

    /**
     * No action.
     */
    @Override
    public void handleInput() {

    }

    /**
     * Updates the ball and platform and checks for ball collision.
     * @param dt seconds per frame
     */
    @Override
    public void update(float dt) {
        //handleInput();
        if (ball.collides(platform.getBound())) {
            ball.bounceY((int) (platform.getPosition().y + platform.getTexture().getHeight()), BALL_PLAT_MULT);
            score++;
            bounceSound.play();
        }
        else if (ball.collides(top.getBound())) {
            ball.bounceY((int) (top.getPosition().y - ball.getTexture().getHeight()), BALL_TOP_MULT);
        }
        else if (ball.collides((int) (cam.position.y - background.getHeight() / 2))) {
            Preferences hs = Gdx.app.getPreferences("highscore");
            if (score > hs.getInteger("highscore", 0)) {
                hs.putInteger("highscore", score);
                hs.flush();
            }
            gsm.pop();
        }

        if (ball.collides(left.getBound())) {
            ball.bounceX(left.getPosition().x + left.getTexture().getWidth());
        }
        else if (ball.collides(right.getBound())) {
            ball.bounceX(right.getPosition().x - ball.getTexture().getWidth());
        }

        if (platform.collides(left.getBound())) {
            platform.bounce((int) (left.getPosition().x + left.getTexture().getWidth()));
        }
        else if (platform.collides(right.getBound())) {
            platform.bounce((int) (right.getPosition().x - platform.getTexture().getWidth()));
        }

        ball.update(dt);
        platform.update(dt);
    }

    /**
     * Renders the background, ball, platform, and score.
     * @param sb sprite batch
     */
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, -background.getWidth() / 2, -background.getHeight() / 2);
        sb.draw(ball.getTexture(), ball.getPosition().x, ball.getPosition().y);
        sb.draw(platform.getTexture(), platform.getPosition().x, platform.getPosition().y);
        sb.draw(top.getTexture(), top.getPosition().x, top.getPosition().y);
        sb.draw(left.getTexture(), left.getPosition().x, left.getPosition().y);
        sb.draw(right.getTexture(), right.getPosition().x, right.getPosition().y);
        scoreText.draw(sb, "" + score, -256, -800);
        sb.end();
    }

    /**
     * Disposes of background, ball, and platform assets.
     */
    @Override
    public void dispose() {
        ball.dispose();
        platform.dispose();
        top.dispose();
        left.dispose();
        right.dispose();
        scoreText.dispose();
        System.out.println("PlayGameState disposed");
    }


} // End of public class PlayGameState extends GameState

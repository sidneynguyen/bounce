/*
 * Carnage Games
 * December 18, 2015
 * GameStateManager.java
 *
 * Manages game states
 */

package com.eggmasters.bounce.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * A class managing all game States and updating and rendering the current game GameState.
 */
public class GameStateManager {

    private Stack<GameState> gameStates;

    /**
     * Constructs a Stack of game States.
     */
    public GameStateManager() {
        gameStates = new Stack<GameState>();
    }

    /**
     * Pushes on a game GameState.
     * @param gameState game GameState
     */
    public void push(GameState gameState) {
        gameStates.push(gameState);
    }

    /**
     * Pops off a game GameState.
     */
    public void pop() {
        gameStates.pop().dispose();
    }

    /**
     * Sets peek game GameState.
     * @param gameState game GameState
     */
    public void set(GameState gameState) {
        gameStates.pop().dispose();
        gameStates.push(gameState);
    }

    /**
     * Updates peek game state.
     * @param dt seconds per frames
     */
    public void update(float dt) {
        gameStates.peek().update(dt);
    }

    /**
     * Renders peek game state.
     * @param sb SpriteBatch for rendering
     */
    public void render(SpriteBatch sb) {
        gameStates.peek().render(sb);
    }

    /**
     * Dispose assets of all States.
     */
    public void dispose() {
        for (GameState s: gameStates) {
            s.dispose();
            System.out.println("GameStateManager disposed");
        }
    }

} // End of public class GameStateManager

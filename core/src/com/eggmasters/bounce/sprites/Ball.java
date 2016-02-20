/*
 * Carnage Games
 * December 18, 2015
 * Ball.java
 *
 * Ball sprite
 */

package com.eggmasters.bounce.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * A class representing the Ball sprite.
 */
public class Ball {

    private Texture texture;
    private Rectangle bound;

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private Vector2 gravity;

    public Ball(int x, int y, int gy, int vx, int ay) {
        this.texture = new Texture("ball.png");
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(vx, 0);
        this.gravity = new Vector2(0, gy);
        this.acceleration = new Vector2(0, ay);
        this.bound = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    /**
     * Accelerates the Ball when the screen is touched.
     */
    public void handleInput() {
        if (Gdx.input.isTouched()) {
            velocity.add(acceleration);
        }
    }

    /**
     * Moves the Ball and subjects it to gravity.
     * @param dt seconds per frame
     */
    public void update(float dt) {
        velocity.add(gravity);
        velocity.scl(dt);
        position.add(velocity);
        velocity.scl(1/dt);
        bound.setPosition(position.x, position.y);
        handleInput();
    }

    /**
     * Changes direction of Ball, decrease its size, and slow its speed.
     * @param y position
     */
    public void bounceY(int y, float dy) {
        velocity.y *= dy;
        position.y = y;
        bound.setPosition(position.x, position.y);
    }

    public void bounceX(float x) {
        velocity.x *= -1;
        position.x = x;
        bound.setPosition(position.x, position.y);
    }

    /**
     * Check for collision
     * @param platform boundary
     * @return true if collided
     */
    public boolean collides(Rectangle platform) {
       return bound.overlaps(platform);
    }

    public boolean collides(int y) {
        return position.y < y;
    }

    /**
     * Gets position of Ball.
     * @return position
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Gets texture of Ball.
     * @return texture
     */
    public Texture getTexture() {
        return texture;
    }


    /**
     * Dispose texture asset
     */
    public void dispose() {
        texture.dispose();
        System.out.println("Ball disposed");
    }

} // End of public class Ball

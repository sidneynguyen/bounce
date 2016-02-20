/*
 * Carnage Games
 * December 18, 2015
 * Platform.java
 *
 * Platform sprite
 */

package com.eggmasters.bounce.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * A class representing the Platform sprite.
 */
public class Platform {

    private Vector2 position;
    private Vector2 velocity;
    private Texture texture;
    private Rectangle bound;
    private float width;
    private float height;
    private float screenEdge;

    /**
     * Constructs a Platform sprite.
     * @param x position
     * @param y position
     * @param width of Platform
     * @param height of Platform
     * @param speed initial Platform speed
     * @param screenEdge right edge of the screen
     */
    public Platform(int x, int y, int vx) {
        this.texture = new Texture("platform.png");
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(vx, 0);
        this.bound = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    /**
     * Moves the platform left and right within the screen.
     * @param dt seconds per frame
     */
    public void update(float dt) {
        velocity.scl(dt);
        position.add(velocity.x, 0);
        velocity.scl(1 / dt);
        bound.setPosition(position.x, position.y);
    }

    public boolean collides(Rectangle platform) {
        return bound.overlaps(platform);
    }

    public void bounce(int x) {
        velocity.scl(-1);
        position.x = x;
        bound.setPosition(position.x, position.y);
    }

    /**
     * Gets position of Platform.
     * @return position
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Gets texture of Platform.
     * @return texture
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Get boundary
     * @return boundary
     */
    public Rectangle getBound() {
        return bound;
    }

    /**
     * Gets width of Platform.
     * @return width
     */
    public float getWidth() {
        return width;
    }

    /**
     * Gets height of Platform.
     * @return height
     */
    public float getHeight() { return height; }

    /**
     * Dispose texture asset.
     */
    public void dispose() {
        texture.dispose();
        System.out.println("Platform disposed");
    }

} // End of public class Platform

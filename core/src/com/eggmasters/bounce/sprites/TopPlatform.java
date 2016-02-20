package com.eggmasters.bounce.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class TopPlatform {

    private Texture texture;
    private Vector2 position;
    private float width;
    private float height;
    private Rectangle bound;

    public TopPlatform(Vector2 position) {
        this.texture = new Texture("top.png");
        this.position = position;
        bound = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    public void dispose() {
        texture.dispose();
    }

    public Texture getTexture() {
        return texture;
    }

    public Rectangle getBound() {
        return bound;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}

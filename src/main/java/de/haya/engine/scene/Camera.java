package de.haya.engine.scene;

import org.joml.Vector2f;

public class Camera {

    private Vector2f position;
    private float size;

    public Camera(Vector2f position, float size) {
        this.position = position;
        this.size = size;
    }

    public Camera(float size) {
        this.position = new Vector2f(0f, 0f);
        this.size = size;
    }

    public Vector2f translate(Vector2f vec2f) {
        return vec2f.mul(size).sub(position);
    }

    public Vector2f getPosition() {
        return position;
    }

    public float getSize() {
        return size;
    }
}

package de.haya.engine.gameobject;

import de.haya.engine.util.Mathf;
import org.joml.Math;
import org.joml.Vector2f;

public class Transform {

    private Vector2f position;
    private float rotation;
    private Vector2f scale;

    private boolean isDirty;

    public Transform(Vector2f position, float rotation, Vector2f scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Transform(Vector2f position, float rotation) {
        this(position, rotation, new Vector2f(1f, 1f));
    }

    public Transform(Vector2f position, Vector2f scale) {
        this(position, 0f, scale);
    }

    public Transform(Vector2f position) {
        this(position, 0f, new Vector2f(1f, 1f));
    }

    public Transform() {
        this(new Vector2f(0f, 0f), 0f, new Vector2f(1f, 1f));
    }

    public void lookAt(Transform transform) {
        this.lookAt(transform.position);
    }

    public void lookAt(Vector2f position) {
        Vector2f direction = this.position.sub(position);
        rotation = Math.atan2(direction.y, direction.x);
    }

    public Vector2f up() {
        return Mathf.atd(this.rotation());
    }

    public Vector2f right() {
        return Mathf.atd(this.rotation() - 90f);
    }

    public Vector2f position() {
        return position;
    }

    public void position(Vector2f position) {
        this.position = position;
        this.isDirty = true;
    }

    public float rotation() {
        return rotation;
    }

    public void rotation(float rotation) {
        this.rotation = rotation;
        this.isDirty = true;
    }

    public Vector2f scale() {
        return scale;
    }

    public void scale(Vector2f scale) {
        this.scale = scale;
        this.isDirty = true;
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void clean() {
        this.isDirty = false;
    }
}
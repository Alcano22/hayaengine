package de.haya.engine.util;

import org.joml.Math;
import org.joml.Vector2f;

public final class Mathf extends Math {

    private Mathf() {}

    public static Vector2f atd(float angle) {
        float x = Math.cos(Math.toRadians(angle));
        float y = Math.sin(Math.toRadians(angle));
        return new Vector2f(x, y);
    }

}

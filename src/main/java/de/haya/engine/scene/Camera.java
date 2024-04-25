package de.haya.engine.scene;

import org.joml.Vector2f;
import org.joml.Vector2i;

public class Camera {

    public Vector2f position;
    public int pixelsPerUnit;

    public Camera(Vector2f position, int pixelsPerUnit) {
        this.position = position;
        this.pixelsPerUnit = pixelsPerUnit;
    }

    public Vector2i translate(Vector2f worldPos) {
        worldPos.mul(this.pixelsPerUnit).sub(this.position);
        int screenX = (int) worldPos.x;
        int screenY = (int) worldPos.y;
        return new Vector2i(screenX, screenY);
    }

}

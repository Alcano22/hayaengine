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

    public Vector2i worldToScreenPoint(Vector2f worldPos) {
        Vector2f translatedPos = new Vector2f(worldPos).mul(this.pixelsPerUnit).sub(this.position);
        int screenX = (int) translatedPos.x;
        int screenY = (int) translatedPos.y;
        return new Vector2i(screenX, screenY);
    }

    public Vector2i worldToScreenScale(Vector2f worldScale) {
        Vector2f translatedScale = new Vector2f(worldScale).mul(this.pixelsPerUnit);
        int screenScaleX = (int) translatedScale.x;
        int screenScaleY = (int) translatedScale.y;
        return new Vector2i(screenScaleX, screenScaleY);
    }

}

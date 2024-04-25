package de.haya.engine.gameobject.component;

import de.haya.engine.gameobject.GameObject;
import de.haya.engine.gameobject.Transform;

import java.awt.*;

public abstract class Component {

    public GameObject gameObject;

    public void start() {}
    public void update() {}
    public void render(Graphics gfx) {}

    public Transform transform() {
        return gameObject.transform;
    }

}

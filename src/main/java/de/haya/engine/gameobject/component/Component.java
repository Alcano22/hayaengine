package de.haya.engine.gameobject.component;

import de.haya.engine.gameobject.GameObject;
import de.haya.engine.gameobject.Transform;
import de.haya.engine.scene.Camera;
import de.haya.engine.scene.Scene;
import de.haya.engine.scene.SceneManager;

import java.awt.*;

public abstract class Component {

    public GameObject gameObject;

    public void start() {}
    public void update() {}
    public void render(Graphics gfx) {}

    public Transform transform() {
        return gameObject.transform;
    }

    public Scene scene() {
        return SceneManager.getCurrentScene();
    }

    public Camera camera() {
        return this.scene().camera;
    }

}

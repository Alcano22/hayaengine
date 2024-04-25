package de.haya.engine.scene;

import de.haya.engine.gameobject.GameObject;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Scene {

    public final Camera camera;

    private final List<GameObject> gameObjects;

    public Scene(Camera camera) {
        this.camera = camera;
        this.gameObjects = new LinkedList<>();
    }

    public void start() {
        gameObjects.forEach(GameObject::start);
    }

    public void update() {
        gameObjects.forEach(GameObject::update);
    }

    public void render(Graphics gfx) {
        gameObjects.forEach(gameObject -> gameObject.render(gfx));
    }

    public void addGameObject(GameObject gameObject) {
        this.gameObjects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        this.gameObjects.remove(gameObject);
    }
}

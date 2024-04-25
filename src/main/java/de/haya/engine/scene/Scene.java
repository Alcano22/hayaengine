package de.haya.engine.scene;

import de.haya.engine.gameobject.GameObject;
import de.haya.engine.tilemap.Tilemap;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Scene {

    private final List<GameObject> gameObjects;

    boolean renderTilemap1 = true;

    public Tilemap tilemap;

    public Scene() {
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

package de.haya.tloy.scene;

import de.haya.tloy.gameobject.GameObject;

import java.util.LinkedList;
import java.util.List;

public class Scene {

    private final List<GameObject> gameObjects;

    public Scene() {
        this.gameObjects = new LinkedList<>();
    }

    public void start() {
        gameObjects.forEach(GameObject::start);
    }

    public void update() {
        gameObjects.forEach(GameObject::update);
    }

    public void addGameObject(GameObject gameObject) {
        this.gameObjects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        this.gameObjects.remove(gameObject);
    }

}

package de.haya.tloy.gameobject;

import de.haya.tloy.gameobject.component.Component;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

    public Transform transform;

    private final List<Component> components;

    public GameObject(Transform transform) {
        this.transform = transform;
        this.components = new ArrayList<>();
    }

    public GameObject() {
        this(new Transform());
    }

    public void start() {
        this.components.forEach(Component::start);
    }

    public void update() {
        this.components.forEach(Component::update);
    }

}

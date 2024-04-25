package de.haya.engine.gameobject;

import de.haya.engine.gameobject.component.Component;
import de.haya.engine.logging.Log;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
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

    public void render(Graphics gfx) {
        this.components.forEach(component -> component.render(gfx));
    }

    public <T extends Component> T addComponent(Class<T> componentClass) {
        try {
            T component = (T) componentClass.getDeclaredConstructors()[0].newInstance();
            component.gameObject = this;
            this.components.add(component);
            return component;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            Log.error(e);
        }

        return null;
    }

    public <T extends Component> void removeComponent(Class<T> componentClass) {
        this.components.remove(this.getComponent(componentClass));
    }

    public <T extends Component> void removeComponents(Class<T> componentClass) {
        this.components.removeAll(this.getComponents(componentClass));
    }

    public <T extends Component> List<T> getComponents(Class<T> componentClass) {
        return this.components.stream()
                .filter(component -> component.getClass().equals(componentClass))
                .map(component -> (T) component)
                .toList();
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        return this.getComponents(componentClass).get(0);
    }

    public <T extends Component> boolean hasComponent(Class<T> componentClass) {
        return !this.getComponents(componentClass).isEmpty();
    }

}

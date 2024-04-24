package de.haya.tloy.gameobject.component;

import de.haya.tloy.gameobject.GameObject;
import de.haya.tloy.gameobject.Transform;

public abstract class Component {

    public GameObject gameObject;

    public void start() {}
    public void update() {}

    public Transform transform() {
        return gameObject.transform;
    }

}

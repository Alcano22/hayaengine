package de.haya.engine.input;

import org.joml.Vector2f;

public class Input {

    private static Input instance;

    private final KeyboardListener keyboard;
    private final MouseListener mouse;

    private Input() {
        this.keyboard = new KeyboardListener();
        this.mouse = new MouseListener();
    }

    public void endFrame() {
        this.keyboard.endFrame();
    }

    public static boolean getKey(int key) {
        return get().keyboard.getKey(key);
    }

    public static boolean getKeyDown(int key) {
        return get().keyboard.getKeyDown(key);
    }

    public static boolean getKeyUp(int key) {
        return get().keyboard.getKeyUp(key);
    }

    public static float getAxis(Axis axis) {
        if (axis == Axis.HORIZONTAL) {
            if (getKey(KeyCode.A)) return -1;
            if (getKey(KeyCode.D)) return 1;
        } else {
            if (getKey(KeyCode.W)) return -1;
            if (getKey(KeyCode.S)) return 1;
        }

        return 0;
    }

    public static Vector2f getAxes() {
        return new Vector2f(getAxis(Axis.HORIZONTAL), getAxis(Axis.VERTICAL));
    }

    public KeyboardListener getKeyboard() {
        return keyboard;
    }

    public MouseListener getMouse() {
        return mouse;
    }

    public static Input get() {
        if (instance == null)
            instance = new Input();
        return instance;
    }

    public enum Axis {
        HORIZONTAL, VERTICAL;
    }

}

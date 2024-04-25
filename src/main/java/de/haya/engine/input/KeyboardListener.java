package de.haya.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class KeyboardListener implements KeyListener {

    public static final int NUM_KEYS = 255;

    private final boolean[] pressedKeys, currentKeys, upKeys, downKeys;

    public KeyboardListener() {
        this.pressedKeys = new boolean[255];
        this.currentKeys = new boolean[255];
        this.upKeys = new boolean[255];
        this.downKeys = new boolean[255];
    }

    public void endFrame() {
        Arrays.fill(this.upKeys, false);
        for (int i = 0; i < NUM_KEYS; i++)
            this.upKeys[i] = !this.pressedKeys[i] && this.currentKeys[i];

        Arrays.fill(this.downKeys, false);
        for (int i = 0; i < NUM_KEYS; i++)
            this.downKeys[i] = this.pressedKeys[i] && !this.currentKeys[i];

        System.arraycopy(this.pressedKeys, 0, this.currentKeys, 0, NUM_KEYS);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.pressedKeys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.pressedKeys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public boolean getKey(int key) {
        return key < NUM_KEYS && this.currentKeys[key];
    }

    public boolean getKeyDown(int key) {
        return key < NUM_KEYS && this.downKeys[key];
    }

    public boolean getKeyUp(int key) {
        return key < NUM_KEYS && this.upKeys[key];
    }
}

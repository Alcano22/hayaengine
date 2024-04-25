package de.haya.engine.scene;

import java.awt.*;

public final class SceneManager {

    private static Scene currentScene;

    private SceneManager() {}

    public static void update() {
        currentScene.update();
    }

    public static void render(Graphics gfx) {
		currentScene.render(gfx);
	}

    public static void loadScene(Scene scene) {
        scene.start();
        currentScene = scene;
    }

    public static Scene getCurrentScene() {
        return currentScene;
    }

}

package de.haya.tloy.scene;

public final class SceneManager {

    private static Scene currentScene;

    private SceneManager() {}

    public static void update() {
        currentScene.update();
    }

    public static void loadScene(Scene scene) {
        scene.start();
        currentScene = scene;
    }

    public static Scene getCurrentScene() {
        return currentScene;
    }

}

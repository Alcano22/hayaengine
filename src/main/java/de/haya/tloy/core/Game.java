package de.haya.tloy.core;

import de.haya.tloy.gameobject.GameObject;
import de.haya.tloy.gameobject.Transform;
import de.haya.tloy.scene.Scene;
import de.haya.tloy.scene.SceneManager;
import de.haya.tloy.util.Debug;
import de.haya.tloy.util.Time;
import de.haya.tloy.window.Window;
import org.joml.Vector2f;

public class Game {

    public final Window window;

    private final Thread mainThread;

    private boolean isRunning;

    private Game() {
        this.window = new Window();
        Debug.error("Window setup");
        this.window.run();
        Debug.error("Window run");

        this.mainThread = new Thread(this::run);

        this.init();
        this.start();
    }

    private void init() {
        Scene scene = new Scene();
        GameObject player = new GameObject(new Transform(new Vector2f(0f, 0f), 20f));
        System.out.println(player.transform.up());
        scene.addGameObject(player);
        SceneManager.loadScene(scene);
    }

    public synchronized void start() {
        this.mainThread.start();
        this.isRunning = true;
    }

    public synchronized void stop() {
        try {
            this.mainThread.join();
            this.isRunning = false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void run() {
        long last = System.nanoTime();
        double deltaTime = 0f;

        long timer = System.currentTimeMillis();
        int frames = 0;

        while (this.isRunning) {
            long now = System.nanoTime();
            double tickDuration = 1_000_000_000f / Time.fpsCap;
            deltaTime += (now - last) / tickDuration;
            last = now;

            boolean shouldRender = false;

            while (deltaTime >= 1f) {
                Time.deltaTime = (float) deltaTime;
                this.update();
                deltaTime--;
                shouldRender = true;
            }

            if (shouldRender) {
                this.render();
                frames++;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                Time.fps = frames;
                frames = 0;
                timer += 1000;
            }
        }

        this.stop();
    }

    private void update() {
        SceneManager.update();
    }

    private void render() {
        this.window.render();
    }

    public static void main(String[] args) {
        new Game().run();
    }

}

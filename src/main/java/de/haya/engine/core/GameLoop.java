package de.haya.engine.core;

import de.haya.engine.logging.Log;
import de.haya.engine.util.Time;

public class GameLoop {

    private final Engine engine;
    private final Thread thread;

    private boolean running;
    private int frames = 0;

    public GameLoop(Engine engine) {
        this.engine = engine;

        this.thread = new Thread(this::loop);
    }

    public synchronized void start() {
        this.running = true;
        this.thread.start();
    }

    public synchronized void stop() {
        try {
            this.running = false;
            this.thread.join();
        } catch (InterruptedException e) {
            Log.error(e);
        }
    }

    public synchronized void loop() {
        long last = System.nanoTime();
        double deltaTime = 0f;

        long timer = System.currentTimeMillis();

        while (this.running) {
            long now = System.nanoTime();

            if (Time.isFPSLimited) {
                double tickDuration = 1_000_000_000f / Time.fpsLimit;
                deltaTime += (now - last) / tickDuration;
            } else
                deltaTime = 1;

            last = now;

            boolean renderNextFrame = false;

            while (deltaTime >= 1f) {
                Time.deltaTime = (float) deltaTime;
                engine.update();
                deltaTime--;
                renderNextFrame = true;
            }

            if (renderNextFrame) {
                this.engine.render();
                this.frames++;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                Time.fps = this.frames;
                this.frames = 0;
                timer += 1000;
            }
        }

        this.stop();
    }
}

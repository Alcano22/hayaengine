package de.haya.engine.core;

import de.haya.engine.logging.Log;
import de.haya.engine.util.Time;

public class GameLoop {

    private final Engine engine;
    private final Thread thread;

    public boolean limitFPS;
    public int targetFPS;

    private boolean running;

    public GameLoop(Engine engine, boolean limitFPS, int targetFPS) {
        this.engine = engine;
        this.limitFPS = limitFPS;
        this.targetFPS = targetFPS;

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
        float interval = 1f / (float) targetFPS;
        float accumulator = 0f;
        float currentTime = Time.getTime();
        float frameTime;

        while (this.running) {
            float newTime = Time.getTime();
            frameTime = newTime - currentTime;
            currentTime = newTime;

            accumulator += frameTime;

            while (accumulator >= interval) {
                Time.deltaTime = interval;
                this.engine.update();
                accumulator -= interval;
            }

            this.engine.render();

            if (limitFPS) {
                float sleepTime = interval - (Time.getTime() - currentTime);
                if (sleepTime > 0f) {
                    try {
                        Thread.sleep((long) (sleepTime * 1000f));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}

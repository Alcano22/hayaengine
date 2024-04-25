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
        float drawInterval = 1f / this.targetFPS;
        float last = Time.getTime();
        float current;
        float deltaTime = 0f;

        while (this.running) {
            current = Time.getTime();
            deltaTime += (current - last) / drawInterval;
            last = current;

            Time.deltaTime = deltaTime;

            if (deltaTime >= 1f) {
                this.engine.update();
                this.engine.render();
                deltaTime--;
            }
        }

        this.stop();
    }
}

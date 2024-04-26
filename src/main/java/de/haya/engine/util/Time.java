package de.haya.engine.util;

public final class Time {

    public static final long START_TIME = System.nanoTime();

    public static float deltaTime;

    private Time() {}

    public static float getTime() {
        return (System.nanoTime() - START_TIME) * 1E-9f;
    }

    public static int getFPS() {
        if (deltaTime == 0) return 0;
        return Math.round(1f / deltaTime);
    }

}

package de.haya.engine.sound;

public final class SoundUtils {

    private SoundUtils() {}

    public static float volumeToDecibel(float volume) {
        if (volume == 0) return -60f;
        return (float) Math.log10(volume);
    }

}

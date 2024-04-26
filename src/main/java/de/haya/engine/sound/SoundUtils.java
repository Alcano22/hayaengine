package de.haya.engine.sound;

public final class SoundUtils {

    private SoundUtils() {}

    public static float volumeToDecibel(float volume) {
        volume /= 100f;
        if (volume == 0) return -60f;
        return 20f * (float) Math.log10(volume);
    }

}

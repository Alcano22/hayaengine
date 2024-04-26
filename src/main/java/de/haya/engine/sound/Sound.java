package de.haya.engine.sound;

import de.haya.engine.util.Mathf;

import javax.sound.sampled.Clip;

public class Sound {

	public Clip audioClip;

	private float volume;
	private float pitch;

	public Sound(Clip audioClip, float volume, float pitch) {
		this.audioClip = audioClip;
		this.volume = volume;
		this.pitch = pitch;
	}

	public Sound(Clip audioClip, float volume) {
		this(audioClip, volume, 1f);
	}

	public Sound(Clip audioClip) {
		this(audioClip, 100f);
	}

	public float getDecibel() {
		return SoundUtils.volumeToDecibel(this.volume);
	}

	public float getVolume() {
		return this.volume;
	}

	public void setVolume(float volume) {
		this.volume = Mathf.clamp(volume, 0f, 100f);
	}

	public float getPitch() {
		return this.pitch;
	}

	public void setPitch(float pitch) {
		if (pitch < 0f) pitch = 0f;

		this.pitch = pitch;
	}
}

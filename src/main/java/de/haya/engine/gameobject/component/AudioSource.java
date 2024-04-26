package de.haya.engine.gameobject.component;

import de.haya.engine.sound.Sound;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class AudioSource extends Component {

	public Sound sound;
	public boolean loop;

	public void play() {
		Clip audioClip = this.sound.audioClip;

		audioClip.setFramePosition(0);

		FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(this.sound.getDecibel());

		audioClip.start();
	}

}

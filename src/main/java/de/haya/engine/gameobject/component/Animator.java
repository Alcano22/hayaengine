package de.haya.engine.gameobject.component;

import de.haya.engine.animation.AnimationAction;
import de.haya.engine.animation.AnimationFrame;
import de.haya.engine.logging.Log;
import de.haya.engine.util.Time;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Animator extends Component {

    private final Map<String, AnimationFrame[]> clips;

    private String currentClipId;
    private int index;

    private float nextProgressTime;

    public Animator() {
        this.clips = new HashMap<>();
    }

    @Override
    public void update() {
        if (this.currentClipId == null) return;

        AnimationFrame[] clip = this.clips.get(this.currentClipId);
        AnimationFrame frame = clip[this.index];

        Log.debug("[BEFORE TIME CHECK] Time: " + Time.getTime());
        Log.debug("[BEFORE TIME CHECK] NextProgressTime: " + this.nextProgressTime);
        if (Time.getTime() <= this.nextProgressTime) return;

        for (AnimationAction action : frame.actions())
            action.play(this.gameObject);

        if (this.index < clip.length - 1) {
            this.index++;
        } else
            this.index = 0;

        this.nextProgressTime = Time.getTime() + frame.duration();

        Log.debug(this.currentClipId + ": " + this.index + " / " + (clip.length - 1) + " takes " + frame.duration() + "s");
        Log.debug("[AFTER  TIME CHECK] Time: " + Time.getTime());
        Log.debug("[AFTER  TIME CHECK] NextProgressTime: " + this.nextProgressTime);
    }

    public AnimationFrame[] getClip(String id) {
        return this.clips.get(id);
    }

    public void setClip(String id, AnimationFrame... clip) {
        this.clips.put(id, clip);
    }

    public String getCurrentClip() {
        return this.currentClipId;
    }

    public void setCurrentClip(String id) {
        if (Objects.equals(this.currentClipId, id)) return;

        this.currentClipId = id;
        this.index = 0;
        this.nextProgressTime = 0f;
    }
}

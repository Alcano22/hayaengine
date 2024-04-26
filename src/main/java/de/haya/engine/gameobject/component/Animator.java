package de.haya.engine.gameobject.component;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Animator extends Component {

    public Map<String, BufferedImage[]> clips;

    public Animator() {
        this.clips = new HashMap<>();
    }

    @Override
    public void update() {

    }
}

package de.haya.engine.animation;

import de.haya.engine.gameobject.component.SpriteRenderer;
import de.haya.engine.util.ArrayUtils;
import org.joml.Vector2f;

import java.awt.image.BufferedImage;

public record AnimationFrame(float duration, AnimationAction... actions) {
	public static AnimationFrame position(float duration, Vector2f position, AnimationAction... optionalActions) {
		return new AnimationFrame(duration, ArrayUtils.join(new AnimationAction[] { gameObject -> gameObject.transform.position(position) }, optionalActions));
	}

	public static AnimationFrame rotation(float duration, float rotation, AnimationAction... optionalActions) {
		return new AnimationFrame(duration, ArrayUtils.join(new AnimationAction[] { gameObject -> gameObject.transform.rotation(rotation) }, optionalActions));
	}

	public static AnimationFrame scale(float duration, Vector2f scale, AnimationAction... optionalActions) {
		return new AnimationFrame(duration, ArrayUtils.join(new AnimationAction[] { gameObject -> gameObject.transform.scale(scale) }, optionalActions));
	}

	public static AnimationFrame texture(float duration, BufferedImage texture, AnimationAction... optionalActions) {
		return new AnimationFrame(duration,
				ArrayUtils.join(new AnimationAction[] { gameObject -> gameObject.getComponent(SpriteRenderer.class).texture = texture }, optionalActions));
	}
}

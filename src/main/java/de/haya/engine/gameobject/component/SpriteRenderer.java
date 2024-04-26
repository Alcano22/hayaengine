package de.haya.engine.gameobject.component;

import de.haya.engine.scene.Camera;
import org.joml.Vector2f;
import org.joml.Vector2i;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteRenderer extends Component {

	public BufferedImage texture;
	public Color color = Color.WHITE;

	@Override
	public void render(Graphics gfx) {
		Camera cam = this.camera();

		Vector2i screenPos = cam.worldToScreenPoint(this.transform().position());
		Vector2i screenScale = cam.worldToScreenScale(this.transform().scale());

		if (texture != null) {
			gfx.drawImage(this.texture, screenPos.x, screenPos.y, screenScale.x, screenScale.y, null);
		} else {
			gfx.setColor(this.color);
			gfx.fillRect(screenPos.x, screenPos.y, screenScale.x, screenScale.y);
		}
	}
}

package de.haya.engine.window;

import de.haya.engine.input.Input;
import de.haya.engine.input.KeyCode;
import de.haya.engine.util.Time;
import de.haya.engine.util.Version;
import org.joml.Vector2f;

import java.awt.*;

public class WindowDebugInfo {

	public Vector2f position;

	private boolean showing;

	private float nextFrameUpdateTime = 0f;
	private int fps;

	public WindowDebugInfo(Vector2f position) {
		this.position = position;
	}

	public void update() {
		if (Input.getKey(KeyCode.CONTROL) && Input.getKeyDown(KeyCode.F3))
			this.showing = !this.showing;

		if (Time.getTime() > this.nextFrameUpdateTime) {
			this.nextFrameUpdateTime = Time.getTime() + 1f;

			this.fps = Time.getFPS();
		}
	}

	public void render(Graphics gfx) {
		if (!this.showing) return;

		gfx.setColor(Color.YELLOW);
		gfx.setFont(gfx.getFont().deriveFont(16f));

		int x = (int) this.position.x;
		int y = (int) this.position.y;
		gfx.drawString("The Legend of Yan", x, y);
		gfx.drawString("Running on " + Version.get() + "!", x, y + gfx.getFont().getSize() + 5);
		gfx.drawString(this.fps + " FPS", x, y + gfx.getFont().getSize() * 2 + 10);
	}

}

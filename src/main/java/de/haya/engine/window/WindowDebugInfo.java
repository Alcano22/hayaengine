package de.haya.engine.window;

import de.haya.engine.input.Input;
import de.haya.engine.input.KeyCode;
import de.haya.engine.resources.AssetPool;
import de.haya.engine.util.Random;
import de.haya.engine.util.Time;
import de.haya.engine.util.Version;
import org.joml.Vector2f;

import java.awt.*;

public class WindowDebugInfo {

	public Vector2f position;

	private boolean showing;
	private float alpha;

	private float nextUpdateTime = 0f;
	private int fps;
	private float deltaTime;

	public WindowDebugInfo(Vector2f position) {
		this.position = position;
	}

	public void update() {
		if (Input.getKey(KeyCode.CONTROL) && Input.getKeyDown(KeyCode.F3))
			this.showing = !this.showing;

		if (Time.getTime() > this.nextUpdateTime) {
			this.nextUpdateTime = Time.getTime() + 1f;

			this.fps = Time.getFPS();
			this.deltaTime = Time.deltaTime;
		}

//		this.alpha += 0.01f * (showing ? 1f : -1f);
	}

	public void render(Graphics gfx) {
		if (!this.showing) return;

//		gfx.setColor(new Color(1f, 1f, 0f, this.alpha));
		gfx.setColor(Color.YELLOW);
		gfx.setFont(AssetPool.loadTTFFont("assets/fonts/roboto-mono.ttf").deriveFont(16f));

		int x = (int) this.position.x;
		int y = (int) this.position.y;

		String[] lines = {
				"The Legend of Yan",
				"Running on " + Version.get() + "!",
				"Debug Info:",
				"  FPS: " + this.fps,
				"  DT:  " + this.deltaTime
		};

		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];

			int offsetY = (gfx.getFont().getSize() + 5) * i;
			gfx.drawString(line, x, y + offsetY);
		}
	}

}

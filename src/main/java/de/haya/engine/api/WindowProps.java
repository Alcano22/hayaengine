package de.haya.engine.api;

import de.haya.engine.resources.AssetPool;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class WindowProps {

	private String title;
	private boolean limitFPS;
	private int targetFPS;

	public WindowProps() {
		this.title = "Haya Engine";
		this.limitFPS = true;
		this.targetFPS = 60;
	}

	public static WindowProps loadFromFile() {
		try {
			Properties props = new Properties();
			props.load(new FileReader(AssetPool.loadResourceFile("window.properties")));

			return new WindowProps()
					.title(props.getOrDefault("title", "Haya Engine").toString())
					.limitFPS(Boolean.parseBoolean(props.getOrDefault("limit_fps", "true").toString()))
					.targetFPS(Integer.parseInt(props.getOrDefault("target_fps", "60").toString()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public WindowProps title(String title) {
		this.title = title;
		return this;
	}

	public WindowProps limitFPS(boolean limitFPS) {
		this.limitFPS = limitFPS;
		return this;
	}

	public WindowProps targetFPS(int targetFPS) {
		this.targetFPS = targetFPS;
		return this;
	}

	public String title() {
		return this.title;
	}

	public boolean limitFPS() {
		return this.limitFPS;
	}

	public int targetFPS() {
		return this.targetFPS;
	}
}

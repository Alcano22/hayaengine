package de.haya.engine.resources;

import de.haya.engine.logging.Log;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public final class AssetPool {

	private static final Map<String, File> FILES = new HashMap<>();

	private AssetPool() {}

	public static File loadFile(String filepath) {
		if (FILES.containsKey(filepath))
			return FILES.get(filepath);

		File file = new File(filepath);
		FILES.put(filepath, file);
		return file;
	}

	public static File loadResourceFile(String filepath) {
		if (FILES.containsKey(filepath))
			return FILES.get(filepath);

		try {
			File file = new File(AssetPool.class.getResource("/" + filepath).toURI());
			FILES.put(filepath, file);
			return file;
		} catch (URISyntaxException e) {
			Log.error(e);
		}

		return null;
	}

}

package de.haya.engine.resources;

import de.haya.engine.logging.Log;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public final class AssetPool {

	private static final Map<String, File> FILES = new HashMap<>();
	private static final Map<String, Font> FONTS = new HashMap<>();

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

	public static Font loadTTFFont(String filepath) {
		if (FONTS.containsKey(filepath))
			return FONTS.get(filepath);

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(filepath));
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
			FONTS.put(filepath, font);
			return font;
        } catch (FontFormatException | IOException e) {
            Log.error(e);
        }

		return null;
    }

}

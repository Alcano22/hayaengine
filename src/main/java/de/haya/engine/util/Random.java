package de.haya.engine.util;

import java.awt.*;

public final class Random {

	private static final java.util.Random RANDOM = new java.util.Random();

	public static float floatValue() {
		return RANDOM.nextFloat();
	}

	public static double doubleValue() {
		return RANDOM.nextDouble();
	}

	public static int range(int min, int max) {
		return RANDOM.nextInt(min, max);
	}

	public static float range(float min, float max) {
		return RANDOM.nextFloat(min, max);
	}

	public static double range(double min, double max) {
		return RANDOM.nextDouble(min, max);
	}

	public static Color color(boolean randomizeAlpha) {
		return new Color(Random.floatValue(), Random.floatValue(), Random.floatValue(), randomizeAlpha ? Random.floatValue() : 1f);
	}

	public static Color color() {
		return color(false);
	}

	public static Color bwColor(boolean randomizeAlpha) {
		float value = Random.floatValue();
		return new Color(value, value, value, randomizeAlpha ? Random.floatValue() : 1f);
	}

	public static Color bwColor() {
		return bwColor(false);
	}

}

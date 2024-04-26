package de.haya.engine.util;

import java.lang.reflect.Array;

public final class ArrayUtils {

	private ArrayUtils() {}

	public static <T> T[] join(T[] original, T[] newElements) {
		if (original == null || newElements == null)
			throw new IllegalArgumentException("Arrays must not be null");

		Class<?> type = original.getClass().getComponentType();
		T[] result = (T[]) Array.newInstance(type, original.length + newElements.length);

		System.arraycopy(original, 0, result, 0, original.length);
		System.arraycopy(newElements, 0, result, original.length, newElements.length);

		return result;
	}

}

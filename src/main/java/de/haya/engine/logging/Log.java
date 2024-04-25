package de.haya.engine.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

	public static final Logger LOGGER = LogManager.getLogger("Game", new VectorMessageFactory());

	public static void log(Level level, Object obj) {
		LOGGER.log(level, obj);
	}

	public static void trace(String msg, Object... params) {
		LOGGER.trace(msg, params);
	}

	public static void trace(Object obj) {
		LOGGER.trace(obj);
	}

	public static void debug(String msg, Object... params) {
		LOGGER.debug(msg, params);
	}

	public static void debug(Object obj) {
		LOGGER.debug(obj);
	}

	public static void info(String msg, Object... params) {
		LOGGER.info(msg, params);
	}

	public static void info(Object obj) {
		LOGGER.info(obj);
	}

	public static void warn(String msg, Object... params) {
		LOGGER.warn(msg, params);
	}

	public static void warn(Object obj) {
		LOGGER.warn(obj);
	}

	public static void error(String msg, Object... params) {
		LOGGER.error(msg, params);
	}

	public static void error(Object obj) {
		LOGGER.error(obj);
	}

	public static void fatal(String msg, Object... params) {
		LOGGER.fatal(msg, params);
	}

	public static void fatal(Object obj) {
		LOGGER.fatal(obj);
	}

}

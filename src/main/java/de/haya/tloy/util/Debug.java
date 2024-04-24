package de.haya.tloy.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joml.*;

public final class Debug {

    private static final Logger LOGGER = LogManager.getLogger("Game");

    private Debug() {}

    public static void log(Level level, Object msg) {
        if (msg instanceof Vector2i vec2i)
            msg = String.format("(%d, %d)", vec2i.x, vec2i.y);
        if (msg instanceof Vector2f vec2f)
            msg = String.format("(%.4f, %.4f)", vec2f.x, vec2f.y);
        if (msg instanceof Vector2d vec2d)
            msg = String.format("(%.4f, %.4f)", vec2d.x, vec2d.y);
        if (msg instanceof Vector3i vec3i)
            msg = String.format("(%d, %d, %d)", vec3i.x, vec3i.y, vec3i.z);
        if (msg instanceof Vector3f vec3f)
            msg = String.format("(%.4f, %.4f, %.4f)", vec3f.x, vec3f.y, vec3f.z);
        if (msg instanceof Vector3d vec3d)
            msg = String.format("(%.4f, %.4f, %.4f)", vec3d.x, vec3d.y, vec3d.z);
        if (msg instanceof Vector4i vec4i)
            msg = String.format("(%d, %d, %d, %d)", vec4i.x, vec4i.y, vec4i.z, vec4i.z);
        if (msg instanceof Vector4f vec4f)
            msg = String.format("(%.4f, %.4f, %.4f, %.4f)", vec4f.x, vec4f.y, vec4f.z, vec4f.w);
        if (msg instanceof Vector4d vec4d)
            msg = String.format("(%.4f, %.4f, %.4f, %.4f)", vec4d.x, vec4d.y, vec4d.z, vec4d.w);

        LOGGER.log(level, msg);
    }

    public static void trace(Object msg) {
        log(Level.TRACE, msg);
    }

    public static void debug(Object msg) {
        log(Level.DEBUG, msg);
    }

    public static void info(Object msg) {
        log(Level.INFO, msg);
    }

    public static void warn(Object msg) {
        log(Level.WARN, msg);
    }

    public static void error(Object msg) {
        log(Level.ERROR, msg);
    }

    public static void fatal(Object msg) {
        log(Level.FATAL, msg);
    }

}

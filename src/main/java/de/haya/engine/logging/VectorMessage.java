package de.haya.engine.logging;

import org.apache.logging.log4j.message.Message;
import org.joml.*;

public class VectorMessage implements Message {

	private final String formattedMessage;

	public VectorMessage(Object obj) {
		this.formattedMessage = formatVector(obj);
	}

	@Override
	public String getFormattedMessage() {
		return this.formattedMessage;
	}

	@Override
	public String getFormat() {
		return this.formattedMessage;
	}

	@Override
	public Object[] getParameters() {
		return new Object[0];
	}

	@Override
	public Throwable getThrowable() {
		return null;
	}

	private String formatVector(Object obj) {
		if (obj instanceof Vector2i vec2i) return String.format("(%d, %d)", vec2i.x, vec2i.y);
		if (obj instanceof Vector2f vec2f) return String.format("(%.2f, %.2f)", vec2f.x, vec2f.y);
		if (obj instanceof Vector2d vec2d) return String.format("(%.2f, %.2f)", vec2d.x, vec2d.y);
		if (obj instanceof Vector3i vec3i) return String.format("(%d, %d, %d)", vec3i.x, vec3i.y, vec3i.z);
		if (obj instanceof Vector3f vec3f) return String.format("(%.2f, %.2f, %.2f)", vec3f.x, vec3f.y, vec3f.z);
		if (obj instanceof Vector3d vec3d) return String.format("(%.2f, %.2f, %.2f)", vec3d.x, vec3d.y, vec3d.z);
		if (obj instanceof Vector4i vec4i) return String.format("(%d, %d, %d, %d)", vec4i.x, vec4i.y, vec4i.z, vec4i.w);
		if (obj instanceof Vector4f vec4f) return String.format("(%.2f, %.2f, %.2f, %.2f)", vec4f.x, vec4f.y, vec4f.z, vec4f.w);
		if (obj instanceof Vector4d vec4d) return String.format("(%.2f, %.2f, %.2f, %.2f)", vec4d.x, vec4d.y, vec4d.z, vec4d.w);
		return obj.toString();
	}
}

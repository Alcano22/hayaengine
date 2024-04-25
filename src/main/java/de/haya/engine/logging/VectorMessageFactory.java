package de.haya.engine.logging;

import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;

public class VectorMessageFactory implements MessageFactory {

	@Override
	public Message newMessage(Object message) {
		return new VectorMessage(message);
	}

	@Override
	public Message newMessage(String message) {
		return new VectorMessage(message);
	}

	@Override
	public Message newMessage(String message, Object... params) {
		if (params.length == 0)
			return new VectorMessage(message);
		return new VectorMessage(params[0]);
	}
}

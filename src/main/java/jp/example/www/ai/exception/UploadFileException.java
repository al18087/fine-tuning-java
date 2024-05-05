package jp.example.www.ai.exception;

import jp.example.www.ai.message.MessageType;

public class UploadFileException extends Exception {
	
	public UploadFileException(final MessageType type, final Object... args) {
		super(type.getContent(args));
	}
}

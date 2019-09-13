package com.hackathon.sharedeconomy.exception;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDefineException extends RuntimeException {

	private String originalErrorMessage;
	private String errorMethod;
	private boolean success;

	public UserDefineException(String message) {
		super(message);
	}

	@Builder
	public UserDefineException(String originalMessage, boolean success) {
		this.originalErrorMessage = originalMessage;
		this.success = success;
	}
}

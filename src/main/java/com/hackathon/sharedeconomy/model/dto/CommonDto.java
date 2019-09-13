package com.hackathon.sharedeconomy.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by YoungMan on 2019-03-27.
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonDto {

	private boolean success;
	private String message;

	@Builder
	public CommonDto(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
}

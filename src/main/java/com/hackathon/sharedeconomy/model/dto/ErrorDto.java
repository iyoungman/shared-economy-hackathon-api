package com.hackathon.sharedeconomy.model.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorDto {

    private String originalErrorMessage;
    private String requestURL;
    private boolean success;

    @Builder
    public ErrorDto(String originalErrorMessage, String requestURL, boolean success) {
        this.originalErrorMessage = originalErrorMessage;
        this.requestURL = requestURL;
        this.success = success;
    }
}

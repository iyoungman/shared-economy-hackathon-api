package com.hackathon.sharedeconomy.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {

    private String originalErrorMessage;
    private String errorMessage;
    private String requestURL;

    public ErrorDto() {
    }

    @Builder
    public ErrorDto(String originalErrorMessage, String errorMessage, String requestURL) {
        this.originalErrorMessage = originalErrorMessage;
        this.errorMessage = errorMessage;
        this.requestURL = requestURL;
    }
}

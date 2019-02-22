package com.hackathon.sharedeconomy.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by YoungMan on 2019-02-14.
 */

@Getter
@Setter
public class ShoppingSaveDto {

    private String userId;
    private String name;//매물 이름

    public ShoppingSaveDto() {
    }

    @Builder
    public ShoppingSaveDto(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}

package com.hackathon.sharedeconomy.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by YoungMan on 2019-02-24.
 */

@Getter
@Setter
public class ForSaleRequestDto {

    private String userId;
    private String address;

    public ForSaleRequestDto() {
    }

    @Builder
    public ForSaleRequestDto(String userId, String address) {
        this.userId = userId;
        this.address = address;
    }
}

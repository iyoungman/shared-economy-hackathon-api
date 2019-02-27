package com.hackathon.sharedeconomy.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by YoungMan on 2019-02-14.
 */

@Getter
@Setter
public class ShoppingSaveDto {

    private String userId;//찜하는 사람 id
    private String name;//찜하는 매물 이름
    private String forSaleUserId;//찜하는 매물의 주인 id

    public ShoppingSaveDto() {
    }

    @Builder
    public ShoppingSaveDto(String userId, String name, String forSaleUserId) {
        this.userId = userId;
        this.name = name;
        this.forSaleUserId = forSaleUserId;
    }
}

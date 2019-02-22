package com.hackathon.sharedeconomy.domain.dto;

import com.hackathon.sharedeconomy.domain.entity.ForSale;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by YoungMan on 2019-02-15.
 */

@Getter
@Setter
public class ForSaleResponseDto {

    private ForSale forSale;
    private String userId;
    private String address;
    private String phoneNumber;

    public ForSaleResponseDto() {
    }

    @Builder
    public ForSaleResponseDto(ForSale forSale, String userId, String address, String phoneNumber) {
        this.forSale = forSale;
        this.userId = userId;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}

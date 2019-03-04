package com.hackathon.sharedeconomy.model.dto;

import com.hackathon.sharedeconomy.model.entity.ForSale;
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
    private String phoneNumber;
    private String address;

    public ForSaleResponseDto() {
    }

    @Builder
    public ForSaleResponseDto(ForSale forSale, String userId, String phoneNumber, String address) {
        this.forSale = forSale;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}

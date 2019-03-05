package com.hackathon.sharedeconomy.model.dto;

import com.hackathon.sharedeconomy.model.entity.ForSale;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by YoungMan on 2019-03-04.
 */

public class ForSaleDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private String userId;
        private String address;

        @Builder
        public Request(String userId, String address) {
            this.userId = userId;
            this.address = address;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private ForSale forSale;
        private String userId;
        private String phoneNumber;
        private String address;

        @Builder
        public Response(ForSale forSale, String userId, String phoneNumber, String address) {
            this.forSale = forSale;
            this.userId = userId;
            this.phoneNumber = phoneNumber;
            this.address = address;
        }
    }

}

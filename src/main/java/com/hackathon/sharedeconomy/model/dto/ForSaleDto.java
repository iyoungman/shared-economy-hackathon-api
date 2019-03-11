package com.hackathon.sharedeconomy.model.dto;

import com.hackathon.sharedeconomy.model.entity.ForSale;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Register {
        private Long price;
        private String name;
        private String userId;
//        private List<String> imagePath = new ArrayList<>();
        private String imagePath;

        @Builder
        public Register(Long price, String name, String userId, String imagePath) {
            this.price = price;
            this.name = name;
            this.userId = userId;
            this.imagePath = imagePath;
        }
    }

    /*
     * 매물정보
     * 해당 매물의 사용자 정보
     */
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ResponseDto {
        private Long id;
        private Long price;
        private String name;
        private String imagePath;
        private String userId;
        private String phoneNumber;
        private String address;
        private String userName;
        private Integer age;

        @Builder
        public ResponseDto(Long id, Long price, String name, String imagePath, String userId, String phoneNumber, String address, String userName, Integer age) {
            this.id = id;
            this.price = price;
            this.name = name;
            this.imagePath = imagePath;
            this.userId = userId;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.userName = userName;
            this.age = age;
        }
    }

}

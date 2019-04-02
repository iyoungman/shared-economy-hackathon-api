package com.hackathon.sharedeconomy.model.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.hackathon.sharedeconomy.model.entity.AdditionalInfo;
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
    public static class SearchRequest {
        private String userId;
        private String address;

        @Builder
        public SearchRequest(String userId, String address) {
            this.userId = userId;
            this.address = address;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class RegisterRequest {
        private Long price;
        private String name;
        private String userId;
        private String imagePath;
        private String preferredSex;
        private String offerFirst;
        private String offerSecond;
        private String precautionFirst;
        private String precautionSecond;

        @Builder
        public RegisterRequest(Long price, String name, String userId, String imagePath, String preferredSex, String offerFirst, String offerSecond, String precautionFirst, String precautionSecond) {
            this.price = price;
            this.name = name;
            this.userId = userId;
            this.imagePath = imagePath;
            this.preferredSex = preferredSex;
            this.offerFirst = offerFirst;
            this.offerSecond = offerSecond;
            this.precautionFirst = precautionFirst;
            this.precautionSecond = precautionSecond;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ForSaleInfo {
        private Long id;
        private Long price;
        private String name;
        private String imagePath;
        private String userId;
        private String phoneNumber;
        private String address;
        private String userName;
        private Integer age;

        @JsonUnwrapped
        private AdditionalInfo additionalInfo;

        @Builder
        public ForSaleInfo(Long id, Long price, String name, String imagePath, String userId, String phoneNumber, String address, String userName, Integer age, AdditionalInfo additionalInfo) {
            this.id = id;
            this.price = price;
            this.name = name;
            this.imagePath = imagePath;
            this.userId = userId;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.userName = userName;
            this.age = age;
            this.additionalInfo = additionalInfo;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ForSaleResponse {

        private boolean success;
        private List<ForSaleInfo> forSaleInfos = new ArrayList<>();

        @Builder
        public ForSaleResponse(boolean success, List<ForSaleInfo> forSaleInfos) {
            this.success = success;
            this.forSaleInfos = forSaleInfos;
        }
    }

}

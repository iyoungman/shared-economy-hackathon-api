package com.hackathon.sharedeconomy.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by YoungMan on 2019-02-14.
 */

@Getter
@Setter
public class ForSaleSaveDto {

    private Long price;
    private String name;
    private String userId;
    private List<String> imagePath;

    public ForSaleSaveDto() {
    }

    @Builder
    public ForSaleSaveDto(Long price, String name, String userId, List<String> imagePath) {
        this.price = price;
        this.name = name;
        this.userId = userId;
        this.imagePath = imagePath;
    }
}

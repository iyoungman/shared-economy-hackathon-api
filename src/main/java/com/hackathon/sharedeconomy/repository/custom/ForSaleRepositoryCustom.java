package com.hackathon.sharedeconomy.repository.custom;

import com.hackathon.sharedeconomy.domain.dto.ForSaleResponseDto;

import java.util.List;

/**
 * Created by YoungMan on 2019-02-23.
 */

public interface ForSaleRepositoryCustom {

    List<ForSaleResponseDto> getForSaleResponseDtos(String userId, String address);
}

package com.hackathon.sharedeconomy.repository.custom;

import com.hackathon.sharedeconomy.model.dto.ForSaleRequestDto;
import com.hackathon.sharedeconomy.model.dto.ForSaleResponseDto;

import java.util.List;

/**
 * Created by YoungMan on 2019-02-23.
 */

public interface ForSaleRepositoryCustom {

    List<ForSaleResponseDto> getForSaleResponseDtos(ForSaleRequestDto forSaleRequestDto);
}

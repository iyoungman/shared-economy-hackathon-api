package com.hackathon.sharedeconomy.repository.custom;

import com.hackathon.sharedeconomy.model.dto.ForSaleDto;

import java.util.List;

/**
 * Created by YoungMan on 2019-02-23.
 */

public interface ForSaleRepositoryCustom {

    List<ForSaleDto.ForSaleInfo> getForSaleResponseDtos(ForSaleDto.SearchRequest searchRequestDto);

    List<ForSaleDto.ForSaleInfo> getForSaleResponseDtosByShopping(String userId);
}

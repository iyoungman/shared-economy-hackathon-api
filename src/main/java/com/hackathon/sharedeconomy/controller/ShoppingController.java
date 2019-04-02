package com.hackathon.sharedeconomy.controller;

import com.hackathon.sharedeconomy.model.dto.CommonDto;
import com.hackathon.sharedeconomy.model.dto.ShoppingDto;
import com.hackathon.sharedeconomy.model.entity.Shopping;
import com.hackathon.sharedeconomy.service.ShoppingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by YoungMan on 2019-02-15.
 */

@Api(description = "찜(장바구니) 관련 API")
@RestController
@RequestMapping(value = "/shared/shopping")
public class ShoppingController {

    private ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @ApiOperation(value = "찜할 매물 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "찜하는 유저의 ID"),
            @ApiImplicitParam(name = "forSaleUserId", value = "등록할 매물 유저의 ID")
    })
    @PostMapping
    public CommonDto saveShopping(@RequestBody ShoppingDto.Save saveDto) {
        return shoppingService.saveShopping(saveDto);
    }

}

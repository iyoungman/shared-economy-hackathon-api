package com.hackathon.sharedeconomy.controller;

import com.hackathon.sharedeconomy.model.dto.ForSaleDto;
import com.hackathon.sharedeconomy.model.entity.Shopping;
import com.hackathon.sharedeconomy.service.ForSaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "매물 관련 API")
@RestController
@RequestMapping(value = "/shared/forsale")
public class ForSaleController {

    private ForSaleService forSaleService;

    public ForSaleController(ForSaleService forSaleService) {
        this.forSaleService = forSaleService;
    }

    /*@ApiOperation(value = "로그인 후 매물 페이지, 로그인 한 사용자 주소에 해당하는 매물 목록 리스트")
    @GetMapping("/id/{id}")
    public List<ForSaleDto.Response> getForSaleById(@PathVariable("id") String userId) {
        ForSaleDto.Request requestDto = ForSaleDto.Request.builder()
                .userId(userId)
                .build();

        return forSaleService.getForSaleResponseDtos(requestDto);
    }


    @ApiOperation(value = "주소 검색시에 나오는 매물 목록 리스트")
    @GetMapping("/address/{address}")
    public List<ForSaleDto.Response> getForSaleListByRegion(@PathVariable("address") String address) {
        ForSaleDto.Request requestDto = ForSaleDto.Request.builder()
                .address(address)
                .build();

        return forSaleService.getForSaleResponseDtos(requestDto);
    }*/

    @ApiOperation(value = "로그인 후 매물 페이지, 로그인 한 사용자 주소에 해당하는 매물 목록 리스트")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "로그인한 유저의 ID"),
    })
    @GetMapping("/id/{id}")
    public List<ForSaleDto.ResponseDto> getForSaleById(@PathVariable("id") String userId) {
        ForSaleDto.Request requestDto = ForSaleDto.Request.builder()
                .userId(userId)
                .build();

        return forSaleService.getForSaleResponseDtos(requestDto);
    }


    @ApiOperation(value = "주소 검색시에 나오는 매물 목록 리스트")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "검색한 주소"),
    })
    @GetMapping("/address/{address}")
    public List<ForSaleDto.ResponseDto> getForSaleListByRegion(@PathVariable("address") String address) {
        ForSaleDto.Request requestDto = ForSaleDto.Request.builder()
                .address(address)
                .build();

        return forSaleService.getForSaleResponseDtos(requestDto);
    }


    @ApiOperation(value = "매물 정보 등록(매물은 아이디 당 하나씩 등록가능, 매물을 하나 더 등록하려면 '@PutMapping(shared/forsale/id/{id})' 통해 기존의 매물 상태를 판매완료로 변경 후 새로 등록 가능)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "price", value = "매물 가격"),
            @ApiImplicitParam(name = "name", value = "매물 이름"),
            @ApiImplicitParam(name = "userId", value = "매물을 등록할 유저의 ID"),
            @ApiImplicitParam(name = "imagePath", value = "base64 매물 이미지(한장만 등록)"),
    })
    @PostMapping
    public void saveForSale(@RequestBody ForSaleDto.Register registerDto) {
        forSaleService.saveForSale(registerDto);
    }


    @ApiOperation(value = "매물 정보 수정(매물가격 또는 이름 수정, 이미지 수정 보류)")
    @PutMapping
    public void updateForSale(@RequestBody ForSaleDto.Register registerDto) {
        forSaleService.updateForSale(registerDto);
    }


    @ApiOperation(value = "해당 유저의 매물 판매완료로 변경, 판매완료로 변경하면 매물 리스트에 나오지 않음")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "유저의 ID"),
    })
    @PutMapping("/id/{id}")
    public void changeSaleType(@PathVariable("id") String userId) {
        forSaleService.updateSaleType(userId);
    }

    @ApiOperation(value = "해당 유저의 찜한 매물 목록 리스트")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "유저의 ID"),
    })
    @GetMapping("shopping/id/{id}")
    public List<ForSaleDto.ResponseDto> getShoppingsByUserId(@PathVariable("id") String userId) {
        return forSaleService.getForSaleResponseDtosByShopping(userId);
    }



   /* @ApiOperation(value = "매물 정보 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "forSaleSaveDto", dataType = "ForSaleSaveDto"),
    })
    @PutMapping
    public void updateForSale(@RequestBody ForSaleSaveDto forSaleSaveDto) {

    }*/
}

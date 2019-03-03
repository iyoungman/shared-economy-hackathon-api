package com.hackathon.sharedeconomy.controller;

import com.hackathon.sharedeconomy.model.dto.ForSaleRequestDto;
import com.hackathon.sharedeconomy.model.dto.ForSaleResponseDto;
import com.hackathon.sharedeconomy.model.dto.ForSaleSaveDto;
import com.hackathon.sharedeconomy.service.ForSaleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/shared/forsale")
public class ForSaleController {

    private ForSaleService forSaleService;

    public ForSaleController(ForSaleService forSaleService) {
        this.forSaleService = forSaleService;
    }

    @ApiOperation(value = "로그인 후 매물 페이지, 로그인 한 사용자 주소에 해당하는 매물 목록 리스트")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "사용자 id" , dataType = "string", paramType = "path"),
    })
    @GetMapping("/id/{id}")
    public List<ForSaleResponseDto> getForSaleById(@PathVariable("id") String userId) {
        ForSaleRequestDto forSaleRequestDto = ForSaleRequestDto.builder()
                .userId(userId)
                .build();

        return forSaleService.getForSaleResponseDtos(forSaleRequestDto);
    }


    @ApiOperation(value = "주소 검색시에 나오는 매물 목록 리스트")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "검색한 주소" , dataType = "string", paramType = "path"),
    })
    @GetMapping("/address/{address}")
    public List<ForSaleResponseDto> getForSaleListByRegion(@PathVariable("address") String address) {
        ForSaleRequestDto forSaleRequestDto = ForSaleRequestDto.builder()
                .address(address)
                .build();

        return forSaleService.getForSaleResponseDtos(forSaleRequestDto);
    }


    @ApiOperation(value = "매물 정보 등록(저장)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "forSaleSaveDto", dataType = "ForSaleSaveDto"),
    })
    @PostMapping
    public void saveForSale(@RequestBody ForSaleSaveDto forSaleSaveDto) {
        forSaleService.saveForSale(forSaleSaveDto);
    }

   /* @ApiOperation(value = "매물 정보 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "forSaleSaveDto", dataType = "ForSaleSaveDto"),
    })
    @PutMapping
    public void updateForSale(@RequestBody ForSaleSaveDto forSaleSaveDto) {

    }*/
}

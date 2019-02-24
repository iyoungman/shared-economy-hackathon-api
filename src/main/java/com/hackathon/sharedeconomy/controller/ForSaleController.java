package com.hackathon.sharedeconomy.controller;

import com.hackathon.sharedeconomy.domain.dto.ForSaleRequestDto;
import com.hackathon.sharedeconomy.domain.dto.ForSaleResponseDto;
import com.hackathon.sharedeconomy.domain.dto.ForSaleSaveDto;
import com.hackathon.sharedeconomy.service.ForSaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/shared/forsale")
public class ForSaleController {

    private ForSaleService forSaleService;

    public ForSaleController(ForSaleService forSaleService) {
        this.forSaleService = forSaleService;
    }

    @GetMapping("/id/{id}")
    public List<ForSaleResponseDto> getForSaleById(@PathVariable("id") String userId){
        ForSaleRequestDto forSaleRequestDto = ForSaleRequestDto.builder()
                .userId(userId)
                .build();

        return forSaleService.getForSaleResponseDtos(forSaleRequestDto);
    }

    @GetMapping("/address/{address}")
    public List<ForSaleResponseDto> getForSaleListByRegion(@PathVariable("address") String address){
        ForSaleRequestDto forSaleRequestDto = ForSaleRequestDto.builder()
                .address(address)
                .build();

        return forSaleService.getForSaleResponseDtos(forSaleRequestDto);
    }

    @PostMapping("/save")
    public void saveForSale(@RequestBody ForSaleSaveDto forSaleSaveDto){
        forSaleService.saveForSale(forSaleSaveDto);
    }

    @PostMapping("/update")
    public void updateForSale(@RequestBody ForSaleSaveDto forSaleSaveDto){

    }
}

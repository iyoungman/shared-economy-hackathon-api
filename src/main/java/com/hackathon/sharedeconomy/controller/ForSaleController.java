package com.hackathon.sharedeconomy.controller;

import com.hackathon.sharedeconomy.model.dto.ForSaleRequestDto;
import com.hackathon.sharedeconomy.model.dto.ForSaleResponseDto;
import com.hackathon.sharedeconomy.model.dto.ForSaleSaveDto;
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
    public List<ForSaleResponseDto> getForSaleById(@PathVariable("id") String userId) {
        ForSaleRequestDto forSaleRequestDto = ForSaleRequestDto.builder()
                .userId(userId)
                .build();

        return forSaleService.getForSaleResponseDtos(forSaleRequestDto);
    }

    @GetMapping("/address/{address}")
    public List<ForSaleResponseDto> getForSaleListByRegion(@PathVariable("address") String address) {
        ForSaleRequestDto forSaleRequestDto = ForSaleRequestDto.builder()
                .address(address)
                .build();

        return forSaleService.getForSaleResponseDtos(forSaleRequestDto);
    }

    @PostMapping
    public void saveForSale(@RequestBody ForSaleSaveDto forSaleSaveDto) {
        forSaleService.saveForSale(forSaleSaveDto);
    }

    @PutMapping
    public void updateForSale(@RequestBody ForSaleSaveDto forSaleSaveDto) {

    }
}

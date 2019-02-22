package com.hackathon.sharedeconomy.controller;

import com.hackathon.sharedeconomy.domain.dto.ForSaleSaveDto;
import com.hackathon.sharedeconomy.domain.entity.ForSale;
import com.hackathon.sharedeconomy.service.ForSaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/forsale")
public class ForSaleController {

    private ForSaleService forSaleService;

    public ForSaleController(ForSaleService forSaleService) {
        this.forSaleService = forSaleService;
    }

    @GetMapping("/id/{id}")
    public List<ForSale> getForSaleById(@PathVariable("id") String userId){
        return forSaleService.getForSaleByUserId(userId);
    }

    @GetMapping("/address/{address}")
    public List<ForSale> getForSaleListByRegion(@PathVariable("address") String address){
        return forSaleService.getForSaleByAddress(address);
    }

    @PostMapping("/save")
    public void saveForSale(@RequestBody ForSaleSaveDto forSaleSaveDto){
        forSaleService.saveForSale(forSaleSaveDto);
    }

    @PostMapping("/update")
    public void updateForSale(@RequestBody ForSaleSaveDto forSaleSaveDto){

    }
}

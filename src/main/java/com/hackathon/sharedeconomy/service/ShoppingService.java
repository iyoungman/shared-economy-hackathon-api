package com.hackathon.sharedeconomy.service;

import com.hackathon.sharedeconomy.model.dto.ShoppingSaveDto;
import com.hackathon.sharedeconomy.model.entity.Shopping;
import com.hackathon.sharedeconomy.repository.ShoppingRepository;
import org.springframework.stereotype.Service;

/**
 * Created by YoungMan on 2019-02-14.
 */

@Service
public class ShoppingService {

    private ShoppingRepository shoppingRepository;
    private LoginService loginService;
    private ForSaleService forSaleService;

    public ShoppingService(ShoppingRepository shoppingRepository, LoginService loginService, ForSaleService forSaleService) {
        this.shoppingRepository = shoppingRepository;
        this.loginService = loginService;
        this.forSaleService = forSaleService;
    }

    public void saveShopping(ShoppingSaveDto shoppingSaveDto) {
        Shopping shopping = Shopping.builder()
                .forSale(forSaleService.findByName(shoppingSaveDto.getName()))
                .user(loginService.findById(shoppingSaveDto.getUserId()))
                .build();

        shoppingRepository.save(shopping);
    }
}

package com.hackathon.sharedeconomy.controller;

import com.hackathon.sharedeconomy.model.dto.ShoppingSaveDto;
import com.hackathon.sharedeconomy.service.ShoppingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YoungMan on 2019-02-15.
 */

@RestController
@RequestMapping(value = "/shared/shopping")
public class ShoppingController {

    private ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @PostMapping
    public void saveShopping(ShoppingSaveDto shoppingSaveDto) {
        shoppingService.saveShopping(shoppingSaveDto);
    }
}

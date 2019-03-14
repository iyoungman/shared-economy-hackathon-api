package com.hackathon.sharedeconomy.service;

import com.hackathon.sharedeconomy.model.dto.ForSaleDto;
import com.hackathon.sharedeconomy.model.dto.ShoppingDto;
import com.hackathon.sharedeconomy.model.entity.Shopping;
import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.repository.ShoppingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YoungMan on 2019-02-14.
 */

@Service
public class ShoppingService {

    private ShoppingRepository shoppingRepository;
    private UserService userService;
    private ForSaleService forSaleService;

    public ShoppingService(ShoppingRepository shoppingRepository, UserService userService, ForSaleService forSaleService) {
        this.shoppingRepository = shoppingRepository;
        this.userService = userService;
        this.forSaleService = forSaleService;
    }

    public void saveShopping(ShoppingDto.Save saveDto) {
        Shopping shopping = Shopping.builder()
                .forSale(forSaleService.findByUserId(saveDto.getForSaleUserId()))
                .user(userService.findById(saveDto.getUserId()))
                .build();

        shoppingRepository.save(shopping);
    }
}

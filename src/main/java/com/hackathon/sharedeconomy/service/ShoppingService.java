package com.hackathon.sharedeconomy.service;

import com.hackathon.sharedeconomy.exception.UserDefineException;
import com.hackathon.sharedeconomy.model.dto.CommonDto;
import com.hackathon.sharedeconomy.model.dto.ShoppingDto;
import com.hackathon.sharedeconomy.model.entity.Shopping;
import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.repository.ShoppingRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

    public CommonDto saveShopping(ShoppingDto.Save saveDto) {

        if (forSaleService.isEmptyForSaleByUserId(saveDto.getForSaleUserId())) {
            throw new UserDefineException("해당 유저의 매물이 없습니다.", false);
        }

        Shopping existShopping = shoppingRepository.findByUserIdAndForSaleUserId(saveDto.getUserId(), saveDto.getForSaleUserId());
        if(!ObjectUtils.isEmpty(existShopping)) {
            throw new UserDefineException("이미 찜한 매물입니다.", false);
        }

        Shopping shopping = Shopping.builder()
                .forSale(forSaleService.findByUserId(saveDto.getForSaleUserId()))
                .user(userService.findById(saveDto.getUserId()))
                .build();

        try {
            shoppingRepository.save(shopping);
        } catch (Exception e) {
            throw new UserDefineException("저장 실패", false);
        }

        return CommonDto.builder()
                .success(true)
                .message("저장 성공")
                .build();
    }


}

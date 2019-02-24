package com.hackathon.sharedeconomy;

import com.hackathon.sharedeconomy.model.dto.ForSaleRequestDto;
import com.hackathon.sharedeconomy.model.dto.ForSaleSaveDto;
import com.hackathon.sharedeconomy.model.dto.ShoppingSaveDto;
import com.hackathon.sharedeconomy.model.entity.ForSale;
import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.service.ForSaleService;
import com.hackathon.sharedeconomy.service.LoginService;
import com.hackathon.sharedeconomy.service.ShoppingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-02-15.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class tests {

    @Autowired
    private ForSaleService forSaleService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ShoppingService shoppingService;


    @Test
    public void findByName() {
        String name = "매물";
        ForSale forSale = forSaleService.findByName(name);
        System.out.println("=========================");
        System.out.println(forSale.getName());
    }

    @Test
    public void saveForSale() {
        String userId = "testid3";
        User user = loginService.findById(userId);
        System.out.println(user.getAddress());

        List<String> imgs = new ArrayList<>();
        imgs.add("img1");
        imgs.add("img2");

        ForSaleSaveDto forSaleSaveDto = ForSaleSaveDto.builder()
                .name("매물2")
                .price(10000L)
                .userId(userId)
                .imagePath(imgs)
                .build();

        forSaleService.saveForSale(forSaleSaveDto);
    }



    @Test
    public void saveShopping() {
        ShoppingSaveDto shoppingSaveDto = ShoppingSaveDto.builder()
                .name("매물")
                .userId("testid")
                .build();

        shoppingService.saveShopping(shoppingSaveDto);

    }

    @Test
    public void getForSaleResponse() {
        ForSaleRequestDto forSaleRequestDto = ForSaleRequestDto.builder()
                .userId("testid")
                .build();

        /*ForSaleRequestDto forSaleRequestDto = ForSaleRequestDto.builder()
                .address("중계1동")
                .build();*/

        forSaleService.getForSaleResponseDtos(forSaleRequestDto);
    }


}

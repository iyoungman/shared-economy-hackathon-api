package com.hackathon.sharedeconomy.service;

import com.hackathon.sharedeconomy.model.dto.ForSaleRequestDto;
import com.hackathon.sharedeconomy.model.dto.ForSaleResponseDto;
import com.hackathon.sharedeconomy.model.dto.ForSaleSaveDto;
import com.hackathon.sharedeconomy.model.entity.ForSale;
import com.hackathon.sharedeconomy.model.entity.Image;
import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.repository.ForSaleRepository;
import com.hackathon.sharedeconomy.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-02-14.
 */

@Service
public class ForSaleService {

    private ForSaleRepository forSaleRepository;
    private LoginService loginService;
    private ImageRepository imageRepository;

    public ForSaleService(ForSaleRepository forSaleRepository, LoginService loginService, ImageRepository imageRepository) {
        this.forSaleRepository = forSaleRepository;
        this.loginService = loginService;
        this.imageRepository = imageRepository;
    }

    public ForSale findByName(String name) {
        return forSaleRepository.findByName(name);
    }

    public void saveForSale(ForSaleSaveDto forSaleSaveDto) {
        User user = loginService.findById(forSaleSaveDto.getUserId());
        List<String> imageList = forSaleSaveDto.getImagePath();
        List<Image> images = new ArrayList<>();

        ForSale forSale = ForSale.builder()
                .name(forSaleSaveDto.getName())
                .price(forSaleSaveDto.getPrice())
                .user(user)
                .build();

        for (String anImageList : imageList) {
            images.add(Image.builder()
                    .path(anImageList)
                    .forSale(forSale)
                    .build());
        }

        imageRepository.saveAll(images);
    }

    public List<ForSaleResponseDto> getForSaleResponseDtos(ForSaleRequestDto forSaleRequestDto) {
        return forSaleRepository.getForSaleResponseDtos(forSaleRequestDto);
    }


}

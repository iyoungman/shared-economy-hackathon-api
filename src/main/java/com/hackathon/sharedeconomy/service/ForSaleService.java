package com.hackathon.sharedeconomy.service;

import com.hackathon.sharedeconomy.exception.UserDefineException;
import com.hackathon.sharedeconomy.model.dto.ForSaleRequestDto;
import com.hackathon.sharedeconomy.model.dto.ForSaleResponseDto;
import com.hackathon.sharedeconomy.model.dto.ForSaleSaveDto;
import com.hackathon.sharedeconomy.model.entity.ForSale;
import com.hackathon.sharedeconomy.model.entity.Image;
import com.hackathon.sharedeconomy.model.entity.Shopping;
import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.model.enums.SaleType;
import com.hackathon.sharedeconomy.repository.ForSaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungMan on 2019-02-14.
 */

@Service
public class ForSaleService {

    private ForSaleRepository forSaleRepository;
    private LoginService loginService;
    private ImageService imageService;

    public ForSaleService(ForSaleRepository forSaleRepository, LoginService loginService, ImageService imageService) {
        this.forSaleRepository = forSaleRepository;
        this.loginService = loginService;
        this.imageService = imageService;
    }

    /*
     * 한 사람당 여러개의 매물 올릴 수 있을 때 사용
     */
    public ForSale findByNameAndUserId(String name, String forSaleUserId) {
        return forSaleRepository.findByNameAndUserId(name, forSaleUserId);
    }

    /*
     * 한 사람당 하나의 매물만 올릴 수 있을 때 사용
     * 현재 판매되고 있는 매물만 찾는다.
     */
    public ForSale findByUserId(String forSaleUserId) {
        return forSaleRepository.findByUserId(forSaleUserId);
    }

    /*
     * 연관 관계에 의해 imageService의 사진이 저장되면서 forSale정보가 등록된다.
     */
    public void saveForSale(ForSaleSaveDto forSaleSaveDto) {
        if (!isEmptyForSaleByUserId(forSaleSaveDto.getUserId())) {
            throw new UserDefineException("해당 유저의 매물이 등록되어 있습니다.");
        }

        User user = loginService.findById(forSaleSaveDto.getUserId());
        List<String> imageList = forSaleSaveDto.getImagePath();
        List<Image> images = new ArrayList<>();

        ForSale forSale = ForSale.builder()
                .name(forSaleSaveDto.getName())
                .price(forSaleSaveDto.getPrice())
                .user(user)
                .build();

        for (int i = 0; i < imageList.size(); i++) {
            String writeFileName = user.getId() + "img" + String.valueOf(i);//file 이름 저장 형식 : 중복되지 않기위해 userId + img + 0,1,2..
            String writeFilePath = imageService.convertBase64ToImgFile(imageList.get(i), writeFileName);

            images.add(Image.builder()
                    .path(writeFilePath)
                    .forSale(forSale)
                    .build());
        }

        imageService.saveAll(images);
    }

    private Boolean isEmptyForSaleByUserId(String forSaleUserId) {
        return ObjectUtils.isEmpty(findByUserId(forSaleUserId));
    }

    /*
     * 이미지 수정 보류
     */
    public void updateForSale(ForSaleSaveDto forSaleSaveDto) {
        ForSale forSale = findByUserId(forSaleSaveDto.getUserId());
        forSale.updateForSale(forSaleSaveDto);
        forSaleRepository.save(forSale);
    }

    public List<ForSaleResponseDto> getForSaleResponseDtos(ForSaleRequestDto forSaleRequestDto) {
        List<ForSaleResponseDto> forSaleResponseDtos = forSaleRepository.getForSaleResponseDtos(forSaleRequestDto);
        return convertImgToBase64(forSaleResponseDtos);
    }

    private List<ForSaleResponseDto> convertImgToBase64(List<ForSaleResponseDto> forSaleResponseDtos) {

        for (ForSaleResponseDto forSaleResponseDto : forSaleResponseDtos) {
            ForSale forSale = forSaleResponseDto.getForSale();
            List<Image> images = forSale.getImages();

            for (Image image : images) {
                image.updatePath(imageService.convertImgFileToBase64(image.getPath()));
            }
        }

        return forSaleResponseDtos;
    }

    public void updateSaleType(String userId) {
        ForSale forSale = findByUserId(userId);

        if (forSale.getSaleType() == SaleType.SALE) {
            forSale.updateSaleType();
            forSaleRepository.save(forSale);
        }
    }


}

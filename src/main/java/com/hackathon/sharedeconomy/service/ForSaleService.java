package com.hackathon.sharedeconomy.service;

import com.hackathon.sharedeconomy.exception.UserDefineException;
import com.hackathon.sharedeconomy.model.dto.CommonDto;
import com.hackathon.sharedeconomy.model.dto.ForSaleDto;
import com.hackathon.sharedeconomy.model.entity.AdditionalInfo;
import com.hackathon.sharedeconomy.model.entity.ForSale;
import com.hackathon.sharedeconomy.model.entity.Image;
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

    //    private final String URL_PATH = "http://54.180.67.243:8094/imgfile/";
    private final String URL_PATH = "http://15.164.57.47:8094/imgfile/";
    private ForSaleRepository forSaleRepository;
    private UserService userService;
    private ImageService imageService;

    public ForSaleService(ForSaleRepository forSaleRepository, UserService userService, ImageService imageService) {
        this.forSaleRepository = forSaleRepository;
        this.userService = userService;
        this.imageService = imageService;
    }

    //한 사람당 여러개의 매물 올릴 수 있을 때 사용
    public ForSale findByNameAndUserId(String name, String forSaleUserId) {
        return forSaleRepository.findByNameAndUserId(name, forSaleUserId);
    }

    //한 사람당 하나의 매물만 올릴 수 있을 때 사용, 현재 판매되고 있는 매물만 찾는다.
    public ForSale findByUserId(String forSaleUserId) {
        return forSaleRepository.findByUserId(forSaleUserId);
    }

    //연관 관계에 의해 imageService 의 사진이 저장되면서 forSale 정보가 등록된다.
    public CommonDto saveForSale(ForSaleDto.RegisterRequest registerDto) {

        if (!isEmptyForSaleByUserId(registerDto.getUserId())) {
            throw new UserDefineException("해당 유저의 매물이 등록되어 있습니다.", false);
        }

        User user = userService.findById(registerDto.getUserId());
        List<String> imageList = new ArrayList<>();
        imageList.add(registerDto.getImagePath());
        List<Image> images = new ArrayList<>();
        AdditionalInfo additionalInfo = AdditionalInfo.builder()
                .offerFirst(registerDto.getOfferFirst())
                .offerSecond(registerDto.getOfferSecond())
                .precautionFirst(registerDto.getPrecautionFirst())
                .precautionSecond(registerDto.getPrecautionSecond())
                .build();

        ForSale forSale = ForSale.builder()
                .name(registerDto.getName())
                .price(registerDto.getPrice())
                .preferredSex(registerDto.getPreferredSex())
                .additionalInfo(additionalInfo)
                .user(user)
                .build();

        for (int i = 0; i < imageList.size(); i++) {
            String writeFileName = user.getId() + "img" + String.valueOf(i);//file 이름 저장 형식 : 중복되지 않기위해 userId + img + 0,1,2..
            String writeFilePath = imageService.convertBase64ToImgFile(imageList.get(i), writeFileName);

            images.add(Image.builder()
                    .path(writeFilePath)
                    .url(URL_PATH + writeFileName + ".png")
                    .forSale(forSale)
                    .build());
        }
        imageService.saveAll(images);

        return CommonDto.builder()
                .success(true)
                .message("매물 등록 성공")
                .build();
    }

    public Boolean isEmptyForSaleByUserId(String forSaleUserId) {
        return ObjectUtils.isEmpty(findByUserId(forSaleUserId));
    }

    //이미지 수정 보류
    public CommonDto updateForSale(ForSaleDto.RegisterRequest registerDto) {

        ForSale forSale = findByUserId(registerDto.getUserId());
        forSale.updateForSale(registerDto);
        forSaleRepository.save(forSale);

        return CommonDto.builder()
                .success(true)
                .message("매물지 수정 성공")
                .build();
    }

    public List<ForSaleDto.ForSaleInfo> getForSaleResponseDtos(ForSaleDto.SearchRequest searchRequestDto) {

        List<ForSaleDto.ForSaleInfo> forSaleInfos = forSaleRepository.getForSaleResponseDtos(searchRequestDto);

        if (ObjectUtils.isEmpty(forSaleInfos)) {
            throw new UserDefineException("해당 조건의 판매중인 매물이 없습니다.", false);
        }
        return forSaleInfos;
    }

    public CommonDto updateSaleType(String userId) {
        ForSale forSale = findByUserId(userId);

        if (forSale.getSaleType() == SaleType.SALE) {
            forSale.updateSaleType();
            forSaleRepository.save(forSale);
        }
        return CommonDto.builder()
                .success(true)
                .message("매물 판매완료로 변경")
                .build();
    }

    public List<ForSaleDto.ForSaleInfo> getForSaleResponseDtosByShopping(String userId) {

        List<ForSaleDto.ForSaleInfo> forSaleInfos = forSaleRepository.getForSaleResponseDtosByShopping(userId);

        if (ObjectUtils.isEmpty(forSaleInfos)) {
            throw new UserDefineException("해당 조건의 판매중인 매물이 없습니다.", false);
        }
        return forSaleInfos;
    }


    /*private List<ForSaleDto.Response> convertImgToBase64(List<ForSaleDto.Response> responseDtos) {

        for (ForSaleDto.Response responseDto : responseDtos) {
            ForSale forSale = responseDto.getForSale();
            List<Image> images = forSale.getImages();

            for (Image image : images) {
                image.updatePath(imageService.convertImgFileToBase64(image.getPath()));
            }
        }
        return responseDtos;
    }*/



}

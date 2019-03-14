package com.hackathon.sharedeconomy.service;

import com.hackathon.sharedeconomy.exception.UserDefineException;
import com.hackathon.sharedeconomy.model.dto.ForSaleDto;
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

    private final String URLPATH = "http://54.180.67.243:8094/imgfile/";
    private ForSaleRepository forSaleRepository;
    private UserService userService;
    private ImageService imageService;

    public ForSaleService(ForSaleRepository forSaleRepository, UserService userService, ImageService imageService) {
        this.forSaleRepository = forSaleRepository;
        this.userService = userService;
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
    public void saveForSale(ForSaleDto.Register registerDto) {
        if (!isEmptyForSaleByUserId(registerDto.getUserId())) {
            throw new UserDefineException("해당 유저의 매물이 등록되어 있습니다.");
        }

        User user = userService.findById(registerDto.getUserId());
//        List<String> imageList = registerDto.getImagePath();
        List<String> imageList = new ArrayList<>();
        imageList.add(registerDto.getImagePath());
        List<Image> images = new ArrayList<>();

        ForSale forSale = ForSale.builder()
                .name(registerDto.getName())
                .price(registerDto.getPrice())
                .user(user)
                .build();

        for (int i = 0; i < imageList.size(); i++) {
            String writeFileName = user.getId() + "img" + String.valueOf(i);//file 이름 저장 형식 : 중복되지 않기위해 userId + img + 0,1,2..
            String writeFilePath = imageService.convertBase64ToImgFile(imageList.get(i), writeFileName);

            images.add(Image.builder()
                    .path(writeFilePath)
                    .url(URLPATH + writeFileName + ".png")
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
    public void updateForSale(ForSaleDto.Register registerDto) {
        ForSale forSale = findByUserId(registerDto.getUserId());
        forSale.updateForSale(registerDto);
        forSaleRepository.save(forSale);
    }

    /*public List<ForSaleDto.Response> getForSaleResponseDtos(ForSaleDto.Request requestDto) {
        List<ForSaleDto.Response> responseDtos = forSaleRepository.getForSaleResponseDtos(requestDto);
        return responseDtos;
        //        return convertImgToBase64(responseDtos);
    }*/

    public List<ForSaleDto.ResponseDto> getForSaleResponseDtos(ForSaleDto.Request requestDto) {
        List<ForSaleDto.ResponseDto> responseDtos = forSaleRepository.getForSaleResponseDtos(requestDto);
        return responseDtos;
        //        return convertImgToBase64(responseDtos);
    }

    private List<ForSaleDto.Response> convertImgToBase64(List<ForSaleDto.Response> responseDtos) {

        for (ForSaleDto.Response responseDto : responseDtos) {
            ForSale forSale = responseDto.getForSale();
            List<Image> images = forSale.getImages();

            for (Image image : images) {
                image.updatePath(imageService.convertImgFileToBase64(image.getPath()));
            }
        }

        return responseDtos;
    }

    public void updateSaleType(String userId) {
        ForSale forSale = findByUserId(userId);

        if (forSale.getSaleType() == SaleType.SALE) {
            forSale.updateSaleType();
            forSaleRepository.save(forSale);
        }
    }

    public List<ForSaleDto.ResponseDto> getForSaleResponseDtosByShopping(String userId) {
        return forSaleRepository.getForSaleResponseDtosByShopping(userId);
    }


}

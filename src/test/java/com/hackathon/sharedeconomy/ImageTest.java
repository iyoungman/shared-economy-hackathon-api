package com.hackathon.sharedeconomy;

import com.hackathon.sharedeconomy.service.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by YoungMan on 2019-02-27.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageTest {

    @Autowired
    private ImageService imageService;
    private final String WINDOW_PATH = "C:\\testimg\\";

    @Test
    public void imageEncodingTest() {
        String strBase64 = imageService.convertImgFileToBase64("test.png");
        System.out.println("=====================");
        System.out.println(strBase64);
    }

    @Test
    public void imageDecodingTest() {
        String strBase64 = imageService.convertImgFileToBase64("test.png");
//        imageService.convertBase64ToImgFile(strBase64);
    }
}

package com.hackathon.sharedeconomy.controller;

import com.hackathon.sharedeconomy.model.dto.UserDto;
import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(description = "회원관련 API")
@RestController
@RequestMapping(value = "/shared")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/signin")
    public UserDto.UserResponse signIn(@RequestBody UserDto.SignInRequest signInRequestDto){
        return userService.signIn(signInRequestDto.toEntity());
    }


    @ApiOperation(value = "회원가입(role 타입은 '노인'or'청년'으로 기입)")
    @PostMapping("/signup")
    public UserDto.UserResponse signUp(@RequestBody UserDto.SingUpRequest signupDto){
        return userService.signUp(signupDto.toEntity());
    }


    /*@ApiOperation(value = "회원정보 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "signupDto", dataType = "SignupDto"),
    })
    @PostMapping("/update")
    public SignupDto update(@RequestBody SignupDto signupDto){
        return loginService.update(signupDto);
    }*/
}

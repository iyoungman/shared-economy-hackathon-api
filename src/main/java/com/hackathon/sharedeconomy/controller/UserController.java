package com.hackathon.sharedeconomy.controller;

import com.hackathon.sharedeconomy.model.dto.UserDto;
import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "아이디(중복된 아이디 있으면 중복 메세지)"),
            @ApiImplicitParam(name = "pw", value = "비밀번호")
    })
    @PostMapping("/signin")
    public UserDto.UserResponse signIn(@RequestBody UserDto.SignInRequest signInRequestDto){
        return userService.signIn(signInRequestDto.toEntity());
    }


    @ApiOperation(value = "회원가입")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "아이디(중복된 아이디 있으면 중복 메세지)"),
            @ApiImplicitParam(name = "role", value = "역할은 '노인' or '청년' 둘중 하나의 형태로 기입")
    })
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

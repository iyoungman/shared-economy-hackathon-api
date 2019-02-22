package com.hackathon.sharedeconomy.controller;

import com.hackathon.sharedeconomy.domain.dto.LoginDto;
import com.hackathon.sharedeconomy.domain.dto.SignupDto;
import com.hackathon.sharedeconomy.domain.entity.User;
import com.hackathon.sharedeconomy.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shared")
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginDto loginDto){
        return loginService.login(loginDto.toEntity());
    }

    @PostMapping("/signup")
    public User signup(@RequestBody SignupDto signupDto){
        return loginService.signup(signupDto.toEntity());
    }

    @PostMapping("/update")
    public SignupDto update(@RequestBody SignupDto signupDto){
        return loginService.update(signupDto);
    }
}

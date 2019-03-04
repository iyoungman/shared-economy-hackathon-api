package com.hackathon.sharedeconomy.service;

import com.hackathon.sharedeconomy.exception.UserDefineException;
import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.repository.LoginRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public User login(User user) {
        User user1 = loginRepository.findById(user.getId()).orElseThrow(() -> new UserDefineException(user.getId() + "의 아이디를 찾을 수 없습니다."));

        if (user.getPw().equals(user1.getPw())) {
            return user1;
        } else {
            throw new UserDefineException(user.getId() + "의 비밀번호가 잘못 되었습니다.");
        }
    }

    public User signup(User user) {
        return loginRepository.save(
                loginRepository.findById(user.getId())
                        .orElseThrow(() -> new UserDefineException("아이디가 중복됩니다."))
        );
    }

    public User findById(String userId) {
        return loginRepository.findById(userId)
                .orElseThrow(() -> new UserDefineException("해당 Id의 유저가 없습니다."));
    }

    /*public SignupDto update(SignupDto dto) {
        User user = new User();
        if (loginRepository.findById(dto.getId()).isPresent())
            user = loginRepository.save(dto.toEntity());
        return SignupDto.builder()
                .id(user.getId())
                .pw(user.getPw())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getgetRole())
                .build();
    }*/
}

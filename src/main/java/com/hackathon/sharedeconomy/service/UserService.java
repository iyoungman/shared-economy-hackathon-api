package com.hackathon.sharedeconomy.service;

import com.hackathon.sharedeconomy.exception.UserDefineException;
import com.hackathon.sharedeconomy.model.dto.CommonDto;
import com.hackathon.sharedeconomy.model.dto.UserDto;
import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserDefineException.builder()
                        .originalMessage("해당 id의 유저가 없습니다.")
                        .success(false)
                        .build()
                );
    }

    public UserDto.UserResponse signIn(User connectUser) {
        User checkUser = userRepository.findById(connectUser.getId()).orElseThrow(() -> UserDefineException.builder()
                .originalMessage(connectUser.getId() + "의 아이디를 찾을 수 없습니다.")
                .success(false)
                .build()
        );

        if (connectUser.getPw().equals(checkUser.getPw())) {
            return UserDto.UserResponse.toDto(checkUser);
        } else {
            throw UserDefineException.builder()
                    .originalMessage(connectUser.getId() + "의 비밀번호가 잘못 되었습니다.")
                    .success(false)
                    .build();
        }
    }

    public UserDto.UserResponse signUp(User user) {
        if (!checkUser(user.getId())) {
            throw new UserDefineException("아이디 중복입니다.", false);
        }

        User singUpUser = userRepository.save(user);
        return UserDto.UserResponse.toDto(singUpUser);
    }

    private boolean checkUser(String userId) {
        return ObjectUtils.isEmpty(userRepository.findById(userId));
    }

    public CommonDto deleteUser(String userId) {

        try {
            userRepository.deleteById(userId);
        } catch (Exception e) {
            throw new UserDefineException("유저 삭제 실패", false);
        }

        return CommonDto.builder()
                .success(true)
                .message("유저 삭제 성공")
                .build();
    }




    /*public SignupDto update(SignupDto dto) {
        User user = new User();
        if (userRepository.findById(dto.getId()).isPresent())
            user = userRepository.save(dto.toEntity());
        return SignupDto.builder()
                .id(user.getId())
                .pw(user.getPw())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getgetRole())
                .build();
    }*/
}

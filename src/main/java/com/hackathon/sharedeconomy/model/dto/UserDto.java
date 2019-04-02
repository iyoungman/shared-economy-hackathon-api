package com.hackathon.sharedeconomy.model.dto;

import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.model.enums.RoleType;
import lombok.*;
import org.springframework.stereotype.Component;

/**
 * Created by YoungMan on 2019-03-05.
 */

public class UserDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SignInRequest {
        private String id;
        private String pw;

        public User toEntity() {
            return User.builder()
                    .id(id)
                    .pw(pw)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SingUpRequest {
        private String id;
        private String pw;
        private String name;
        private String phoneNumber;
        private String address;
        private Integer age;
        private String sex;
        private String role;

        public User toEntity() {
            return User.builder()
                    .id(id)
                    .pw(pw)
                    .name(name)
                    .phoneNumber(phoneNumber)
                    .address(address)
                    .age(age)
                    .sex(sex)
                    .role(RoleType.convertRoleType(role))
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserResponse {

        private String id;
        private String pw;
        private String name;
        private String phoneNumber;
        private String address;
        private Integer age;
        private String sex;
        private String role;
        private boolean success;

        @Builder
        public UserResponse(String id, String pw, String name, String phoneNumber, String address, Integer age, String sex, String role, boolean success) {
            this.id = id;
            this.pw = pw;
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.age = age;
            this.sex = sex;
            this.role = role;
            this.success = success;
        }

        public static UserResponse toDto(User user) {
            return UserResponse.builder()
                    .id(user.getId())
                    .pw(user.getPw())
                    .name(user.getName())
                    .phoneNumber(user.getPhoneNumber())
                    .address(user.getAddress())
                    .age(user.getAge())
                    .sex(user.getSex())
                    .role(user.getRole().getRoleExplain())
                    .success(true)
                    .build();
        }
    }
}

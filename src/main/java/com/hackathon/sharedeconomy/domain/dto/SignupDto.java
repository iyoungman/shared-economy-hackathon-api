package com.hackathon.sharedeconomy.domain.dto;

import com.hackathon.sharedeconomy.domain.entity.User;
import com.hackathon.sharedeconomy.domain.enums.RoleType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {
    private String id;

    private String pw;

    private String name;

    private RoleType role;

    private String phoneNumber;

    public User toEntity(){
        return User.builder()
                .id(id)
                .pw(pw)
                .name(name)
                .phoneNumber(phoneNumber)
                .role(role)
                .build();
    }
}

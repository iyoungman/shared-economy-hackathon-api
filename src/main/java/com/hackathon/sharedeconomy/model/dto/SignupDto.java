package com.hackathon.sharedeconomy.model.dto;

import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.model.enums.RoleType;
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

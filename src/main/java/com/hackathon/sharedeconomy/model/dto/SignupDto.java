package com.hackathon.sharedeconomy.model.dto;

import com.hackathon.sharedeconomy.model.entity.User;
import com.hackathon.sharedeconomy.model.enums.RoleType;
import lombok.*;
import org.springframework.util.StringUtils;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {

    private String id;
    private String pw;
    private String name;
    private String phoneNumber;
    private String address;
    private Integer age;
    private String role;

    public User toEntity() {
        return User.builder()
                .id(id)
                .pw(pw)
                .name(name)
                .phoneNumber(phoneNumber)
                .role(RoleType.convertRoleType(role))
                .build();
    }
}

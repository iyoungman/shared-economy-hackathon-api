package com.hackathon.sharedeconomy.domain.dto;

import com.hackathon.sharedeconomy.domain.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String id;
    private String pw;

    public User toEntity(){
        return User.builder()
                .id(id)
                .pw(pw)
                .build();
    }
}

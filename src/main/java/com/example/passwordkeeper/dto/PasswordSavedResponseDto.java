package com.example.passwordkeeper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordSavedResponseDto {

    private String userName;
    private String password;
    private String website;

    public PasswordSavedResponseDto(){}

    public PasswordSavedResponseDto(String userName, String password, String website) {
        this.userName = userName;
        this.password = password;
        this.website = website;
    }
}

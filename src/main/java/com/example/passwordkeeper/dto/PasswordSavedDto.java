package com.example.passwordkeeper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordSavedDto {

    private String website;
    private String userName;

    private String password;

}

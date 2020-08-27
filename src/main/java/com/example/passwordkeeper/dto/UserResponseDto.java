package com.example.passwordkeeper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private Long id;

    private String message;

    public UserResponseDto(){}

    public UserResponseDto(String message) {
        this.message = message;
    }

    public UserResponseDto(Long id, String message) {
        this.id = id;
        this.message = message;
    }

}

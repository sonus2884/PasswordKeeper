package com.example.passwordkeeper.controller;

import com.example.passwordkeeper.dto.*;
import com.example.passwordkeeper.model.PasswordKeeper;
import com.example.passwordkeeper.model.User;
import com.example.passwordkeeper.sevice.PasswordKeeperService;
import com.example.passwordkeeper.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordKeeperService passwordKeeperService;

    @PostMapping("/app/user")
    ResponseDto<UserResponseDto> createUser(@RequestBody UserDto userDto) {

        User user = userService.createUser(userDto);
        return new ResponseDto<>(
                HttpStatus.OK,
                new UserResponseDto(user.getId(),"account created")
        );
    }


    @PostMapping("/app/user/auth")
    ResponseDto<UserResponseDto>loginUser(@RequestBody UserDto userDto){

        User user = userService.findUser(userDto);

        return new ResponseDto<>(
                HttpStatus.OK,
                new UserResponseDto(user.getId(),"success")
        );
    }

    @PostMapping("/app/auth")
    String savePassword(@RequestBody PasswordSavedDto passwordSavedDto){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {

           String userName= authentication.getName();

//            return user_id+"";

            return passwordKeeperService.savePassword(passwordSavedDto,userName);
        }
        return "User Not Found";
    }

   @GetMapping("/app/sites/list")
   List<PasswordKeeper> getAllSavedPassword(){

       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

       if (!(authentication instanceof AnonymousAuthenticationToken)) {

           String userName= authentication.getName();

           return passwordKeeperService.getAllSavedPassword(userName);
       }

        return null;
   }
}

package com.example.passwordkeeper.controller;

import com.example.passwordkeeper.dto.*;
import com.example.passwordkeeper.exception.UserNotFoundException;
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

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordKeeperService passwordKeeperService;

    //Registration API...
    @PostMapping("/app/user")
    ResponseDto<UserResponseDto> createUser(@RequestBody UserDto userDto) {

        User user = userService.createUser(userDto);
        return new ResponseDto<>(
                HttpStatus.OK,
                new UserResponseDto(user.getId(),"account created")
        );
    }

    // Login API...
    @PostMapping("/app/user/auth")
    ResponseDto<UserResponseDto>loginUser(@RequestBody UserDto userDto){

        User user = userService.findUser(userDto);

        return new ResponseDto<>(
                HttpStatus.OK,
                new UserResponseDto(user.getId(),"success")
        );
    }

    // Saved Password Api..
    @PostMapping("/app/auth")
    String savePassword(@RequestBody PasswordSavedDto passwordSavedDto){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {

           String userName= authentication.getName();

//            return user_id+"";

            return passwordKeeperService.savePassword(passwordSavedDto,userName);
        }
       throw new UserNotFoundException("User Not Found");
    }

    // View All Saved Password API..
   @GetMapping("/app/sites/list")
   List<PasswordSavedResponseDto> getAllSavedPassword(){

       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
       if (!(authentication instanceof AnonymousAuthenticationToken)) {

           String userName= authentication.getName();

           List<String> keepers = passwordKeeperService.getAllSavedPassword(userName);


           List<PasswordSavedResponseDto> list = new ArrayList<>();

           for(String st : keepers){

               System.out.println(st);
               PasswordSavedResponseDto passwordSavedResponseDto = new PasswordSavedResponseDto();

               String[] splitStr = st.split(",");
//               for (String str : splitStr)
//                   System.out.println(str);

               passwordSavedResponseDto.setWebsite(splitStr[2]);
               passwordSavedResponseDto.setUserName(splitStr[0]);
               passwordSavedResponseDto.setPassword(splitStr[1]);

               list.add(passwordSavedResponseDto);
           }

           return list;

       }

        throw new UserNotFoundException("User Not Found");
   }
}

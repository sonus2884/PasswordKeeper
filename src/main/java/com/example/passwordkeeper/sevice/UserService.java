package com.example.passwordkeeper.sevice;

import com.example.passwordkeeper.dto.UserDto;
import com.example.passwordkeeper.model.User;
import org.springframework.stereotype.Service;


public interface UserService {

    User createUser(UserDto userDto);

    User findUser(UserDto userDto);


}

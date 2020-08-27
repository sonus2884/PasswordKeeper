package com.example.passwordkeeper.sevice;

import com.example.passwordkeeper.dto.UserDto;
import com.example.passwordkeeper.exception.UserExistsException;
import com.example.passwordkeeper.exception.UserNotFoundException;
import com.example.passwordkeeper.model.User;
import com.example.passwordkeeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserDto userDto) {

        if (userRepository.findUserByEmail(userDto.getEmail()) != null){

            throw  new UserNotFoundException("User with \'" + userDto.getEmail()+ " \' email already exists");
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User findUser(UserDto userDto) {

        if (userRepository.findUserByEmail(userDto.getEmail()) == null){

            throw  new UserExistsException("User with \'" + userDto.getEmail()+ " \' email not exists");
        }

        return userRepository.findUserByEmail(userDto.getEmail());
    }
}

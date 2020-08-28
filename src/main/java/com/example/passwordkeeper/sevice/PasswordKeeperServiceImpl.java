package com.example.passwordkeeper.sevice;

import com.example.passwordkeeper.dto.PasswordSavedDto;
import com.example.passwordkeeper.dto.PasswordSavedResponseDto;
import com.example.passwordkeeper.model.PasswordKeeper;
import com.example.passwordkeeper.model.User;
import com.example.passwordkeeper.repository.PasswordKeeperRepository;
import com.example.passwordkeeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordKeeperServiceImpl implements PasswordKeeperService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordKeeperRepository keeperRepository;

    @Override
    public String savePassword(PasswordSavedDto passwordSavedDto, String  user_id) {

        User user = userRepository.findUserByEmail(user_id);

        PasswordKeeper passwordKeeper = new PasswordKeeper();

        passwordKeeper.setWebsite(passwordSavedDto.getWebsite());
        passwordKeeper.setPassword(passwordSavedDto.getPassword());
        passwordKeeper.setUserName(passwordSavedDto.getUserName());

        passwordKeeper.setUser(user);

        PasswordKeeper savedPassword = keeperRepository.save(passwordKeeper);

        return "SUCCESS";
    }

    @Override
    public List<String> getAllSavedPassword(String userName) {

        User user = userRepository.findUserByEmail(userName);

        return keeperRepository.getAllSavedPassword(user.getId());

//        return null;
    }
}

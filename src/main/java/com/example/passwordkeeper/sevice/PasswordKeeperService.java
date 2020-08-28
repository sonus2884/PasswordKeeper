package com.example.passwordkeeper.sevice;

import com.example.passwordkeeper.dto.PasswordSavedDto;
import com.example.passwordkeeper.dto.PasswordSavedResponseDto;
import com.example.passwordkeeper.model.PasswordKeeper;
import com.example.passwordkeeper.model.User;

import java.util.List;

public interface PasswordKeeperService {

    String savePassword(PasswordSavedDto passwordSavedDto, String  userName);

    List<String> getAllSavedPassword(String userName);
}

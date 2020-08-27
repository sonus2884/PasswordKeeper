package com.example.passwordkeeper.sevice;

import com.example.passwordkeeper.dto.PasswordSavedDto;
import com.example.passwordkeeper.dto.PasswordSavedResponseDto;
import com.example.passwordkeeper.model.PasswordKeeper;

import java.util.List;

public interface PasswordKeeperService {

    String savePassword(PasswordSavedDto passwordSavedDto, String  userName);

    List<PasswordKeeper> getAllSavedPassword(String userName);
}

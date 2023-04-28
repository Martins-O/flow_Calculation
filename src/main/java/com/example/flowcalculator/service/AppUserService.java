package com.example.flowcalculator.service;

import com.example.flowcalculator.data.dto.CreateRequest;
import com.example.flowcalculator.data.dto.TokenResponseDto;
import com.example.flowcalculator.data.model.AppUser;

import java.util.List;

public interface AppUserService {

    TokenResponseDto creatUser(CreateRequest request);
    TokenResponseDto login(String username, String password);
    List<AppUser> getAllUsers();

    AppUser findByUsername(String username);
}

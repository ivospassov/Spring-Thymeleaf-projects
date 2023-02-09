package com.example.prep1.services;

import com.example.prep1.domain.dto.UserLoginDTO;
import com.example.prep1.domain.dto.UserRegistrationDTO;

public interface AuthService {

    boolean canRegister(UserRegistrationDTO userRegistrationDTO);

    boolean canLogin(UserLoginDTO userLoginDTO);
}

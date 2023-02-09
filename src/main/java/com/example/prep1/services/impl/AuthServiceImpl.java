package com.example.prep1.services.impl;

import com.example.prep1.domain.dto.UserLoginDTO;
import com.example.prep1.domain.dto.UserRegistrationDTO;
import com.example.prep1.domain.entities.User;
import com.example.prep1.repositories.UserRepository;
import com.example.prep1.services.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean canRegister(UserRegistrationDTO userRegistrationDTO) {
        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
            return false;
        }

        Optional<User> userOptional = this.userRepository.findByUsernameOrEmail(userRegistrationDTO.getUsername(), userRegistrationDTO.getEmail());
        if (userOptional.isPresent()) {
            return false;
        }

        return true;
    }

    @Override
    public boolean canLogin(UserLoginDTO userLoginDTO) {
        Optional<User> userOptional = this.userRepository.findByUsernameOrEmail(userLoginDTO.getUsername(), "");
        if (userOptional.isEmpty()) {
            return false;
        }

        User user = userOptional.get();
        if (!userLoginDTO.getPassword().equals(user.getPassword())) {
            return false;
        }

        return true;
    }
}

package com.example.prep1.services.impl;

import com.example.prep1.domain.dto.UserRegistrationDTO;
import com.example.prep1.domain.entities.User;
import com.example.prep1.repositories.UserRepository;
import com.example.prep1.services.RegisterUserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        User newUser = this.modelMapper.map(userRegistrationDTO, User.class);
        newUser.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        this.userRepository.save(newUser);
    }
}

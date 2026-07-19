package com.yusuf.cloudvault.user.service.impl;

import com.yusuf.cloudvault.common.exception.EmailAlreadyExistsException;
import com.yusuf.cloudvault.auth.dto.RegisterRequest;
import com.yusuf.cloudvault.auth.dto.RegisterResponse;
import com.yusuf.cloudvault.user.entity.Role;
import com.yusuf.cloudvault.user.entity.User;
import com.yusuf.cloudvault.user.repository.UserRepository;
import com.yusuf.cloudvault.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        User savedUser = userRepository.save(user);

        return RegisterResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .build();
    }
}
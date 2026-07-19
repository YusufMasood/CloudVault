package com.yusuf.cloudvault.auth.service.impl;

import com.yusuf.cloudvault.auth.dto.LoginRequest;
import com.yusuf.cloudvault.auth.dto.LoginResponse;
import com.yusuf.cloudvault.auth.service.AuthService;
import com.yusuf.cloudvault.security.JwtService;
import com.yusuf.cloudvault.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import com.yusuf.cloudvault.security.CustomUserDetails;
import com.yusuf.cloudvault.user.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.getUser();

        String token = jwtService.generateToken(userDetails);

        return LoginResponse.builder()
                .token(token)
                .type("Bearer")
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}

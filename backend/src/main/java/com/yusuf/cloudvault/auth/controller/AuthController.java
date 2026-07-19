package com.yusuf.cloudvault.auth.controller;

import com.yusuf.cloudvault.common.dto.ApiResponse;
import com.yusuf.cloudvault.auth.dto.RegisterRequest;
import com.yusuf.cloudvault.auth.dto.RegisterResponse;
import com.yusuf.cloudvault.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.yusuf.cloudvault.auth.dto.LoginRequest;
import com.yusuf.cloudvault.auth.dto.LoginResponse;
import com.yusuf.cloudvault.auth.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;


    // Register API
    @PostMapping("/register")
    public ApiResponse<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        RegisterResponse response = userService.register(request);

        return ApiResponse.<RegisterResponse>builder()
                .success(true)
                .message("User registered successfully")
                .data(response)
                .build();
    }


    //Login API
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        return ApiResponse.<LoginResponse>builder()
                .success(true)
                .message("Login successful")
                .data(response)
                .build();
    }


}
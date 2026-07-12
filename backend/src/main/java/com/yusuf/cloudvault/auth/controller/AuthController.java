package com.yusuf.cloudvault.auth.controller;

import com.yusuf.cloudvault.common.dto.ApiResponse;
import com.yusuf.cloudvault.user.dto.request.RegisterRequest;
import com.yusuf.cloudvault.user.dto.response.RegisterResponse;
import com.yusuf.cloudvault.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

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
}
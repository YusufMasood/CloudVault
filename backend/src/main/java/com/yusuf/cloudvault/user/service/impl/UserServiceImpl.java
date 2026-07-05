package com.yusuf.cloudvault.user.service.impl;

import com.yusuf.cloudvault.user.dto.request.RegisterRequest;
import com.yusuf.cloudvault.user.dto.response.RegisterResponse;
import com.yusuf.cloudvault.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public RegisterResponse register(RegisterRequest request) {

        return null;
    }
}
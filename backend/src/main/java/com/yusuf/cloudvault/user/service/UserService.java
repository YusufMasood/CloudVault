package com.yusuf.cloudvault.user.service;

import com.yusuf.cloudvault.user.dto.request.RegisterRequest;
import com.yusuf.cloudvault.user.dto.response.RegisterResponse;

public interface UserService {

    RegisterResponse register(RegisterRequest request);

}
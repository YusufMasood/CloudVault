package com.yusuf.cloudvault.user.service;

import com.yusuf.cloudvault.auth.dto.RegisterRequest;
import com.yusuf.cloudvault.auth.dto.RegisterResponse;

public interface UserService {

    RegisterResponse register(RegisterRequest request);

}
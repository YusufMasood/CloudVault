package com.yusuf.cloudvault.auth.service;

import com.yusuf.cloudvault.auth.dto.LoginRequest;
import com.yusuf.cloudvault.auth.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);
}

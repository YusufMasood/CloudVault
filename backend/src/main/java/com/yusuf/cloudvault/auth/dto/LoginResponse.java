package com.yusuf.cloudvault.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private String token;

    private String type;

    private Long userId;

    private String name;

    private String email;

    private String role;
}
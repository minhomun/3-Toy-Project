package com.team3.controller;

import com.team3.domain.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String id;
    private String password;
    private Role role;
}

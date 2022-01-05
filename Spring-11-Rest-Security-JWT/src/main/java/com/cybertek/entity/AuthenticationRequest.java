package com.cybertek.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
//This class is for my request body.
public class AuthenticationRequest {

    private String username;
    private String password;
}

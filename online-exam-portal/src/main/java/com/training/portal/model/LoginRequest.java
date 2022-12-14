package com.training.portal.model;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginRequest {

    @NotBlank(message = "Email is mandatory")
    private String emailId;

    @NotBlank(message = "password cannot be mandatory")
    private String password;
}

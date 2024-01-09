package com.gabriel.jwt_authentication_example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequestDTO(
    @NotBlank(message = "Email field cannot be null or blank")
        @Email(message = "The provided email is not valid")
        String email,
    @NotBlank(message = "Password field cannot be null or blank") String password) {}

package com.monefy.app.auth.dtos;

public record RegisterRequest(
        @jakarta.validation.constraints.NotBlank
        @jakarta.validation.constraints.Size(min = 4, max = 32)
        String username,

        @jakarta.validation.constraints.NotBlank
        @jakarta.validation.constraints.Size(min = 6, max = 128)
        String password) { }
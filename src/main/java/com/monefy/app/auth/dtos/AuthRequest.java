package com.monefy.app.auth.dtos;

public record AuthRequest(
        @jakarta.validation.constraints.NotBlank String username,
        @jakarta.validation.constraints.NotBlank String password) { }
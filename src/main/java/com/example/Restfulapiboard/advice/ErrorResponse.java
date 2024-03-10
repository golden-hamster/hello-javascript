package com.example.Restfulapiboard.advice;

import lombok.Getter;

@Getter
public record ErrorResponse(String message) {}

package com.weatherwebapp.error;

public record ErrorResponse(int status, String error, String message) {
}

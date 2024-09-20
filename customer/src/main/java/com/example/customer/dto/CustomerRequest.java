package com.example.customer.dto;

public record CustomerRequest(
        String name,
        Long contactNumber,
        String address,
        String email,
        Long userId
        ) {
}

package com.example.springtest.dto;



public record CustomerSummary(
        int customerId,
        String firstName,
        String lastName,
        String email,
        int active,
        String country
        ) {
}

package com.example.springtest.dto;

public record CustomerDetail(
	    int customerId,
	    String firstName,
	    String lastName,
	    String email,
	    int active,
	    String address,
	    String district,
	    String city,
	    String country,
	    String postalCode,
	    String phone,
	    int storeId
	) {}

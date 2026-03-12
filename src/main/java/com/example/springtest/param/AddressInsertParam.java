package com.example.springtest.param;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressInsertParam {
    private Integer addressId;
    private String address;
    private String district;
    private String cityId;
    private String postalCode;
    private String phone;
}
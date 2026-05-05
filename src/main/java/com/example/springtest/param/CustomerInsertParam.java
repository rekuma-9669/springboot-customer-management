package com.example.springtest.param;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerInsertParam {
    private Integer customerId;
    private String storeId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer addressId;
    private String active;
}

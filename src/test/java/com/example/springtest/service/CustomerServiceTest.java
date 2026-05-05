package com.example.springtest.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.springtest.mapper.CustomerMapper;
import com.example.springtest.param.AddressInsertParam;
import com.example.springtest.param.CustomerInsertParam;
import com.example.springtest.form.CustomerCreateForm;

class CustomerServiceTest {

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    public CustomerServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCustomer_shouldCreateCustomerSuccessfully() {

        CustomerCreateForm form = new CustomerCreateForm();
        form.setFirstName("Taro");
        form.setLastName("Yamada");
        form.setEmail("test@test.com");
        form.setActive("1");
        form.setAddress("Tokyo");
        form.setDistrict("Shibuya");
        form.setCityId("1");
        form.setPhone("09000000000");
        form.setStoreId("1");

        // Address登録Mock
        doAnswer(invocation -> {
            AddressInsertParam param = invocation.getArgument(0);
            param.setAddressId(1);
            return null;
        }).when(customerMapper).createAddress(any());

        // Customer登録Mock
        doAnswer(invocation -> {
            CustomerInsertParam param = invocation.getArgument(0);
            param.setCustomerId(1);
            return null;
        }).when(customerMapper).createCustomer(any());

        int result = customerService.createCustomer(form);

        assertEquals(1, result);
    }
}
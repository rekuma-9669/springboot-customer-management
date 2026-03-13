package com.example.springtest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.springtest.dto.CityOption;
import com.example.springtest.service.CustomerService;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerService customerService;

    @Test
    void showCreate_shouldReturnCustomerNewPage() throws Exception {
        when(customerService.findAllCities()).thenReturn(
                List.of(new CityOption(1, "Tokyo", "Japan"))
        );

        mockMvc.perform(get("/customers/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer-new"))
                .andExpect(model().attributeExists("form"))
                .andExpect(model().attributeExists("cities"));
    }

    @Test
    void confirmCreate_whenValidationError_shouldReturnCustomerNewPage() throws Exception {
        when(customerService.findAllCities()).thenReturn(
                List.of(new CityOption(1, "Tokyo", "Japan"))
        );

        mockMvc.perform(post("/customers/new/confirm")
                        .param("firstName", "")
                        .param("lastName", "")
                        .param("email", "invalid")
                        .param("active", "")
                        .param("address", "")
                        .param("district", "")
                        .param("cityId", "")
                        .param("postalCode", "")
                        .param("phone", "")
                        .param("storeId", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("customer-new"));
    }
}
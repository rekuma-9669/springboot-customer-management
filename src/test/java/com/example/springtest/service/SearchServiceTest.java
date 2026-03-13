package com.example.springtest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.springtest.dto.CustomerDetail;
import com.example.springtest.dto.CustomerSummary;
import com.example.springtest.form.CustomerEditForm;
import com.example.springtest.mapper.CustomerMapper;

class SearchServiceTest {

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchCustomers_shouldReturnCustomerList() throws Exception {
        List<CustomerSummary> expected = List.of(
                new CustomerSummary(1, "Taro", "Yamada", "test@test.com", 1, "Japan")
        );

        when(customerMapper.searchCustomers("Yamada", null, null, null, null, 0, 0)).thenReturn(expected);

        List<CustomerSummary> result =
                searchService.searchCustomers("Yamada", null, null, null, null, 0, 0);

        assertEquals(1, result.size());
        assertEquals("Yamada", result.get(0).lastName());
    }

    @Test
    void findCustomerDetailById_shouldReturnDetail() {
        CustomerDetail expected = new CustomerDetail(
                1, "Taro", "Yamada", "test@test.com", 1,
                "Tokyo", "Shibuya", "Tokyo", "Japan", "1500001", "09000000000", 1
        );

        when(customerMapper.findCustomerDetailById(1)).thenReturn(expected);

        CustomerDetail result = searchService.findCustomerDetailById(1);

        assertEquals(1, result.customerId());
        assertEquals("Taro", result.firstName());
    }

    @Test
    void updateCustomer_shouldCallMapper() {
        CustomerEditForm form = new CustomerEditForm(1, "Taro", "Yamada", "test@test.com", 1, 1);

        searchService.updateCustomer(form);

        verify(customerMapper).updateCustomer(form);
    }

    @Test
    void deactivateCustomer_shouldCallMapper() {
        searchService.deactivateCustomer(1);

        verify(customerMapper).deactivateCustomer(1);
    }
}
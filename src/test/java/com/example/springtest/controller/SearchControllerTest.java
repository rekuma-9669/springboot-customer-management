package com.example.springtest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.springtest.dto.CustomerDetail;
import com.example.springtest.dto.CustomerSummary;
import com.example.springtest.service.SearchService;

@WebMvcTest(SearchController.class)
class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SearchService searchService;

    @Test
    void search_shouldReturnSearchPage() throws Exception {
        when(searchService.searchCustomers("Yamada", null, null, null, null, 0, 0))
                .thenReturn(List.of(
                        new CustomerSummary(1, "Taro", "Yamada", "test@test.com", 1, "Japan")
                ));

        mockMvc.perform(get("/search").param("lastName", "Yamada"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"))
                .andExpect(model().attributeExists("customers"));
    }

    @Test
    void showDetail_shouldReturnDetailPage() throws Exception {
        when(searchService.findCustomerDetailById(1)).thenReturn(
                new CustomerDetail(
                        1, "Taro", "Yamada", "test@test.com", 1,
                        "Tokyo", "Shibuya", "Tokyo", "Japan", "1500001", "09000000000", 1
                )
        );

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("detail"))
                .andExpect(model().attributeExists("customer"));
    }
}
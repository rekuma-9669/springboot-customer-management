package com.example.springtest.form;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class CustomerCreateFormValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validForm_shouldHaveNoViolations() {
        CustomerCreateForm form = new CustomerCreateForm();
        form.setFirstName("Taro");
        form.setLastName("Yamada");
        form.setEmail("test@test.com");
        form.setActive("1");
        form.setAddress("Tokyo");
        form.setDistrict("Shibuya");
        form.setCityId("1");
        form.setPostalCode("1500001");
        form.setPhone("09000000000");
        form.setStoreId("1");

        Set<ConstraintViolation<CustomerCreateForm>> violations = validator.validate(form);

        assertTrue(violations.isEmpty());
    }

    @Test
    void invalidForm_shouldHaveViolations() {
        CustomerCreateForm form = new CustomerCreateForm();
        form.setFirstName("");
        form.setLastName("");
        form.setEmail("abc");
        form.setActive("");
        form.setAddress("");
        form.setDistrict("");
        form.setCityId("");
        form.setPhone("");
        form.setStoreId("");

        Set<ConstraintViolation<CustomerCreateForm>> violations = validator.validate(form);

        assertFalse(violations.isEmpty());
    }
}
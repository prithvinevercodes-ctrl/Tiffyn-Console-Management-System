package com.tiffyn.service;

import com.tiffyn.exception.CustomerAlreadyExistsException;
import com.tiffyn.exception.CustomerNotFoundException;
import com.tiffyn.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService();
    }

    @Test
    void shouldAddCustomerSuccessfully() {

        Customer customer = new Customer(
                "C001",
                "Rahul",
                "9876543210",
                "rahul@example.com",
                "Greater Noida"
        );

        customerService.addCustomer(customer);

        Customer result =
                customerService.getCustomerById("C001");

        assertEquals("Rahul", result.getName());
        assertEquals("C001", result.getCustomerId());
    }

    @Test
    void shouldRejectDuplicateCustomer() {

        Customer customer = new Customer(
                "C001",
                "Rahul",
                "9876543210",
                "rahul@example.com",
                "Greater Noida"
        );

        customerService.addCustomer(customer);

        assertThrows(
                CustomerAlreadyExistsException.class,
                () -> customerService.addCustomer(customer)
        );
    }

    @Test
    void shouldThrowExceptionWhenCustomerDoesNotExist() {

        assertThrows(
                CustomerNotFoundException.class,
                () -> customerService.getCustomerById("C999")
        );
    }

    @Test
    void shouldUpdateCustomer() {

        Customer customer = new Customer(
                "C001",
                "Rahul",
                "9876543210",
                "rahul@example.com",
                "Greater Noida"
        );

        customerService.addCustomer(customer);

        customer.setName("Rahul Sharma");

        customerService.updateCustomer(customer);

        assertEquals(
                "Rahul Sharma",
                customerService
                        .getCustomerById("C001")
                        .getName()
        );
    }

    @Test
    void shouldRemoveCustomer() {

        Customer customer = new Customer(
                "C001",
                "Rahul",
                "9876543210",
                "rahul@example.com",
                "Greater Noida"
        );

        customerService.addCustomer(customer);

        customerService.removeCustomer("C001");

        assertThrows(
                CustomerNotFoundException.class,
                () -> customerService.getCustomerById("C001")
        );
    }
}
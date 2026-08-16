package com.tiffyn;

import com.tiffyn.exception.CustomerAlreadyExistsException;
import com.tiffyn.exception.CustomerNotFoundException;
import com.tiffyn.model.Customer;
import com.tiffyn.service.CustomerService;

public class Main {

    public static void main(String[] args) {

        CustomerService customerService =
                new CustomerService();

        Customer customer = new Customer(
                "C001",
                "Rahul",
                "9876543210",
                "rahul@example.com",
                "Greater Noida"
        );

        // Add customer
        customerService.addCustomer(customer);

        System.out.println("Customer added successfully.");

        // Try duplicate customer
        try {

            customerService.addCustomer(customer);

        } catch (CustomerAlreadyExistsException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }

        // Try finding non-existing customer
        try {

            customerService.getCustomerById("C999");

        } catch (CustomerNotFoundException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }

        // Get existing customer
        Customer foundCustomer =
                customerService.getCustomerById("C001");

        System.out.println(
                "\nFound: " + foundCustomer
        );
    }
}
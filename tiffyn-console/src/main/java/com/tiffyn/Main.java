package com.tiffyn;

import com.tiffyn.model.Customer;
import com.tiffyn.service.CustomerService;

public class Main {

    public static void main(String[] args) {

        CustomerService customerService = new CustomerService();

        Customer customer1 = new Customer(
                "C001",
                "Rahul",
                "9876543210",
                "rahul@example.com",
                "Greater Noida"
        );

        Customer customer2 = new Customer(
                "C002",
                "Aman",
                "9876543211",
                "aman@example.com",
                "Delhi"
        );

        // Add customers
        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);

        // Get all customers
        System.out.println("All Customers:");
        customerService.getAllCustomers()
                .forEach(System.out::println);

        // Find customer
        System.out.println("\nSearching for C001:");
        System.out.println(
                customerService.findCustomerById("C001")
        );

        // Update customer
        customer1.setName("Rahul Sharma");
        customerService.updateCustomer(customer1);

        System.out.println("\nAfter Update:");
        System.out.println(
                customerService.findCustomerById("C001")
        );

        // Remove customer
        customerService.removeCustomer("C002");

        System.out.println("\nAfter Removing C002:");
        customerService.getAllCustomers()
                .forEach(System.out::println);
    }
}
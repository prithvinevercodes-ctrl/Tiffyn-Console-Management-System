package com.tiffyn.service;

import com.tiffyn.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private final List<Customer> customers;

    public CustomerService() {
        customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer findCustomerById(String customerId) {

        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }

        return null;
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    public void updateCustomer(Customer customer) {

        Customer existingCustomer =
                findCustomerById(customer.getCustomerId());

        if (existingCustomer != null) {
            existingCustomer.setName(customer.getName());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setAddress(customer.getAddress());
        }
    }

    public void removeCustomer(String customerId) {

        customers.removeIf(
                customer -> customer.getCustomerId().equals(customerId)
        );
    }
}
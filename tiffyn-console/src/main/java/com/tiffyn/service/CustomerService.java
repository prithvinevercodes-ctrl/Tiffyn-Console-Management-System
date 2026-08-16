package com.tiffyn.service;

import com.tiffyn.exception.CustomerAlreadyExistsException;
import com.tiffyn.exception.CustomerNotFoundException;
import com.tiffyn.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private final List<Customer> customers;

    public CustomerService() {
        customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {

        if (findCustomerById(customer.getCustomerId()) != null) {
            throw new CustomerAlreadyExistsException(
                    "Customer with ID "
                            + customer.getCustomerId()
                            + " already exists."
            );
        }

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

    public Customer getCustomerById(String customerId) {

        Customer customer = findCustomerById(customerId);

        if (customer == null) {
            throw new CustomerNotFoundException(
                    "Customer with ID "
                            + customerId
                            + " not found."
            );
        }

        return customer;
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    public void updateCustomer(Customer customer) {

        Customer existingCustomer =
                getCustomerById(customer.getCustomerId());

        existingCustomer.setName(customer.getName());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setAddress(customer.getAddress());
    }

    public void removeCustomer(String customerId) {

        Customer customer = getCustomerById(customerId);

        customers.remove(customer);
    }
}
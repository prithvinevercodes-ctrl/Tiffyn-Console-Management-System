package com.tiffyn;

import com.tiffyn.model.Customer;

public class Main {

    public static void main(String[] args) {

        Customer customer = new Customer(
                "C001",
                "Somya",
                "8810554256",
                "Somya@example.com",
                "Greater Noida"
        );

        System.out.println(customer);

        customer.setName("Somya Rajput");

        System.out.println(customer.getName());
    }
}
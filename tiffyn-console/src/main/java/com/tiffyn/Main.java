package com.tiffyn;

import com.tiffyn.model.Customer;
import com.tiffyn.model.Vendor;
import jdk.swing.interop.SwingInterOpUtils;

public class Main {

    public static void main(String[] args) {

        Customer customer = new Customer(
                "C001",
                "Somya",
                "8810554256",
                "Somya@example.com",
                "Greater Noida"
        );

        Vendor vendor = new Vendor(
                "V001",
                "Prathvi",
                "997989142",
                "prathvi@example.com",
                "Maa's Kitchen",
                "Greater Noida"
        );


        System.out.println(customer);
        System.out.println(vendor);

        customer.setName("Somya Rajput");
        vendor.setBusinessName("Maa's kitchen");

        System.out.println();

        System.out.println("Updated Customer: "+ customer.getName());
        System.out.println("Updated Vendor: "+ vendor.getBusinessName());
    }
}
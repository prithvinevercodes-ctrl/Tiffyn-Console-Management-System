package com.tiffyn;

import com.tiffyn.model.*;
import jdk.swing.interop.SwingInterOpUtils;

import java.time.LocalDate;

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

        MealPlan mealPlan = new MealPlan(
                "MP001",
                "Monthly Veg Plan",
                "Lunch + Dinner",
                3000.00,
                30,
                "V001"
        );

        Subscription subscription = new Subscription(
                "S001",
                "C001",
                "MP001",
                LocalDate.of(2026, 9, 1),
                LocalDate.of(2026, 9, 30),
                SubscriptionStatus.ACTIVE
        );

        System.out.println(customer);
        System.out.println(vendor);

        customer.setName("Somya Rajput");
        vendor.setBusinessName("Maa's kitchen");

        System.out.println();

        System.out.println("Updated Customer: "+ customer.getName());
        System.out.println("Updated Vendor: "+ vendor.getBusinessName());

        System.out.println(mealPlan);

        mealPlan.setPrice(3200.00);

        System.out.println("Updated Price: ₹" + mealPlan.getPrice());

        System.out.println(subscription);

        subscription.setStatus(SubscriptionStatus.CANCELLED);

        System.out.println("Updated Status: "
                + subscription.getStatus());
    }
}
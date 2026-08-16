package com.tiffyn;

import com.tiffyn.model.Subscription;
import com.tiffyn.model.SubscriptionStatus;
import com.tiffyn.service.SubscriptionService;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        SubscriptionService subscriptionService =
                new SubscriptionService();

        Subscription subscription1 = new Subscription(
                "S001",
                "C001",
                "MP001",
                LocalDate.of(2026, 9, 1),
                LocalDate.of(2026, 9, 30),
                SubscriptionStatus.ACTIVE
        );

        Subscription subscription2 = new Subscription(
                "S002",
                "C001",
                "MP002",
                LocalDate.of(2026, 9, 1),
                LocalDate.of(2026, 9, 30),
                SubscriptionStatus.ACTIVE
        );

        // Add first subscription
        subscriptionService.addSubscription(subscription1);

        System.out.println("All Subscriptions:");
        subscriptionService.getAllSubscriptions()
                .forEach(System.out::println);

        // Try second active subscription
        System.out.println("\nTrying second subscription:");

        subscriptionService.addSubscription(subscription2);

        // Check customer subscriptions
        System.out.println("\nSubscriptions of C001:");

        subscriptionService
                .getSubscriptionsByCustomerId("C001")
                .forEach(System.out::println);

        // Cancel first subscription
        System.out.println("\nCancelling S001...");

        subscriptionService.cancelSubscription("S001");

        System.out.println(
                subscriptionService.findSubscriptionById("S001")
        );

        // Now try adding S002 again
        System.out.println(
                "\nTrying S002 again after cancellation:"
        );

        subscriptionService.addSubscription(subscription2);

        subscriptionService.getAllSubscriptions()
                .forEach(System.out::println);
    }
}
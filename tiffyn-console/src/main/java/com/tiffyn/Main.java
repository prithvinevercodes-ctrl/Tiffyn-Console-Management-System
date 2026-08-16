package com.tiffyn;

import com.tiffyn.model.Order;
import com.tiffyn.model.OrderStatus;
import com.tiffyn.service.OrderService;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        OrderService orderService = new OrderService();

        Order order1 = new Order(
                "O001",
                "C001",
                "MP001",
                LocalDate.now(),
                OrderStatus.PLACED
        );

        Order order2 = new Order(
                "O002",
                "C001",
                "MP002",
                LocalDate.now(),
                OrderStatus.PREPARING
        );

        Order order3 = new Order(
                "O003",
                "C002",
                "MP001",
                LocalDate.now(),
                OrderStatus.DELIVERED
        );

        // Add orders
        orderService.addOrder(order1);
        orderService.addOrder(order2);
        orderService.addOrder(order3);

        // Get all orders
        System.out.println("All Orders:");

        orderService.getAllOrders()
                .forEach(System.out::println);

        // Find order
        System.out.println("\nSearching for O001:");

        System.out.println(
                orderService.findOrderById("O001")
        );

        // Get orders by customer
        System.out.println("\nOrders of C001:");

        orderService.getOrdersByCustomerId("C001")
                .forEach(System.out::println);

        // Get orders by meal plan
        System.out.println("\nOrders for MP001:");

        orderService.getOrdersByMealPlanId("MP001")
                .forEach(System.out::println);

        // Update status
        System.out.println("\nUpdating O001 status...");

        orderService.updateOrderStatus(
                "O001",
                OrderStatus.OUT_FOR_DELIVERY
        );

        System.out.println(
                orderService.findOrderById("O001")
        );

        // Remove order
        System.out.println("\nRemoving O003...");

        orderService.removeOrder("O003");

        System.out.println("\nRemaining Orders:");

        orderService.getAllOrders()
                .forEach(System.out::println);
    }
}
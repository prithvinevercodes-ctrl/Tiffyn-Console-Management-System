package com.tiffyn.service;

import com.tiffyn.model.Order;
import com.tiffyn.model.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final List<Order> orders;

    public OrderService() {
        orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Order findOrderById(String orderId) {

        for (Order order : orders) {

            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }

        return null;
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public List<Order> getOrdersByCustomerId(String customerId) {

        List<Order> customerOrders = new ArrayList<>();

        for (Order order : orders) {

            if (order.getCustomerId().equals(customerId)) {
                customerOrders.add(order);
            }
        }

        return customerOrders;
    }

    public List<Order> getOrdersByMealPlanId(String mealPlanId) {

        List<Order> mealPlanOrders = new ArrayList<>();

        for (Order order : orders) {

            if (order.getMealPlanId().equals(mealPlanId)) {
                mealPlanOrders.add(order);
            }
        }

        return mealPlanOrders;
    }

    public void updateOrderStatus(
            String orderId,
            OrderStatus newStatus) {

        Order existingOrder = findOrderById(orderId);

        if (existingOrder != null) {
            existingOrder.setStatus(newStatus);
        }
    }

    public void removeOrder(String orderId) {

        orders.removeIf(
                order -> order.getOrderId().equals(orderId)
        );
    }
}
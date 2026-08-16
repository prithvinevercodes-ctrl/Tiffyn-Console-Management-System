package com.tiffyn.service;

import com.tiffyn.exception.OrderException;
import com.tiffyn.model.Order;
import com.tiffyn.model.OrderStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final List<Order> orders;

    public OrderService() {
        orders = new ArrayList<>();
    }

    public void addOrder(Order order) {

        validateOrder(order);

        if (findOrderById(order.getOrderId()) != null) {
            throw new OrderException(
                    "Order with ID "
                            + order.getOrderId()
                            + " already exists."
            );
        }

        orders.add(order);
    }

    private void validateOrder(Order order) {

        if (order == null) {
            throw new OrderException(
                    "Order cannot be null."
            );
        }

        if (order.getCustomerId() == null
                || order.getCustomerId().isBlank()) {

            throw new OrderException(
                    "Customer ID cannot be empty."
            );
        }

        if (order.getMealPlanId() == null
                || order.getMealPlanId().isBlank()) {

            throw new OrderException(
                    "Meal Plan ID cannot be empty."
            );
        }

        if (order.getOrderDate() == null) {
            throw new OrderException(
                    "Order date cannot be null."
            );
        }

        if (order.getOrderDate()
                .isAfter(LocalDate.now())) {

            throw new OrderException(
                    "Order date cannot be in the future."
            );
        }

        if (order.getStatus() == null) {
            throw new OrderException(
                    "Order status cannot be null."
            );
        }
    }

    public Order findOrderById(String orderId) {

        for (Order order : orders) {

            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }

        return null;
    }

    public Order getOrderById(String orderId) {

        Order order = findOrderById(orderId);

        if (order == null) {
            throw new OrderException(
                    "Order with ID "
                            + orderId
                            + " not found."
            );
        }

        return order;
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

        if (newStatus == null) {
            throw new OrderException(
                    "Order status cannot be null."
            );
        }

        Order existingOrder = getOrderById(orderId);

        existingOrder.setStatus(newStatus);
    }

    public void removeOrder(String orderId) {

        Order order = getOrderById(orderId);

        orders.remove(order);
    }
}
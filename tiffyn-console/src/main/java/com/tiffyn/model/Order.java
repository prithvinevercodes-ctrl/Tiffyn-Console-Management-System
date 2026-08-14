package com.tiffyn.model;

import java.time.LocalDate;

public class Order {

    private String orderId;
    private String customerId;
    private String mealPlanId;
    private LocalDate orderDate;
    private OrderStatus status;

    public Order(String orderId,
                 String customerId,
                 String mealPlanId,
                 LocalDate orderDate,
                 OrderStatus status) {

        this.orderId = orderId;
        this.customerId = customerId;
        this.mealPlanId = mealPlanId;
        this.orderDate = orderDate;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getMealPlanId() {
        return mealPlanId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", mealPlanId='" + mealPlanId + '\'' +
                ", orderDate=" + orderDate +
                ", status=" + status +
                '}';
    }
}
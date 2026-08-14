package com.tiffyn.model;

import java.time.LocalDate;

public class Subscription {

    private String subscriptionId;
    private String customerId;
    private String mealPlanId;
    private LocalDate startDate;
    private LocalDate endDate;
    private SubscriptionStatus status;

    public Subscription(String subscriptionId,
                        String customerId,
                        String mealPlanId,
                        LocalDate startDate,
                        LocalDate endDate,
                        SubscriptionStatus status) {

        this.subscriptionId = subscriptionId;
        this.customerId = customerId;
        this.mealPlanId = mealPlanId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getMealPlanId() {
        return mealPlanId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "subscriptionId='" + subscriptionId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", mealPlanId='" + mealPlanId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                '}';
    }
}
package com.tiffyn.service;

import com.tiffyn.exception.SubscriptionException;
import com.tiffyn.model.Subscription;
import com.tiffyn.model.SubscriptionStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionService {

    private final List<Subscription> subscriptions;

    public SubscriptionService() {
        subscriptions = new ArrayList<>();
    }

    public void addSubscription(Subscription subscription) {

        validateSubscription(subscription);

        if (findSubscriptionById(subscription.getSubscriptionId()) != null) {
            throw new SubscriptionException(
                    "Subscription with ID "
                            + subscription.getSubscriptionId()
                            + " already exists."
            );
        }

        if (hasActiveSubscription(subscription.getCustomerId())) {
            throw new SubscriptionException(
                    "Customer "
                            + subscription.getCustomerId()
                            + " already has an active subscription."
            );
        }

        subscriptions.add(subscription);
    }

    private void validateSubscription(Subscription subscription) {

        if (subscription == null) {
            throw new SubscriptionException(
                    "Subscription cannot be null."
            );
        }

        if (subscription.getCustomerId() == null
                || subscription.getCustomerId().isBlank()) {

            throw new SubscriptionException(
                    "Customer ID cannot be empty."
            );
        }

        if (subscription.getMealPlanId() == null
                || subscription.getMealPlanId().isBlank()) {

            throw new SubscriptionException(
                    "Meal Plan ID cannot be empty."
            );
        }

        if (subscription.getStartDate() == null
                || subscription.getEndDate() == null) {

            throw new SubscriptionException(
                    "Subscription dates cannot be null."
            );
        }

        if (subscription.getEndDate()
                .isBefore(subscription.getStartDate())) {

            throw new SubscriptionException(
                    "End date cannot be before start date."
            );
        }

        if (subscription.getStatus() == null) {

            throw new SubscriptionException(
                    "Subscription status cannot be null."
            );
        }
    }

    public Subscription findSubscriptionById(String subscriptionId) {

        for (Subscription subscription : subscriptions) {

            if (subscription.getSubscriptionId()
                    .equals(subscriptionId)) {

                return subscription;
            }
        }

        return null;
    }

    public Subscription getSubscriptionById(String subscriptionId) {

        Subscription subscription =
                findSubscriptionById(subscriptionId);

        if (subscription == null) {
            throw new SubscriptionException(
                    "Subscription with ID "
                            + subscriptionId
                            + " not found."
            );
        }

        return subscription;
    }

    public List<Subscription> getAllSubscriptions() {
        return new ArrayList<>(subscriptions);
    }

    public List<Subscription> getSubscriptionsByCustomerId(
            String customerId) {

        List<Subscription> customerSubscriptions =
                new ArrayList<>();

        for (Subscription subscription : subscriptions) {

            if (subscription.getCustomerId()
                    .equals(customerId)) {

                customerSubscriptions.add(subscription);
            }
        }

        return customerSubscriptions;
    }

    public List<Subscription> getSubscriptionsByMealPlanId(
            String mealPlanId) {

        List<Subscription> mealPlanSubscriptions =
                new ArrayList<>();

        for (Subscription subscription : subscriptions) {

            if (subscription.getMealPlanId()
                    .equals(mealPlanId)) {

                mealPlanSubscriptions.add(subscription);
            }
        }

        return mealPlanSubscriptions;
    }

    public boolean hasActiveSubscription(String customerId) {

        for (Subscription subscription : subscriptions) {

            if (subscription.getCustomerId()
                    .equals(customerId)
                    && subscription.getStatus()
                    == SubscriptionStatus.ACTIVE) {

                return true;
            }
        }

        return false;
    }

    public void cancelSubscription(String subscriptionId) {

        Subscription subscription =
                getSubscriptionById(subscriptionId);

        if (subscription.getStatus()
                != SubscriptionStatus.ACTIVE) {

            throw new SubscriptionException(
                    "Subscription "
                            + subscriptionId
                            + " is not active."
            );
        }

        subscription.setStatus(
                SubscriptionStatus.CANCELLED
        );
    }
}
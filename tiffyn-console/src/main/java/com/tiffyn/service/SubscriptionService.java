
package com.tiffyn.service;

import com.tiffyn.model.Subscription;
import com.tiffyn.model.SubscriptionStatus;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionService {

    private final List<Subscription> subscriptions;

    public SubscriptionService() {
        subscriptions = new ArrayList<>();
    }

    public void addSubscription(Subscription subscription) {

        if (hasActiveSubscription(subscription.getCustomerId())) {
            System.out.println(
                    "Customer already has an active subscription."
            );
            return;
        }

        subscriptions.add(subscription);
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
                findSubscriptionById(subscriptionId);

        if (subscription != null
                && subscription.getStatus()
                == SubscriptionStatus.ACTIVE) {

            subscription.setStatus(
                    SubscriptionStatus.CANCELLED
            );
        }
    }
}
package com.tiffyn.service;

import com.tiffyn.exception.SubscriptionException;
import com.tiffyn.model.Subscription;
import com.tiffyn.model.SubscriptionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionServiceTest {

    private SubscriptionService subscriptionService;

    @BeforeEach
    void setUp() {
        subscriptionService = new SubscriptionService();
    }

    @Test
    void shouldAddSubscriptionSuccessfully() {

        Subscription subscription = new Subscription(
                "S001",
                "C001",
                "MP001",
                LocalDate.of(2026, 9, 1),
                LocalDate.of(2026, 9, 30),
                SubscriptionStatus.ACTIVE
        );

        subscriptionService.addSubscription(subscription);

        Subscription result =
                subscriptionService.getSubscriptionById("S001");

        assertEquals("S001", result.getSubscriptionId());
        assertEquals("C001", result.getCustomerId());
        assertEquals("MP001", result.getMealPlanId());
        assertEquals(
                SubscriptionStatus.ACTIVE,
                result.getStatus()
        );
    }

    @Test
    void shouldRejectDuplicateSubscriptionId() {

        Subscription subscription = new Subscription(
                "S001",
                "C001",
                "MP001",
                LocalDate.of(2026, 9, 1),
                LocalDate.of(2026, 9, 30),
                SubscriptionStatus.ACTIVE
        );

        subscriptionService.addSubscription(subscription);

        Subscription duplicate = new Subscription(
                "S001",
                "C002",
                "MP002",
                LocalDate.of(2026, 10, 1),
                LocalDate.of(2026, 10, 30),
                SubscriptionStatus.ACTIVE
        );

        assertThrows(
                SubscriptionException.class,
                () -> subscriptionService.addSubscription(duplicate)
        );
    }

    @Test
    void shouldRejectSecondActiveSubscriptionForSameCustomer() {

        Subscription first = new Subscription(
                "S001",
                "C001",
                "MP001",
                LocalDate.of(2026, 9, 1),
                LocalDate.of(2026, 9, 30),
                SubscriptionStatus.ACTIVE
        );

        Subscription second = new Subscription(
                "S002",
                "C001",
                "MP002",
                LocalDate.of(2026, 10, 1),
                LocalDate.of(2026, 10, 30),
                SubscriptionStatus.ACTIVE
        );

        subscriptionService.addSubscription(first);

        assertThrows(
                SubscriptionException.class,
                () -> subscriptionService.addSubscription(second)
        );
    }

    @Test
    void shouldRejectInvalidSubscriptionDates() {

        Subscription subscription = new Subscription(
                "S001",
                "C001",
                "MP001",
                LocalDate.of(2026, 9, 30),
                LocalDate.of(2026, 9, 1),
                SubscriptionStatus.ACTIVE
        );

        assertThrows(
                SubscriptionException.class,
                () -> subscriptionService.addSubscription(subscription)
        );
    }

    @Test
    void shouldRejectMissingCustomerId() {

        Subscription subscription = new Subscription(
                "S001",
                "",
                "MP001",
                LocalDate.of(2026, 9, 1),
                LocalDate.of(2026, 9, 30),
                SubscriptionStatus.ACTIVE
        );

        assertThrows(
                SubscriptionException.class,
                () -> subscriptionService.addSubscription(subscription)
        );
    }

    @Test
    void shouldCancelActiveSubscription() {

        Subscription subscription = new Subscription(
                "S001",
                "C001",
                "MP001",
                LocalDate.of(2026, 9, 1),
                LocalDate.of(2026, 9, 30),
                SubscriptionStatus.ACTIVE
        );

        subscriptionService.addSubscription(subscription);

        subscriptionService.cancelSubscription("S001");

        assertEquals(
                SubscriptionStatus.CANCELLED,
                subscriptionService
                        .getSubscriptionById("S001")
                        .getStatus()
        );
    }

    @Test
    void shouldRejectCancellingAlreadyCancelledSubscription() {

        Subscription subscription = new Subscription(
                "S001",
                "C001",
                "MP001",
                LocalDate.of(2026, 9, 1),
                LocalDate.of(2026, 9, 30),
                SubscriptionStatus.ACTIVE
        );

        subscriptionService.addSubscription(subscription);

        subscriptionService.cancelSubscription("S001");

        assertThrows(
                SubscriptionException.class,
                () -> subscriptionService.cancelSubscription("S001")
        );
    }

    @Test
    void shouldAllowNewSubscriptionAfterCancellation() {

        Subscription first = new Subscription(
                "S001",
                "C001",
                "MP001",
                LocalDate.of(2026, 9, 1),
                LocalDate.of(2026, 9, 30),
                SubscriptionStatus.ACTIVE
        );

        Subscription second = new Subscription(
                "S002",
                "C001",
                "MP002",
                LocalDate.of(2026, 10, 1),
                LocalDate.of(2026, 10, 30),
                SubscriptionStatus.ACTIVE
        );

        subscriptionService.addSubscription(first);

        subscriptionService.cancelSubscription("S001");

        assertDoesNotThrow(
                () -> subscriptionService.addSubscription(second)
        );

        assertEquals(
                SubscriptionStatus.ACTIVE,
                subscriptionService
                        .getSubscriptionById("S002")
                        .getStatus()
        );
    }
}

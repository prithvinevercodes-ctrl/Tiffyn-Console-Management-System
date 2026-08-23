package com.tiffyn.service;

import com.tiffyn.exception.OrderException;
import com.tiffyn.model.Order;
import com.tiffyn.model.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
    }

    @Test
    void shouldAddOrderSuccessfully() {

        Order order = new Order(
                "O001",
                "C001",
                "MP001",
                LocalDate.now(),
                OrderStatus.PLACED
        );

        orderService.addOrder(order);

        Order result =
                orderService.getOrderById("O001");

        assertEquals("O001", result.getOrderId());
        assertEquals("C001", result.getCustomerId());
        assertEquals("MP001", result.getMealPlanId());
        assertEquals(
                OrderStatus.PLACED,
                result.getStatus()
        );
    }

    @Test
    void shouldRejectDuplicateOrderId() {

        Order order = new Order(
                "O001",
                "C001",
                "MP001",
                LocalDate.now(),
                OrderStatus.PLACED
        );

        orderService.addOrder(order);

        Order duplicate = new Order(
                "O001",
                "C002",
                "MP002",
                LocalDate.now(),
                OrderStatus.PLACED
        );

        assertThrows(
                OrderException.class,
                () -> orderService.addOrder(duplicate)
        );
    }

    @Test
    void shouldRejectFutureOrderDate() {

        Order order = new Order(
                "O001",
                "C001",
                "MP001",
                LocalDate.now().plusDays(5),
                OrderStatus.PLACED
        );

        assertThrows(
                OrderException.class,
                () -> orderService.addOrder(order)
        );
    }

    @Test
    void shouldRejectMissingCustomerId() {

        Order order = new Order(
                "O001",
                "",
                "MP001",
                LocalDate.now(),
                OrderStatus.PLACED
        );

        assertThrows(
                OrderException.class,
                () -> orderService.addOrder(order)
        );
    }

    @Test
    void shouldRejectMissingMealPlanId() {

        Order order = new Order(
                "O001",
                "C001",
                "",
                LocalDate.now(),
                OrderStatus.PLACED
        );

        assertThrows(
                OrderException.class,
                () -> orderService.addOrder(order)
        );
    }

    @Test
    void shouldRejectNullOrderDate() {

        Order order = new Order(
                "O001",
                "C001",
                "MP001",
                null,
                OrderStatus.PLACED
        );

        assertThrows(
                OrderException.class,
                () -> orderService.addOrder(order)
        );
    }

    @Test
    void shouldRejectNullOrderStatus() {

        Order order = new Order(
                "O001",
                "C001",
                "MP001",
                LocalDate.now(),
                null
        );

        assertThrows(
                OrderException.class,
                () -> orderService.addOrder(order)
        );
    }

    @Test
    void shouldUpdateOrderStatus() {

        Order order = new Order(
                "O001",
                "C001",
                "MP001",
                LocalDate.now(),
                OrderStatus.PLACED
        );

        orderService.addOrder(order);

        orderService.updateOrderStatus(
                "O001",
                OrderStatus.PREPARING
        );

        assertEquals(
                OrderStatus.PREPARING,
                orderService
                        .getOrderById("O001")
                        .getStatus()
        );
    }

    @Test
    void shouldRemoveOrder() {

        Order order = new Order(
                "O001",
                "C001",
                "MP001",
                LocalDate.now(),
                OrderStatus.PLACED
        );

        orderService.addOrder(order);

        orderService.removeOrder("O001");

        assertThrows(
                OrderException.class,
                () -> orderService.getOrderById("O001")
        );
    }
}
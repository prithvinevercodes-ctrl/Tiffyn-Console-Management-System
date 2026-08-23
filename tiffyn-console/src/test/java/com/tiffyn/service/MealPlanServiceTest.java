package com.tiffyn.service;

import com.tiffyn.exception.InvalidMealPlanException;
import com.tiffyn.exception.MealPlanAlreadyExistsException;
import com.tiffyn.exception.MealPlanNotFoundException;
import com.tiffyn.model.MealPlan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MealPlanServiceTest {

    private MealPlanService mealPlanService;

    @BeforeEach
    void setUp() {
        mealPlanService = new MealPlanService();
    }

    @Test
    void shouldAddMealPlanSuccessfully() {

        MealPlan mealPlan = new MealPlan(
                "MP001",
                "Monthly Veg Plan",
                "Lunch + Dinner",
                3000,
                30,
                "V001"
        );

        mealPlanService.addMealPlan(mealPlan);

        assertEquals(
                "Monthly Veg Plan",
                mealPlanService
                        .getMealPlanById("MP001")
                        .getName()
        );
    }

    @Test
    void shouldRejectDuplicateMealPlan() {

        MealPlan mealPlan = new MealPlan(
                "MP001",
                "Monthly Veg Plan",
                "Lunch + Dinner",
                3000,
                30,
                "V001"
        );

        mealPlanService.addMealPlan(mealPlan);

        assertThrows(
                MealPlanAlreadyExistsException.class,
                () -> mealPlanService.addMealPlan(mealPlan)
        );
    }

    @Test
    void shouldRejectInvalidPrice() {

        MealPlan mealPlan = new MealPlan(
                "MP001",
                "Invalid Plan",
                "Test",
                -500,
                30,
                "V001"
        );

        assertThrows(
                InvalidMealPlanException.class,
                () -> mealPlanService.addMealPlan(mealPlan)
        );
    }

    @Test
    void shouldRejectInvalidDuration() {

        MealPlan mealPlan = new MealPlan(
                "MP001",
                "Invalid Plan",
                "Test",
                3000,
                0,
                "V001"
        );

        assertThrows(
                InvalidMealPlanException.class,
                () -> mealPlanService.addMealPlan(mealPlan)
        );
    }

    @Test
    void shouldRejectEmptyName() {

        MealPlan mealPlan = new MealPlan(
                "MP001",
                "",
                "Test",
                3000,
                30,
                "V001"
        );

        assertThrows(
                InvalidMealPlanException.class,
                () -> mealPlanService.addMealPlan(mealPlan)
        );
    }

    @Test
    void shouldThrowExceptionWhenMealPlanDoesNotExist() {

        assertThrows(
                MealPlanNotFoundException.class,
                () -> mealPlanService.getMealPlanById("MP999")
        );
    }

    @Test
    void shouldFindMealPlansByVendor() {

        MealPlan first = new MealPlan(
                "MP001",
                "Veg Plan",
                "Lunch",
                2500,
                30,
                "V001"
        );

        MealPlan second = new MealPlan(
                "MP002",
                "Premium Plan",
                "Lunch + Dinner",
                4000,
                30,
                "V001"
        );

        MealPlan third = new MealPlan(
                "MP003",
                "Student Plan",
                "Lunch",
                1800,
                30,
                "V002"
        );

        mealPlanService.addMealPlan(first);
        mealPlanService.addMealPlan(second);
        mealPlanService.addMealPlan(third);

        assertEquals(
                2,
                mealPlanService
                        .getMealPlansByVendorId("V001")
                        .size()
        );
    }
}
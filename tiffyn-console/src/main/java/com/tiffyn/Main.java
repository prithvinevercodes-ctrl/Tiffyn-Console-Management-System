package com.tiffyn;

import com.tiffyn.model.MealPlan;
import com.tiffyn.service.MealPlanService;

public class Main {

    public static void main(String[] args) {

        MealPlanService mealPlanService = new MealPlanService();

        MealPlan mealPlan1 = new MealPlan(
                "MP001",
                "Monthly Veg Plan",
                "Lunch + Dinner",
                3000.00,
                30,
                "V001"
        );

        MealPlan mealPlan2 = new MealPlan(
                "MP002",
                "Monthly Premium Plan",
                "Lunch + Dinner + Snacks",
                4500.00,
                30,
                "V001"
        );

        MealPlan mealPlan3 = new MealPlan(
                "MP003",
                "Student Plan",
                "Lunch",
                1800.00,
                30,
                "V002"
        );

        // Add meal plans
        mealPlanService.addMealPlan(mealPlan1);
        mealPlanService.addMealPlan(mealPlan2);
        mealPlanService.addMealPlan(mealPlan3);

        // Get all meal plans
        System.out.println("All Meal Plans:");

        mealPlanService.getAllMealPlans()
                .forEach(System.out::println);

        // Find meal plan
        System.out.println("\nSearching for MP001:");

        System.out.println(
                mealPlanService.findMealPlanById("MP001")
        );

        // Get meal plans by vendor
        System.out.println("\nMeal Plans offered by V001:");

        mealPlanService.getMealPlansByVendorId("V001")
                .forEach(System.out::println);

        // Update meal plan
        mealPlan1.setPrice(3200.00);

        mealPlanService.updateMealPlan(mealPlan1);

        System.out.println("\nAfter Update:");

        System.out.println(
                mealPlanService.findMealPlanById("MP001")
        );

        // Remove meal plan
        mealPlanService.removeMealPlan("MP003");

        System.out.println("\nAfter Removing MP003:");

        mealPlanService.getAllMealPlans()
                .forEach(System.out::println);
    }
}
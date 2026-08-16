package com.tiffyn.service;

import com.tiffyn.exception.InvalidMealPlanException;
import com.tiffyn.exception.MealPlanAlreadyExistsException;
import com.tiffyn.exception.MealPlanNotFoundException;
import com.tiffyn.model.MealPlan;

import java.util.ArrayList;
import java.util.List;

public class MealPlanService {

    private final List<MealPlan> mealPlans;

    public MealPlanService() {
        mealPlans = new ArrayList<>();
    }

    // Add a new meal plan
    public void addMealPlan(MealPlan mealPlan) {

        validateMealPlan(mealPlan);

        if (findMealPlanById(mealPlan.getMealPlanId()) != null) {
            throw new MealPlanAlreadyExistsException(
                    "Meal plan with ID "
                            + mealPlan.getMealPlanId()
                            + " already exists."
            );
        }

        mealPlans.add(mealPlan);
    }

    // Validate meal plan data
    private void validateMealPlan(MealPlan mealPlan) {

        if (mealPlan == null) {
            throw new InvalidMealPlanException(
                    "Meal plan cannot be null."
            );
        }

        if (mealPlan.getName() == null
                || mealPlan.getName().isBlank()) {

            throw new InvalidMealPlanException(
                    "Meal plan name cannot be empty."
            );
        }

        if (mealPlan.getPrice() <= 0) {

            throw new InvalidMealPlanException(
                    "Meal plan price must be greater than zero."
            );
        }

        if (mealPlan.getDuration() <= 0) {

            throw new InvalidMealPlanException(
                    "Meal plan duration must be greater than zero."
            );
        }

        if (mealPlan.getVendorId() == null
                || mealPlan.getVendorId().isBlank()) {

            throw new InvalidMealPlanException(
                    "Vendor ID cannot be empty."
            );
        }
    }

    // Find meal plan by ID
    public MealPlan findMealPlanById(String mealPlanId) {

        for (MealPlan mealPlan : mealPlans) {

            if (mealPlan.getMealPlanId().equals(mealPlanId)) {
                return mealPlan;
            }
        }

        return null;
    }

    // Get meal plan by ID or throw exception
    public MealPlan getMealPlanById(String mealPlanId) {

        MealPlan mealPlan = findMealPlanById(mealPlanId);

        if (mealPlan == null) {
            throw new MealPlanNotFoundException(
                    "Meal plan with ID "
                            + mealPlanId
                            + " not found."
            );
        }

        return mealPlan;
    }

    // Get all meal plans
    public List<MealPlan> getAllMealPlans() {
        return new ArrayList<>(mealPlans);
    }

    // Get meal plans belonging to a specific vendor
    public List<MealPlan> getMealPlansByVendorId(String vendorId) {

        List<MealPlan> vendorMealPlans = new ArrayList<>();

        for (MealPlan mealPlan : mealPlans) {

            if (mealPlan.getVendorId().equals(vendorId)) {
                vendorMealPlans.add(mealPlan);
            }
        }

        return vendorMealPlans;
    }

    // Update an existing meal plan
    public void updateMealPlan(MealPlan mealPlan) {

        validateMealPlan(mealPlan);

        MealPlan existingMealPlan =
                getMealPlanById(mealPlan.getMealPlanId());

        existingMealPlan.setName(mealPlan.getName());
        existingMealPlan.setDescription(mealPlan.getDescription());
        existingMealPlan.setPrice(mealPlan.getPrice());
        existingMealPlan.setDuration(mealPlan.getDuration());
    }

    // Remove a meal plan
    public void removeMealPlan(String mealPlanId) {

        MealPlan mealPlan = getMealPlanById(mealPlanId);

        mealPlans.remove(mealPlan);
    }
}
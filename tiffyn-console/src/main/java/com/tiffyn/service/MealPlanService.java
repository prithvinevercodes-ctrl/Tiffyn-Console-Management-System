package com.tiffyn.service;

import com.tiffyn.model.MealPlan;

import java.util.ArrayList;
import java.util.List;

public class MealPlanService {

    private final List<MealPlan> mealPlans;

    public MealPlanService() {
        mealPlans = new ArrayList<>();
    }

    public void addMealPlan(MealPlan mealPlan) {
        mealPlans.add(mealPlan);
    }

    public MealPlan findMealPlanById(String mealPlanId) {

        for (MealPlan mealPlan : mealPlans) {
            if (mealPlan.getMealPlanId().equals(mealPlanId)) {
                return mealPlan;
            }
        }

        return null;
    }

    public List<MealPlan> getAllMealPlans() {
        return new ArrayList<>(mealPlans);
    }

    public List<MealPlan> getMealPlansByVendorId(String vendorId) {

        List<MealPlan> vendorMealPlans = new ArrayList<>();

        for (MealPlan mealPlan : mealPlans) {
            if (mealPlan.getVendorId().equals(vendorId)) {
                vendorMealPlans.add(mealPlan);
            }
        }

        return vendorMealPlans;
    }

    public void updateMealPlan(MealPlan mealPlan) {

        MealPlan existingMealPlan =
                findMealPlanById(mealPlan.getMealPlanId());

        if (existingMealPlan != null) {
            existingMealPlan.setName(mealPlan.getName());
            existingMealPlan.setDescription(mealPlan.getDescription());
            existingMealPlan.setPrice(mealPlan.getPrice());
            existingMealPlan.setDuration(mealPlan.getDuration());
        }
    }

    public void removeMealPlan(String mealPlanId) {

        mealPlans.removeIf(
                mealPlan -> mealPlan.getMealPlanId().equals(mealPlanId)
        );
    }
}
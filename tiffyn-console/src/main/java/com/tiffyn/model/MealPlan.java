package com.tiffyn.model;

public class MealPlan {

    private String mealPlanId;
    private String name;
    private String description;
    private double price;
    private int duration;
    private String vendorId;

    public MealPlan(String mealPlanId, String name, String description,
                    double price, int duration, String vendorId) {
        this.mealPlanId = mealPlanId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.vendorId = vendorId;
    }

    public String getMealPlanId() {
        return mealPlanId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "MealPlan{" +
                "mealPlanId='" + mealPlanId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", vendorId='" + vendorId + '\'' +
                '}';
    }
}
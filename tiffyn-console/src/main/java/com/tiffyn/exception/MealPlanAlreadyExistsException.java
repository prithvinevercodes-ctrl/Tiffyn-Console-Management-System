package com.tiffyn.exception;

public class MealPlanAlreadyExistsException extends RuntimeException {

    public MealPlanAlreadyExistsException(String message) {
        super(message);
    }
}
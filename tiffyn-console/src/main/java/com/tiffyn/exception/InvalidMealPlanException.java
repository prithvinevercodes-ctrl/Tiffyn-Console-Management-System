package com.tiffyn.exception;

public class InvalidMealPlanException extends RuntimeException {
    public InvalidMealPlanException(String message) {
        super(message);
    }
}

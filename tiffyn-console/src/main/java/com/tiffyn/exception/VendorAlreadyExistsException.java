package com.tiffyn.exception;

public class VendorAlreadyExistsException extends RuntimeException {
    public VendorAlreadyExistsException(String message) {
        super(message);
    }
}

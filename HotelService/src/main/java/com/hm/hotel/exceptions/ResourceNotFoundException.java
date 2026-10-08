package com.hm.hotel.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Resource is not found !!");
    }

    public ResourceNotFoundException(String s) {
        super(s);
    }
}

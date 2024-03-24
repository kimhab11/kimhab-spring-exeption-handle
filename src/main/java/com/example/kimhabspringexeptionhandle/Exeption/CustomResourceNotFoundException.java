package com.example.kimhabspringexeptionhandle.Exeption;


public class CustomResourceNotFoundException extends RuntimeException {
    public CustomResourceNotFoundException(String message) {
        super(message);
    }

    public CustomResourceNotFoundException() {
        super();
    }
}

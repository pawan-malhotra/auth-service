package com.lapp.auth.exceptions;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleNotFoundException(String message) {
        super(message);
    }
}

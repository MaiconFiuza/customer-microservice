package com.fiuza.fiap.customer.core.exceptions;

public class InternalServerError extends RuntimeException{
    public InternalServerError(String message) {
        super(message);
    }
}

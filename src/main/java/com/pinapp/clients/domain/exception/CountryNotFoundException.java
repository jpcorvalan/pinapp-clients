package com.pinapp.clients.domain.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(Number number) {
        super("El ID " + number + " de país especificado no existe");
    }
}

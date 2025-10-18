package com.pinapp.clients.domain.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Number number) {
        super("El cliente con el ID " + number + " no existe");
    }
}

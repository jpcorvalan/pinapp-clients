package com.pinapp.clients.domain.exception;

public class DocumentTypeNotFoundException extends RuntimeException {
    public DocumentTypeNotFoundException(Number number) {
        super("El ID " + number + " de tipo de documento especificado no existe");
    }
}

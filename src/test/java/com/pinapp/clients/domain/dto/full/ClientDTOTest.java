package com.pinapp.clients.domain.dto.full;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ClientDTOTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void emptyFields_ShouldBeValidated() {
        ClientDTO dto = new ClientDTO();

        Set<ConstraintViolation<ClientDTO>> violations = validator.validate(dto);

        Set<String> fieldsWithErrors = violations.stream()
                .map(v -> v.getPropertyPath().toString())
                .collect(Collectors.toSet());

        Set<String> expectedFields = Set.of("country", "lastName", "firstName", "documentType", "documentNumber", "active", "birthDate", "email");

        assertTrue(fieldsWithErrors.containsAll(expectedFields));
    }

}
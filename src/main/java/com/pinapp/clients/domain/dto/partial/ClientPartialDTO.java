package com.pinapp.clients.domain.dto.partial;

public record ClientPartialDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone
) {
}

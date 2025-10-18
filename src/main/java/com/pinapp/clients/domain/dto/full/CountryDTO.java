package com.pinapp.clients.domain.dto.full;

import com.pinapp.clients.domain.dto.partial.ClientPartialDTO;

import java.util.Set;

public record CountryDTO(
        Integer id,
        String country,
        String code,
        Set<ClientPartialDTO> clients
) {
}

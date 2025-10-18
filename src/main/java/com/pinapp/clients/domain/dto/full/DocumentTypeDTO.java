package com.pinapp.clients.domain.dto.full;

import com.pinapp.clients.domain.dto.partial.ClientPartialDTO;

import java.util.Set;

public record DocumentTypeDTO(
        Integer id,
        String docType,
        Set<ClientPartialDTO> clients
) {
}

package com.pinapp.clients.domain.dto.full;

import com.pinapp.clients.domain.dto.partial.ClientPartialDTO;
import lombok.Data;

import java.util.Set;

@Data
public class DocumentTypeDTO {
    private Integer id;
    private String docType;
    private Set<ClientPartialDTO> client;
}

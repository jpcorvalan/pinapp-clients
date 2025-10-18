package com.pinapp.clients.domain.repository;

import com.pinapp.clients.domain.dto.full.DocumentTypeDTO;
import com.pinapp.clients.domain.dto.partial.DocumentTypePartialDTO;

import java.util.Set;

public interface DocumentTypeRepository {
    DocumentTypeDTO getById(int id);
    Set<DocumentTypePartialDTO> getAll();
}

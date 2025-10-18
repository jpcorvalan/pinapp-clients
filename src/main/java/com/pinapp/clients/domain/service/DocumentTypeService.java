package com.pinapp.clients.domain.service;

import com.pinapp.clients.domain.dto.full.DocumentTypeDTO;
import com.pinapp.clients.domain.dto.partial.DocumentTypePartialDTO;
import com.pinapp.clients.domain.repository.DocumentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DocumentTypeService {
    private final DocumentTypeRepository documentTypeRepository;

    public DocumentTypeService(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public DocumentTypeDTO getById(int id) {
        return documentTypeRepository.getById(id);
    }

    public Set<DocumentTypePartialDTO> getAll() {
        return documentTypeRepository.getAll();
    }

}

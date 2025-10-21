package com.pinapp.clients.domain.service;

import com.pinapp.clients.domain.dto.full.DocumentTypeDTO;
import com.pinapp.clients.domain.dto.partial.DocumentTypePartialDTO;
import com.pinapp.clients.domain.exception.DocumentTypeNotFoundException;
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
        DocumentTypeDTO documentTypeDTO = documentTypeRepository.getById(id);

        if(documentTypeDTO == null) throw new DocumentTypeNotFoundException(id);

        return documentTypeDTO;
    }

    public Set<DocumentTypePartialDTO> getAll() {
        return documentTypeRepository.getAll();
    }

}

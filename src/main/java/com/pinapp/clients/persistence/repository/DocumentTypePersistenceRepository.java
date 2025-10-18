package com.pinapp.clients.persistence.repository;

import com.pinapp.clients.domain.dto.full.DocumentTypeDTO;
import com.pinapp.clients.domain.dto.partial.DocumentTypePartialDTO;
import com.pinapp.clients.domain.repository.DocumentTypeRepository;
import com.pinapp.clients.persistence.crud.DocumentTypeCrud;
import com.pinapp.clients.persistence.mapper.full.DocumentTypeMapper;
import com.pinapp.clients.persistence.mapper.partial.DocumentTypePartialMapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class DocumentTypePersistenceRepository implements DocumentTypeRepository {

    private final DocumentTypeCrud documentTypeCrud;
    private final DocumentTypeMapper documentTypeMapper;
    private final DocumentTypePartialMapper documentTypePartialMapper;

    public DocumentTypePersistenceRepository(DocumentTypeCrud documentTypeCrud, DocumentTypeMapper documentTypeMapper, DocumentTypePartialMapper documentTypePartialMapper) {
        this.documentTypeCrud = documentTypeCrud;
        this.documentTypeMapper = documentTypeMapper;
        this.documentTypePartialMapper = documentTypePartialMapper;
    }

    @Override
    public DocumentTypeDTO getById(int id) {
        return documentTypeMapper.toDTO(documentTypeCrud.findById(id).orElse(null));
    }

    @Override
    public Set<DocumentTypePartialDTO> getAll() {
        return documentTypePartialMapper.toDTO(documentTypeCrud.findAll());
    }
}

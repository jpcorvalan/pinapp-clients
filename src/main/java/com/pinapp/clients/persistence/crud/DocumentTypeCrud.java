package com.pinapp.clients.persistence.crud;

import com.pinapp.clients.persistence.entity.DocumentTypeEntity;
import org.springframework.data.repository.CrudRepository;

public interface DocumentTypeCrud extends CrudRepository<DocumentTypeEntity, Integer> {
}

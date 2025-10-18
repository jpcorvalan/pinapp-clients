package com.pinapp.clients.persistence.mapper.partial;

import com.pinapp.clients.domain.dto.partial.DocumentTypePartialDTO;
import com.pinapp.clients.persistence.entity.DocumentTypeEntity;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface DocumentTypePartialMapper {
    Set<DocumentTypePartialDTO> toDTO(Iterable<DocumentTypeEntity> entity);
}

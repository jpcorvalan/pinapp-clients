package com.pinapp.clients.persistence.mapper.full;

import com.pinapp.clients.domain.dto.full.DocumentTypeDTO;
import com.pinapp.clients.persistence.entity.DocumentTypeEntity;
import com.pinapp.clients.persistence.mapper.partial.ClientPartialMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ClientPartialMapper.class})
public interface DocumentTypeMapper {
    DocumentTypeDTO toDTO(DocumentTypeEntity entity);
}

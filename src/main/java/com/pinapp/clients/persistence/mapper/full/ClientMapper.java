package com.pinapp.clients.persistence.mapper.full;

import com.pinapp.clients.domain.dto.full.ClientDTO;
import com.pinapp.clients.persistence.entity.ClientEntity;
import com.pinapp.clients.persistence.mapper.partial.CountryPartialMapper;
import com.pinapp.clients.persistence.mapper.partial.DocumentTypePartialMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CountryPartialMapper.class, DocumentTypePartialMapper.class})
public interface ClientMapper {
    ClientDTO toDTO(ClientEntity entity);

    @Mapping(target = "country", source = "country")
    ClientEntity toEntity(ClientDTO dto);
}

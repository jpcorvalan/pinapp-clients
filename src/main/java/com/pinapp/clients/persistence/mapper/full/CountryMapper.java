package com.pinapp.clients.persistence.mapper.full;

import com.pinapp.clients.domain.dto.full.CountryDTO;
import com.pinapp.clients.persistence.entity.CountryEntity;
import com.pinapp.clients.persistence.mapper.partial.ClientPartialMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ClientPartialMapper.class})
public interface CountryMapper {
    CountryDTO toDTO(CountryEntity entity);
    CountryEntity toEntity(CountryDTO dto);
}

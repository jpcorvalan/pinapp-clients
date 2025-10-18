package com.pinapp.clients.persistence.mapper.partial;

import com.pinapp.clients.domain.dto.partial.CountryPartialDTO;
import com.pinapp.clients.persistence.entity.CountryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ClientPartialMapper.class})
public interface CountryPartialMapper {
    CountryPartialDTO toDTO(CountryEntity entity);
    List<CountryPartialDTO> toDTO(Iterable<CountryEntity> entities);
}

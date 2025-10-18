package com.pinapp.clients.persistence.mapper.partial;

import com.pinapp.clients.domain.dto.partial.ClientPartialDTO;
import com.pinapp.clients.persistence.entity.ClientEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CountryPartialMapper.class})
public interface ClientPartialMapper {
    List<ClientPartialDTO> toDTO(Iterable<ClientEntity> entities);
}

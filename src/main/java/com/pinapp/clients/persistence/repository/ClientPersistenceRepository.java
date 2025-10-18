package com.pinapp.clients.persistence.repository;

import com.pinapp.clients.domain.dto.full.ClientDTO;
import com.pinapp.clients.domain.dto.partial.ClientPartialDTO;
import com.pinapp.clients.domain.repository.ClientRepository;
import com.pinapp.clients.persistence.crud.ClientCrud;
import com.pinapp.clients.persistence.mapper.full.ClientMapper;
import com.pinapp.clients.persistence.mapper.partial.ClientPartialMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientPersistenceRepository implements ClientRepository {
    private final ClientCrud clientCrud;
    private final ClientMapper clientMapper;
    private final ClientPartialMapper clientPartialMapper;

    public ClientPersistenceRepository(ClientCrud clientEntity, ClientMapper clientMapper, ClientPartialMapper clientPartialMapper) {
        this.clientCrud = clientEntity;
        this.clientMapper = clientMapper;
        this.clientPartialMapper = clientPartialMapper;
    }

    @Override
    public ClientDTO getById(long id) {
        return this.clientCrud.findById(id)
                .map(clientMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<ClientPartialDTO> getAll() {
        return this.clientPartialMapper.toDTO(this.clientCrud.findAll());
    }

    @Override
    public ClientDTO save(ClientDTO client) {
        return this.clientMapper.toDTO(clientCrud.save(clientMapper.toEntity(client)));
    }
}

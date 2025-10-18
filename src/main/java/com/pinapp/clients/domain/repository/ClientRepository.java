package com.pinapp.clients.domain.repository;

import com.pinapp.clients.domain.dto.full.ClientDTO;
import com.pinapp.clients.domain.dto.partial.ClientPartialDTO;

import java.util.List;

public interface ClientRepository {
    ClientDTO getById(long id);
    List<ClientPartialDTO> getAll();
    ClientDTO save(ClientDTO client);
}

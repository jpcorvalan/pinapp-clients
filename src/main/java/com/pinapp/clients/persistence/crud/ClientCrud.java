package com.pinapp.clients.persistence.crud;

import com.pinapp.clients.persistence.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientCrud extends CrudRepository<ClientEntity, Long> {

}

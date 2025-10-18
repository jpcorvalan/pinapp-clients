package com.pinapp.clients.persistence.crud;

import com.pinapp.clients.persistence.entity.CountryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CountryCrud extends CrudRepository<CountryEntity, Long> {
}

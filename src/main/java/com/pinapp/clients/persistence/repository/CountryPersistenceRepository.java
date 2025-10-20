package com.pinapp.clients.persistence.repository;

import com.pinapp.clients.domain.dto.full.CountryDTO;
import com.pinapp.clients.domain.dto.partial.CountryPartialDTO;
import com.pinapp.clients.domain.repository.CountryRepository;
import com.pinapp.clients.persistence.crud.CountryCrud;
import com.pinapp.clients.persistence.mapper.full.CountryMapper;
import com.pinapp.clients.persistence.mapper.partial.CountryPartialMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CountryPersistenceRepository implements CountryRepository {
    private final CountryCrud countryCrud;
    private final CountryMapper countryMapper;
    private final CountryPartialMapper countryPartialMapper;

    public CountryPersistenceRepository(CountryCrud countryCrud, CountryMapper countryMapper, CountryPartialMapper countryPartialMapper) {
        this.countryCrud = countryCrud;
        this.countryMapper = countryMapper;
        this.countryPartialMapper = countryPartialMapper;
    }

    @Override
    public CountryDTO getById(int id) {
        return countryMapper.toDTO(countryCrud.findById(id).orElse(null));
    }

    @Override
    public List<CountryPartialDTO> getAll() {
        return countryPartialMapper.toDTO(countryCrud.findAll());
    }
}

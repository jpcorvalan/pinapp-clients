package com.pinapp.clients.domain.repository;

import com.pinapp.clients.domain.dto.full.CountryDTO;
import com.pinapp.clients.domain.dto.partial.CountryPartialDTO;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {
    CountryDTO getById(long id);
    List<CountryPartialDTO> getAll();
}

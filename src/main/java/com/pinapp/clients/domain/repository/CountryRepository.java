package com.pinapp.clients.domain.repository;

import com.pinapp.clients.domain.dto.full.CountryDTO;
import com.pinapp.clients.domain.dto.partial.CountryPartialDTO;

import java.util.List;

public interface CountryRepository {
    CountryDTO getById(int id);
    List<CountryPartialDTO> getAll();
}

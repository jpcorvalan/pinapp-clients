package com.pinapp.clients.domain.service;

import com.pinapp.clients.domain.dto.full.CountryDTO;
import com.pinapp.clients.domain.dto.partial.CountryPartialDTO;
import com.pinapp.clients.domain.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public CountryDTO getById(long id) {
        return countryRepository.getById(id);
    }

    public List<CountryPartialDTO> getAll() {
        return countryRepository.getAll();
    }

}

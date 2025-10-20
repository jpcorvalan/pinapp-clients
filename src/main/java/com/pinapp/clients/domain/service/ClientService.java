package com.pinapp.clients.domain.service;

import com.pinapp.clients.domain.dto.full.ClientDTO;
import com.pinapp.clients.domain.dto.full.CountryDTO;
import com.pinapp.clients.domain.dto.full.DocumentTypeDTO;
import com.pinapp.clients.domain.dto.partial.ClientPartialDTO;
import com.pinapp.clients.domain.dto.partial.CountryPartialDTO;
import com.pinapp.clients.domain.dto.partial.DocumentTypePartialDTO;
import com.pinapp.clients.domain.exception.ClientNotFoundException;
import com.pinapp.clients.domain.exception.CountryNotFoundException;
import com.pinapp.clients.domain.exception.DocumentTypeNotFoundException;
import com.pinapp.clients.domain.repository.ClientRepository;
import com.pinapp.clients.domain.repository.CountryRepository;
import com.pinapp.clients.domain.repository.DocumentTypeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final CountryRepository countryRepository;
    private final DocumentTypeRepository documentTypeRepository;

    public ClientService(ClientRepository clientRepository, CountryRepository countryRepository, DocumentTypeRepository documentTypeRepository) {
        this.clientRepository = clientRepository;
        this.countryRepository = countryRepository;
        this.documentTypeRepository = documentTypeRepository;
    }

    public List<ClientPartialDTO> getAll() {
        return this.clientRepository.getAll();
    }

    public ClientDTO getById(long id) {
        ClientDTO client = this.clientRepository.getById(id);

        if(client == null) throw new ClientNotFoundException(id);

        return client;
    }

    public ClientDTO save(ClientDTO client) {
        CountryPartialDTO countryPartialDTO = findCountry(client);
        DocumentTypePartialDTO documentTypePartialDTO = findDocumentType(client);

        client.setAge(calculateAge(client.getBirthDate()));
        client.setCountry(countryPartialDTO);
        client.setDocumentType(documentTypePartialDTO);

        return clientRepository.save(client);
    }

    private CountryPartialDTO findCountry(ClientDTO client) {
        Integer countryId = client.getCountry().id();

        CountryDTO country = Optional.ofNullable(countryRepository.getById(countryId))
                .orElseThrow(() -> new CountryNotFoundException(countryId));

        return new CountryPartialDTO(country.getId(), country.getCountry(), country.getCode());
    }

    private DocumentTypePartialDTO findDocumentType(ClientDTO client) {
        Integer documentId = client.getDocumentType().id();

        DocumentTypeDTO documentTypeDTO = Optional.ofNullable(documentTypeRepository.getById(documentId))
                .orElseThrow(() -> new DocumentTypeNotFoundException(documentId));

        return new DocumentTypePartialDTO(documentTypeDTO.getId(), documentTypeDTO.getDocType());
    }

    private Integer calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}

package com.pinapp.clients.domain.service;

import com.pinapp.clients.domain.dto.full.ClientDTO;
import com.pinapp.clients.domain.dto.full.CountryDTO;
import com.pinapp.clients.domain.dto.full.DocumentTypeDTO;
import com.pinapp.clients.domain.dto.partial.CountryPartialDTO;
import com.pinapp.clients.domain.dto.partial.DocumentTypePartialDTO;
import com.pinapp.clients.domain.exception.CountryNotFoundException;
import com.pinapp.clients.domain.exception.DocumentTypeNotFoundException;
import com.pinapp.clients.domain.repository.ClientRepository;
import com.pinapp.clients.domain.repository.CountryRepository;
import com.pinapp.clients.domain.repository.DocumentTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock CountryRepository countryRepository;
    @Mock DocumentTypeRepository documentTypeRepository;
    @Mock ClientRepository clientRepository;
    @InjectMocks ClientService service;

    @Test
    void save_ShouldPersistsClientsAndRelations() {
        ClientDTO client = new ClientDTO();

        client.setDocumentNumber("42000111");
        client.setCountry(new CountryPartialDTO(1, "Argentina", "AR"));
        client.setDocumentType(new DocumentTypePartialDTO(1, "DNI"));
        client.setBirthDate(LocalDate.of(1998,8,28));

        when(countryRepository.getById(1)).thenReturn(new CountryDTO());
        when(documentTypeRepository.getById(1)).thenReturn(new DocumentTypeDTO());

        when(clientRepository.save(any(ClientDTO.class))).thenAnswer(inv -> inv.getArgument(0));

        ClientDTO result = service.save(client);

        assertNotNull(result);
        assertEquals("42000111", result.getDocumentNumber());
        assertNotNull(result.getCountry());
        assertNotNull(result.getDocumentType());
    }

    @Test
    void save_ShouldThrowExceptionWhenCountryNotFound() {
        ClientDTO dto = new ClientDTO();

        dto.setCountry(new CountryPartialDTO(1, "Argentina", "AR"));
        dto.setDocumentType(new DocumentTypePartialDTO(1, "DNI"));

        when(countryRepository.getById(1)).thenReturn(null);

        assertThrows(CountryNotFoundException.class, () -> service.save(dto));
    }

    @Test
    void save_ShouldThrowExceptionWhenDocumentTypeNotFound() {
        ClientDTO dto = new ClientDTO();

        dto.setCountry(new CountryPartialDTO(1, "Argentina", "AR"));
        dto.setDocumentType(new DocumentTypePartialDTO(1, "DNI"));

        when(countryRepository.getById(1)).thenReturn(new CountryDTO());
        when(documentTypeRepository.getById(1)).thenReturn(null);

        assertThrows(DocumentTypeNotFoundException.class, () -> service.save(dto));
    }
}
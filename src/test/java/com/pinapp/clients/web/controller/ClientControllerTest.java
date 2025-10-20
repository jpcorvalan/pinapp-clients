package com.pinapp.clients.web.controller;

import com.pinapp.clients.domain.dto.full.ClientDTO;
import com.pinapp.clients.domain.dto.partial.CountryPartialDTO;
import com.pinapp.clients.domain.dto.partial.DocumentTypePartialDTO;
import com.pinapp.clients.domain.service.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ClientService service;

    @Test
    void getAll() {
    }

    @Test
    void getById() {
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void save() throws Exception {
        ClientDTO dto = new ClientDTO();

        dto.setDocumentNumber("42000111");
        dto.setCountry(new CountryPartialDTO(1, "Argentina", "AR"));
        dto.setDocumentType(new DocumentTypePartialDTO(1, "DNI"));
        dto.setBirthDate(LocalDate.of(1998, 8, 28));

        when(service.save(any())).thenReturn(dto);

        mockMvc.perform(post("/clients")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType("application/json")
                        .content("""
                                {
                                    "firstName": "Juan",
                                    "lastName": "Corvalán",
                                    "email": "jcorvalan2@gmail.com",
                                    "phone": "+5227126957899",
                                    "address": "Corrientes",
                                    "city": "Buenos Aires",
                                    "country": {
                                        "id": 1
                                    },
                                    "documentType": {
                                        "id": 1
                                    },
                                    "documentNumber": "8999999",
                                    "birthDate": "1998-08-28",
                                    "registrationDate": "2023-11-25",
                                    "active": true,
                                    "gender": "Male"
                                }
                                """))
                .andExpect(status().isCreated());


    }
}
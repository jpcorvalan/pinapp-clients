package com.pinapp.clients.domain.dto.full;

import com.pinapp.clients.domain.dto.partial.ClientPartialDTO;
import lombok.Data;

import java.util.Set;

@Data
public class CountryDTO {
    private Integer id;
    private String country;
    private String code;
    private Set<ClientPartialDTO> client;
}

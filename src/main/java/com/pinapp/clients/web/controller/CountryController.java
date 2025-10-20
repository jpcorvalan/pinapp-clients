package com.pinapp.clients.web.controller;

import com.pinapp.clients.domain.dto.partial.CountryPartialDTO;
import com.pinapp.clients.domain.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@Tag(
        name = "Countries",
        description = "Endpoints para consultar el catálogo de países disponibles en el sistema."
)
@SecurityRequirement(name = "basicAuth")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Operation(
            summary = "Listar países (resumido)",
            description = "Retorna el listado de países con información básica (códigos y nombre). ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Listado obtenido correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CountryPartialDTO.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "No autenticado",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Sin permisos para consultar países",
                            content = @Content
                    )
            }
    )
    @GetMapping("/countries")
    public List<CountryPartialDTO> getAll() {
        return countryService.getAll();
    }
}

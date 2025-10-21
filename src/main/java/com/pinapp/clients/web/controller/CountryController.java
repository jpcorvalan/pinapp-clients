package com.pinapp.clients.web.controller;

import com.pinapp.clients.domain.dto.full.CountryDTO;
import com.pinapp.clients.domain.dto.full.CountryDTO;
import com.pinapp.clients.domain.dto.partial.CountryPartialDTO;
import com.pinapp.clients.domain.service.CountryService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(
        name = "Countries",
        description = "Endpoints para consultar el catálogo de países disponibles en el sistema."
)
@SecurityRequirement(name = "basicAuth")
@RequestMapping("/countries")
@RestController
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Operation(
            summary = "Obtener Country por ID",
            description = "Busca y devuelve el detalle completo de un Country por su id. "
                    + "Si no existe, retorna **404 Not Found**.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Identificador único del Country",
                            required = true,
                            example = "1"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Country encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CountryDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Country no encontrado",
                            content = @Content
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> findById(@PathVariable("id") int id) {
        CountryDTO country = this.countryService.getById(id);
        return country != null ? ResponseEntity.ok(country) : ResponseEntity.notFound().build();
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
    @GetMapping("/all")
    public ResponseEntity<List<CountryPartialDTO>> getAll() {
        return ResponseEntity.ok(countryService.getAll());
    }
}

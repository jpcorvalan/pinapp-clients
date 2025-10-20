package com.pinapp.clients.web.controller;

import com.pinapp.clients.domain.dto.full.DocumentTypeDTO;
import com.pinapp.clients.domain.dto.partial.DocumentTypePartialDTO;
import com.pinapp.clients.domain.service.DocumentTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/document-types")
@Tag(
        name = "Documents",
        description = "Endpoints para consultar los tipos de documento válidos (por ejemplo, DNI, Pasaporte, etc.)."
)
@SecurityRequirement(name = "basicAuth")
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @Operation(
            summary = "Obtener tipo de documento por ID",
            description = "Devuelve el tipo de documento correspondiente al identificador solicitado. ",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Identificador único del tipo de documento",
                            required = true,
                            example = "1"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Tipo de documento encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DocumentTypeDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Tipo de documento no encontrado",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "No autenticado",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Sin permisos para acceder a este recurso",
                            content = @Content
                    )
            }
    )
    @GetMapping(path = "/{id}")
    public DocumentTypeDTO getById(@PathVariable("id") int id) {
        return documentTypeService.getById(id);
    }

    @Operation(
            summary = "Listar todos los tipos de documento",
            description = "Devuelve el conjunto completo de tipos de documento registrados en el sistema. ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Listado de tipos de documento obtenido correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = DocumentTypePartialDTO.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "No autenticado",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Sin permisos para consultar los tipos de documento",
                            content = @Content
                    )
            }
    )
    @GetMapping(path = "/all")
    public Set<DocumentTypePartialDTO> getAll() {
        return documentTypeService.getAll();
    }
}

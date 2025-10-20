package com.pinapp.clients.web.controller;

import com.pinapp.clients.domain.dto.full.ClientDTO;
import com.pinapp.clients.domain.dto.partial.ClientPartialDTO;
import com.pinapp.clients.domain.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Clients",
        description = "Endpoints para consultar y administrar clientes. Incluye listados resumidos, consulta por ID y alta de nuevos clientes."
)
@SecurityRequirement(name = "basicAuth") // <-- si usás JWT/Bearer
@RequestMapping("/clients")
@RestController
public class ClientController {

    private final ClientService clientService;
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(
            summary = "Listar clientes (resumido)",
            description = "Devuelve un listado paginable/filtrable en el futuro (hoy simple) de clientes con campos básicos. "
                    + "Útil para catálogos o listados en grilla. Puede expandirse con filtros/paginación más adelante.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Listado obtenido correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ClientPartialDTO.class))
                            )
                    )
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<ClientPartialDTO>> getAll() {
        return ResponseEntity.ok(this.clientService.getAll());
    }

    @Operation(
            summary = "Obtener cliente por ID",
            description = "Busca y devuelve el detalle completo de un cliente por su id. "
                    + "Si no existe, retorna **404 Not Found**.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Identificador único del cliente",
                            required = true,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cliente encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ClientDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cliente no encontrado",
                            content = @Content
                    )
            }
    )
    @GetMapping(path = "/{id}")
    public ResponseEntity<ClientDTO> getById(@PathVariable("id") long id) {
        ClientDTO client = clientService.getById(id);
        return client != null ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Crear un nuevo cliente",
            description = "Crea un registro de cliente con los datos enviados. "
                    + "Aplica validaciones de negocio y de formato (`@Valid`). "
                    + "Retorna el recurso creado con **201 Created**.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Datos completos del cliente a crear",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClientDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Cliente creado correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ClientDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud inválida (errores de validación)",
                            content = @Content // si tenés un ApiErrorResponse, podés poner su schema aquí
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "No autenticado",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Sin permisos para crear clientes",
                            content = @Content
                    )
            }
    )
    @PostMapping
    public ResponseEntity<ClientDTO> save(@Valid @RequestBody ClientDTO client) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientService.save(client));
    }
}


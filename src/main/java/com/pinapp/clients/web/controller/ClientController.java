package com.pinapp.clients.web.controller;

import com.pinapp.clients.domain.dto.full.ClientDTO;
import com.pinapp.clients.domain.dto.partial.ClientPartialDTO;
import com.pinapp.clients.domain.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/clients")
@RestController
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientPartialDTO>> getAll() {
        return ResponseEntity.ok(this.clientService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClientDTO> getById(@PathVariable("id") long id) {
        ClientDTO client = clientService.getById(id);

        return client != null ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ClientDTO> save(@Valid @RequestBody ClientDTO client) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientService.save(client));
    }
}

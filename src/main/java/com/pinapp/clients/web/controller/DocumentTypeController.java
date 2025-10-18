package com.pinapp.clients.web.controller;

import com.pinapp.clients.domain.dto.full.DocumentTypeDTO;
import com.pinapp.clients.domain.dto.partial.DocumentTypePartialDTO;
import com.pinapp.clients.domain.service.DocumentTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/document-types")
public class DocumentTypeController {
    private final DocumentTypeService documentTypeService;

    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @GetMapping(path = "/{id}")
    public DocumentTypeDTO getById(@PathVariable("id") int id) {
        return documentTypeService.getById(id);
    }

    @GetMapping(path = "/all")
    public Set<DocumentTypePartialDTO> getAll() {
        return documentTypeService.getAll();
    }
}

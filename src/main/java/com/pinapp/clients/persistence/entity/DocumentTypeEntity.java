package com.pinapp.clients.persistence.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "document_types")
public class DocumentTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(name = "doc_type", length = 50)
    private String docType;

    @OneToMany(mappedBy = "documentType")
    private Set<ClientEntity> clients;

    public DocumentTypeEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public Set<ClientEntity> getClients() {
        return clients;
    }

    public void setClients(Set<ClientEntity> clients) {
        this.clients = clients;
    }
}

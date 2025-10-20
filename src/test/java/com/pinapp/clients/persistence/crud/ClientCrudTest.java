package com.pinapp.clients.persistence.crud;

import com.pinapp.clients.persistence.entity.ClientEntity;
import com.pinapp.clients.persistence.entity.CountryEntity;
import com.pinapp.clients.persistence.entity.DocumentTypeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest(properties = "spring.jpa.properties.hibernate.hbm2ddl.import_files=")
class ClientCrudTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ClientCrud clientCrud;

    @Test
    void findById_ShouldReturnClient() {
        ClientEntity cli = new ClientEntity();

        CountryEntity country = new CountryEntity();
        country.setCountry("Argentina");
        country.setCode("AR");
        entityManager.persist(country);

        DocumentTypeEntity documentType = new DocumentTypeEntity();
        documentType.setDocType("DNI");
        entityManager.persist(documentType);

        cli.setCountry(country);
        cli.setFirstName("Marco");
        cli.setLastName("Polo");
        cli.setDocumentType(documentType);
        cli.setDocumentNumber("41000111");
        cli.setBirthDate(LocalDate.of(1998, 8, 28));
        cli.setEmail("email@email.com");
        cli.setAge(27);

        entityManager.persistAndFlush(cli);

        Optional<ClientEntity> found = clientCrud.findById(cli.getId());

        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals("41000111", found.get().getDocumentNumber());
    }
}
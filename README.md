# pinapp-clients — API (Spring Boot + DDD)

## Índice
- [Arquitectura](#arquitectura)
- [Stack](#stack)
- [Cómo ejecutar](#cómo-ejecutar)
- [Despliegue en la nube (GCP)](#despliegue-en-la-nube-gcp)
- [Seguridad (Spring Security)](#seguridad)
- [Documentación de la API (OpenAPI)](#documentación-de-la-api-openapi)
- [Herramientas utilizadas](#herramientas-utilizadas)
- [Referencias](#referencias)

---

## Arquitectura
- Se utilizó la arquitectura DDD (Domain-Driven Design), con el objetivo de aislar la lógica de negocio de la infraestructura y así facilitar la evolución del sistema
#### Patrones:
- **Data mapper**: se utilizó combinado con el patrón DDD, para no exponer las entidades tal cual están en la base de datos, y si exponer DTOs. Estos DTOs responderán a las necesidades de negocio de la capa de Dominio
- **Repository**: también, combinado con la arquitectura DDD, aisla la capa de la persistencia, y desacopla la base de datos del dominio y las reglas de negocio.

## Stack
- Java 21, Maven
- Spring Boot 3.5.6
- Spring Security 6.5.5
- Persistencia: H2, JPA/Hibernate
- Documentación: **springdoc-openapi** (Swagger UI)
- Testing: JUnit 5, Mockito, SpringBoot Starter Test, Spring Security Test

## Cómo ejecutar

### Local
Maven (tener libre el puerto 8080):
```bash
.\mvnw.cmd spring-boot:run
```

## Despliegue en la nube (GCP)

La aplicación está desplegada en Google Cloud Platform (GCP) mediante Cloud Run, bajo la siguiente URL:
- https://clients-api-156073003419.southamerica-east1.run.app/pinapp/api/

Para probar que efectivamente está subido y online, ingresar al siguiente endpoint
- https://clients-api-156073003419.southamerica-east1.run.app/pinapp/api/actuator/health

## Seguridad
La aplicación cuenta con seguridad y autenticación de usuarios básica, brindada por Spring Security, se explica a continuación como hacer cualquier request en los endpoints publicados:

- Mediante Postman, en la pestaña **Authorization**, elegir **Basic Auth**, y colocar "admin" en **Username** y "admin" en **Password**

## Documentación de la API (OpenAPI)

- Swagger UI (local): http://localhost:8080/swagger-ui/index.html
- Swagger UI (GCP): https://clients-api-156073003419.southamerica-east1.run.app/pinapp/api/swagger-ui/index.html#/
- Colección de Postman: https://drive.google.com/file/d/1nePzcCg44PVE5RcQNlJ8sXjWzWB6o1tF/view?usp=sharing

## Herramientas utilizadas
- IntelliJ IDEA
- Postman
- Docker
- Google Cloud CLI (SDK Shell)
- ChatGPT

## Referencias
- https://platzi.com/cursos/java-spring-boot/
- https://platzi.com/cursos/java-spring-security/
- https://platzi.com/cursos/desarrollo-despliegue-gcp/

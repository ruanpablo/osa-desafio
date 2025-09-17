# 🏦 Desafio Técnico – Agência API

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/seuusuario/repositorio)
[![Coverage](https://img.shields.io/badge/coverage-90%25-brightgreen)](https://github.com/seuusuario/repositorio)

Este projeto foi desenvolvido como parte de um **desafio técnico para o Banco Santander**, implementando APIs REST para **gerenciamento e consulta de agências**.

A aplicação foi construída em **Java 17**, utilizando **Spring Boot**, **Spring Data JPA**, **MapStruct** e **H2 em memória** para testes.

---

## 💡 Objetivo

1. **Cadastrar agências** com nome, latitude e longitude.
2. **Buscar agências mais próximas** de uma posição geográfica, com paginação.

Inclui:

* Camada de service
* Controller REST
* Repository JPA
* Testes unitários e integração

---

## 🏗️ Estrutura do Projeto

```
src/main/java
 └── com.osa.desafio
      ├── controller
      │    └── AgencyController         -> Endpoints REST
      │
      ├── core
      │    └── agency
      │         ├── mapper                  -> Mapeamento de entidades
      │         ├── model                   -> Entidades JPA
      │         ├── repository              -> Repositório JPA
      │         ├── request                 -> Classes de request
      │         ├── response                -> Classes de response
      │         └── service                 -> Lógica de negócio
      │
      ├── exception
      │    └── response                -> Tratamento de exceções
      │
      ├── infrastructure
      │    └── swagger
      │         └── OpenApiConfig            -> Configuração do Swagger/OpenAPI
      │
      └── usecase
           └── agency
                ├── CreateAgencyUseCase     -> Caso de uso para criação de agência (interface)
                └── FindAgencyUseCase       -> Caso de uso para busca de agência (interface)


```

---

## ⚡ Tecnologias

* Java 17
* Spring Boot 3
* Spring Data JPA
* H2 Database (testes)
* MapStruct
* JUnit 5 + Mockito
* MockMvc
* Swagger/OpenAPI 3
* Flyway

---

## 🚀 Executando a Aplicação

1. Clone o repositório:

```bash
git clone <URL_DO_REPOSITORIO>
cd desafio
```

2. Compile e rode a aplicação:

```bash
./mvnw spring-boot:run
```

3. A aplicação estará disponível em:

```
http://localhost:8080/desafio
```

4. Swagger (documentação interativa):

```
http://localhost:8080/desafio/swagger-ui.html
```

**Exemplo do Swagger UI:**


---

## 📝 Endpoints

### 1️⃣ Cadastrar Agência

* **URL:** `POST /cadastrar`
* **Request Body:**

```json
{
  "name": "Agência Teste",
  "latitude": 10.0,
  "longitude": 20.0
}
```

* **Response (201 CREATED):**

```json
{
  "id": "UUID",
  "name": "Agência Teste",
  "latitude": 10.0,
  "longitude": 20.0
}
```

### 2️⃣ Buscar Agências Próximas

* **URL:** `GET /distancia`

* **Query Parameters:**

    * `posX` → Latitude do usuário
    * `posY` → Longitude do usuário
    * `page` → Número da página (default: 0)
    * `size` → Quantidade de registros por página (default: 5)

* **Response (200 OK):**

```json
{
  "pageNumber": 0,
  "pageSize": 5,
  "totalPages": 2,
  "totalElements": 7,
  "numberOfElements": 5,
  "last": false,
  "content": [
    {
      "name": "Agência 1",
      "latitude": 10.0,
      "longitude": 20.0,
      "distance": 1.2
    }
  ]
}
```

---

## ✅ Testes

* **Unitários:**

    * `CreateAgencyServiceTest`
    * `FindAgencyServiceTest`

* **Controller:**

    * `AgencyControllerTest` usando MockMvc

* **Cobertura:**

    * Pode ser gerada com IntelliJ ou JaCoCo
    * Percentual por pacote e classe

**Exemplo de cobertura:**
![Coverage Report](docs/coverage-report.png)

---

## 🗄️ Banco de Dados

* **H2 em memória** para testes
* Estrutura da tabela `tbl_agencies`:

```sql
CREATE TABLE tbl_agencies (
    id CHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    latitude DECIMAL(9,6),
    longitude DECIMAL(9,6)
);
```

---

## 🔧 Considerações Técnicas

* MapStruct para mapear DTO ↔ Entity
* Testes integrados usando H2, sem banco externo
* Cálculo de distância simplificado (pode ser aprimorado com geolocalização real)

---

## 📝 Observações

* Projeto desenvolvido como **desafio técnico Banco Santander**
* Foco em:

    * Código limpo e legível
    * Testabilidade e cobertura de testes
    * API REST bem documentada (Swagger)
    * Boas práticas Spring Boot

**Exemplo de execução no Postman:**


# Cuida+

Projeto de plataforma para intermediar cuidadores/prestadores de serviço de home care e pacientes/idosos que necessitam
dos serviços.

## Tecnologias

- Java11
- Maven
- Spring Boot
- Lombok
- Flyway
- Docker
- PostgresSQL

## Instalação

* Instale a versão 11 do Java e o Maven;
* Instale o Docker;
* Execute o seguinte comando para subir o banco de dados Postgres no Docker:

```bash
  docker run --name postgres-cuida_mais -e POSTGRES_PASSWORD=docker -e POSTGRES_USER=postgres -e POSTGRES_DB=cuida_mais -p 5432:5432 -d postgres
```

* Dentro do diretório onde o projeto foi clonado, execute:

```bash
  maven package
  mvn spring-boot:run
```

## API

#### Swagger

Para ter acesso à documentação da API, execute o projeto e acesse a url: [http://localhost:8080/swagger-ui.html]()
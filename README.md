# Colégio Plural - Setor de Inclusão

Sistema desenvolvido em Java com Spring Boot e PostgreSQL para gerenciamento do Setor de Inclusão do Colégio Plural.

## Como executar

1. Clone este repositório.
2. Crie no PostgreSQL um banco chamado `colegio_plural`.
3. Execute o arquivo `banco/colegio_plural.sql` no banco criado.
4. Configure a senha do PostgreSQL no PowerShell:

   $env:DB_PASSWORD="sua_senha"

5. Execute o projeto:

   .\mvnw.cmd spring-boot:run

6. Acesse o Swagger:

   http://localhost:8080/swagger-ui/index.html

## Tecnologias

- Java
- Spring Boot
- PostgreSQL
- DBeaver
- Swagger

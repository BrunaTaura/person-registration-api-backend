# Person Registration API - Backend

Projeto desenvolvido utilizando Java e Spring Boot para realizar o cadastro de pessoas.

O objetivo da aplicação é receber os dados de uma pessoa, validar as informações, consultar o endereço através do ViaCEP, gerar um login único e salvar o cadastro no banco de dados.

## Tecnologias utilizadas

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* H2 Database
* Lombok
* Maven
* ViaCEP

## Funcionalidades

* Cadastro de pessoa
* Validação de CPF
* Validação de e-mail
* Consulta automática de endereço pelo CEP
* Geração automática de login
* Tratamento de erros de negócio
* Persistência em banco de dados

## Regras de negócio

Ao realizar um cadastro:

* Nome é obrigatório
* CPF é obrigatório e deve ser válido
* CPF não pode estar cadastrado anteriormente
* E-mail é obrigatório
* E-mail não pode estar cadastrado anteriormente
* CEP deve existir
* O sistema gera automaticamente um login único
* O login gerado é retornado para o usuário após o cadastro

## Lógica de Geração do Login

O login é gerado automaticamente a partir do nome informado no cadastro.

Para garantir que o login siga as regras definidas no desafio, o sistema:

* Remove acentos e caracteres especiais;
* Converte todas as letras para minúsculas;
* Utiliza combinações entre nome e sobrenomes para gerar um login com exatamente 7 caracteres;
* Permite apenas letras, sem números ou espaços;
* Verifica se o login já existe na base de dados;
* Caso o login já esteja em uso, gera novas combinações até encontrar uma opção disponível.

Dessa forma, todos os logins gerados são únicos, possuem exatamente 7 caracteres e são construídos utilizando informações do nome da pessoa.

### Exemplo

Nome:

```text
Maria Silva Souza
```

Login gerado:

```text
mariasi
```

Caso esse login já exista, o sistema tenta outras combinações do próprio nome até encontrar uma opção livre.

## Como executar o projeto

### Clonar o repositório

```bash
git clone https://github.com/BrunaTaura/person-registration-api-backend.git
```

### Entrar na pasta

```bash
cd person-registration-api-backend
```

### Executar

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

A aplicação será iniciada em:

http://localhost:8080

## Endpoint disponível

### Cadastro de pessoa

POST

```http
/api/people
```

### Exemplo de requisição

```json
{
  "name": "Maria Silva",
  "cpf": "12345678909",
  "email": "maria@email.com",
  "birthDate": "1990-01-01",
  "zipCode": "01001000",
  "complement": "Apartamento 12"
}
```

### Exemplo de resposta

```json
{
  "id": 1,
  "name": "Maria Silva",
  "email": "maria@email.com",
  "login": "mariasi"
}
```

## Tratamento de erros

Exemplos:

```json
{
  "message": "CPF já cadastrado"
}
```

```json
{
  "message": "Email já cadastrado"
}
```

```json
{
  "message": "CEP não encontrado"
}
```

## Estrutura do projeto

controller

* Recebe as requisições HTTP

service

* Contém as regras de negócio

repository

* Comunicação com banco de dados

entity

* Representação das tabelas

dto

* Objetos de entrada e saída

client

* Integração com ViaCEP

exception

* Tratamento de erros

## Melhorias futuras

* Testes unitários
* Docker
* Banco PostgreSQL
* Swagger/OpenAPI
* Autenticação e autorização

## Autor

Bruna Taura Gandolfi

# 🚚 FrigoLogistica

Sistema de gerenciamento logístico desenvolvido com foco em transporte frigorífico, permitindo o controle de motoristas, veículos e operações logísticas através de uma API REST construída com Java e Spring Boot.

---

## 📌 Sobre o Projeto

O **FrigoLogistica** é uma aplicação backend criada para praticar e consolidar conhecimentos em desenvolvimento Java com Spring Boot, aplicando conceitos como:

- API REST
- Arquitetura em camadas
- DTOs
- Mappers
- Tratamento de exceções
- Persistência de dados
- Documentação com Swagger

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Swagger / OpenAPI
- Lombok
- Jakarta Validation

---

## 📂 Estrutura do Projeto

```bash
src
 ┣ main
 ┃ ┣ java
 ┃ ┃ ┗ com.frigocezar.logistica
 ┃ ┃    ┣ controller
 ┃ ┃    ┣ dto
 ┃ ┃    ┣ exceptions
 ┃ ┃    ┣ mapper
 ┃ ┃    ┣ model
 ┃ ┃    ┣ repository
 ┃ ┃    ┗ service
 ┃ ┗ resources
 ┃    ┗ application.properties
```

---

## ⚙️ Funcionalidades

### 🚛 Motoristas
- Cadastro de motoristas
- Listagem de motoristas
- Atualização de dados
- Remoção de motoristas
- Validação de campos

### 🚚 Veículos
- Cadastro de veículos
- Controle de informações dos veículos
- Associação com operações logísticas

### ❌ Tratamento de Exceções
- Exceções customizadas
- Respostas padronizadas da API
- Validações automáticas

---

## ▶️ Como Executar o Projeto

### 1. Clone o repositório

```bash
git clone https://github.com/gamaral203/FrigoLogistica.git
```

### 2. Acesse a pasta do projeto

```bash
cd FrigoLogistica
```

### 3. Configure o banco de dados

No arquivo `application.properties`, configure:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/frigologistica
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 4. Execute a aplicação

Via Maven:

```bash
./mvnw spring-boot:run
```

Ou execute diretamente pela IDE.

---

## 📖 Documentação da API

Após iniciar o projeto, acesse:

```bash
http://localhost:8080/swagger-ui/index.html
```

---

## 📬 Endpoints Principais

| Método | Endpoint |
|--------|-----------|
| GET | `/motoristas` |
| GET | `/motoristas/{id}` |
| POST | `/motoristas` |
| PUT | `/motoristas/{id}` |
| DELETE | `/motoristas/{id}` |

---

## 🧪 Exemplo de Requisição

### Cadastro de Motorista

```json
{
  "nome": "João Silva",
  "cpf": "12345678900",
  "telefone": "74999999999"
}
```

---

## 📚 Objetivo do Projeto

Este projeto foi desenvolvido com o objetivo de praticar:

- Desenvolvimento de APIs REST
- Boas práticas com Spring Boot
- Arquitetura backend
- Integração com banco de dados
- Tratamento de erros e validações

---

## 👨‍💻 Autor

Desenvolvido por Gabriel Amaral

🔗 GitHub:  
https://github.com/gamaral203

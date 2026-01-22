# 🎯 Workshop Spring Boot JPA

Projeto desenvolvido como parte do curso **Java Spring Boot** do professor **Nélio Alves (Udemy)**.

Este módulo aborda o desenvolvimento de **APIs REST** utilizando **Spring Boot** e **JPA/Hibernate**, aplicando boas práticas de **arquitetura em camadas**, persistência com **Spring Data JPA** e tratamento global de exceções.

---

## 🔷 Objetivo do Projeto

- Aprender os fundamentos de APIs REST com Spring Boot.  
- Implementar operações básicas de persistência com Spring Data JPA.  
- Aplicar arquitetura em camadas: controller, service, repository.  
- Criar um projeto didático com entidades como `User`, `Order`, `Product`, etc.  
- Padronizar respostas de erro com tratamento global via `@ControllerAdvice`.

---

## ⚙️ Tecnologias Utilizadas

- ☕ Java 17+  
- 🌱 Spring Boot  
- 🗃️ Spring Data JPA  
- 🧪 H2 Database (banco em memória)  
- 🛠️ Maven / IntelliJ IDEA para gerenciamento do projeto

---

## 📁 Estrutura do Projeto

src/  
└── entities/              # Classes de domínio (User, Order, etc.)  
└── repositories/          # Interfaces JPA para acesso ao banco  
└── services/              # Regras de negócio  
└── resources/             # Controladores REST (endpoints)  
└── exceptions/            # Tratamento global de erros  

---

## 🔗 Endpoints Principais

- `GET /users` → lista todos os usuários  
- `GET /users/{id}` → busca usuário por ID  
- `POST /users` → insere novo usuário  
- `PUT /users/{id}` → atualiza usuário existente  
- `DELETE /users/{id}` → remove usuário por ID  

---

## 📄 Exemplo de Modelo de Dados

```json
{
  "id": 1,
  "name": "Maria Brown",
  "email": "maria@gmail.com",
  "phone": "988888888"
}

⚠️ Tratamento de Erros

Quando um recurso não é encontrado, a API retorna:

{
  "timestamp": "2026-01-21T20:55:00Z",
  "status": 404,
  "error": "Resource not found",
  "message": "Resource not found with id 999",
  "path": "/users/999"
}
🚀 Como Executar

Clonar o repositório
git clone https://github.com/giuliano6943/workshop-springboot4-jpa.git

Executar o projeto

Abrir no IntelliJ IDEA ou outra IDE Java

Rodar a aplicação com:

mvn spring-boot:run

📚 Contexto Didático

Este projeto faz parte do módulo de Spring Boot e JPA do curso do Nélio Alves, considerado um dos mais completos de Java e desenvolvimento backend.

O curso cobre:

Java e OO avançado

UML

JDBC

JavaFX

Spring Boot

JPA / Hibernate

MySQL

MongoDB

E muito mais!

🙌 Créditos

🎓 Curso: Java COMPLETO: Programação Orientada a Objetos + Projetos - Nélio Alves (Udemy)

👨‍💻 Autor do projeto: Giuliano

👨‍🏫 Professor: Nélio Alves

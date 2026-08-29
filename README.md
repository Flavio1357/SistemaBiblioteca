# 📚 Sistema de Biblioteca

Sistema de gerenciamento de biblioteca desenvolvido em **Java**, com aplicação de conceitos de **Programação Orientada a Objetos**, **JDBC** e **PostgreSQL**.

> 🚧 Projeto em desenvolvimento — novas funcionalidades e melhorias serão adicionadas.

## 🎯 Objetivo

O projeto tem como objetivo desenvolver um sistema capaz de gerenciar:

* Usuários
* Livros
* Empréstimos e devoluções
* Disponibilidade de livros
* Persistência de dados em banco de dados

Além da aplicação prática de conceitos estudados durante o curso de **Ciência da Computação**.

## 🛠️ Tecnologias utilizadas

* **Java 17**
* **JDBC**
* **PostgreSQL**
* **Git / GitHub**
* **VS Code**

## 📁 Estrutura do projeto

```text
SistemaBiblioteca/
├── .vscode/
├── lib/
│   └── postgresql-42.7.13.jar
└── src/
    └── main/
        └── java/
            ├── connection/
            │   └── ConnectionFactory.java
            ├── DAO/
            │   └── UsuarioDAO.java
            ├── exception/
            ├── model/
            ├── service/
            └── Main.java
```

## 🗄️ Banco de dados

O sistema utiliza **PostgreSQL** com o banco:

```text
Biblioteca
```

A tabela de usuários possui:

```text
usuarios
├── id
├── nome
└── email
```

O ID dos usuários é gerado automaticamente pelo PostgreSQL.

## 🔌 JDBC

A conexão com o banco é realizada através da classe:

```text
ConnectionFactory
```

A senha do banco **não é armazenada diretamente no código-fonte**. O projeto utiliza a variável de ambiente:

```text
DB_PASSWORD
```

## 👤 UsuarioDAO

O `UsuarioDAO` é responsável pelo acesso aos dados dos usuários.

Atualmente possui:

* `cadastrar()` → `INSERT`
* `listarTodos()` → `SELECT`
* `atualizar()` → `UPDATE`
* `deletar()` → `DELETE`

### CRUD implementado

```text
CREATE ✅
READ   ✅
UPDATE ✅
DELETE ✅
```

## 📌 Funcionalidades do sistema

### Usuários

* Cadastro de usuários
* Listagem de usuários
* Atualização de usuários
* Exclusão de usuários

### Livros

* Cadastro de livros
* Controle de quantidade disponível

### Empréstimos

* Realização de empréstimos
* Devolução de livros
* Controle de disponibilidade
* Validação de empréstimos

> A persistência das demais entidades ainda está sendo implementada.

## 🧠 Conceitos aplicados

O projeto utiliza conceitos como:

* Programação Orientada a Objetos
* Encapsulamento
* Classes e objetos
* Enumerações
* Collections
* Tratamento de exceções
* Separação de responsabilidades
* DAO
* JDBC
* SQL
* Persistência em banco de dados

## 🚀 Próximos passos

* [x] Configurar PostgreSQL
* [x] Configurar JDBC
* [x] Criar `ConnectionFactory`
* [x] Criar `UsuarioDAO`
* [x] Implementar `INSERT`
* [x] Implementar `SELECT`
* [x] Implementar `UPDATE`
* [x] Implementar `DELETE`
* [ ] Integrar `UsuarioDAO` ao `BibliotecaService`
* [ ] Criar `LivroDAO`
* [ ] Criar `EmprestimoDAO`
* [ ] Persistir empréstimos e devoluções no banco
* [ ] Finalizar integração entre Service e DAOs
* [ ] Melhorar a aplicação principal

## 👨‍💻 Status

**Em desenvolvimento.**

Este projeto está sendo desenvolvido como projeto de estudo para praticar **Java, JDBC, SQL, PostgreSQL e desenvolvimento de aplicações orientadas a objetos**.

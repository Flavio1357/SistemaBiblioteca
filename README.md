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
            │   ├── UsuarioDAO.java
            │   └── LivroDAO.java
            ├── exception/
            ├── model/
            ├── service/
            │   └── BibliotecaService.java
            └── Main.java
```

## 🗄️ Banco de dados

O sistema utiliza **PostgreSQL** com o banco:

```text
Biblioteca
```

### Tabela `usuarios`

```text
usuarios
├── id
├── nome
└── email
```

O ID dos usuários é gerado automaticamente pelo PostgreSQL.

### Tabela `livros`

```text
livros
├── id
├── titulo
├── autor
├── ano_publicacao
└── qtd
```

O ID dos livros também é gerado automaticamente pelo PostgreSQL.

## 🔌 JDBC

A conexão com o banco é realizada através da classe:

```text
ConnectionFactory
```

A aplicação utiliza JDBC para realizar a comunicação entre o Java e o PostgreSQL.

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

O `UsuarioDAO` também recupera o ID gerado automaticamente pelo banco de dados.

## 📚 LivroDAO

O `LivroDAO` é responsável pelo acesso aos dados dos livros.

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

O cadastro, atualização e exclusão de livros já estão integrados ao `BibliotecaService`.

## 📌 Funcionalidades do sistema

### Usuários

* Cadastro de usuários ✅
* Listagem de usuários ✅
* Atualização de usuários ✅
* Exclusão de usuários ✅
* Integração com PostgreSQL através do `UsuarioDAO` ✅
* Integração do `UsuarioDAO` ao `BibliotecaService` ✅

### Livros

* Cadastro de livros ✅
* Listagem de livros ✅
* Atualização de livros ✅
* Exclusão de livros ✅
* Controle de quantidade disponível ✅
* Integração com PostgreSQL através do `LivroDAO` ✅
* Integração do `LivroDAO` ao `BibliotecaService` ✅

### Empréstimos

* Realização de empréstimos ✅
* Devolução de livros ✅
* Controle de disponibilidade ✅
* Validação de empréstimos ✅
* Persistência no banco de dados ⏳

> A lógica de empréstimos já existe no `BibliotecaService`, porém sua persistência no PostgreSQL ainda será implementada.

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
* PostgreSQL
* Persistência em banco de dados
* CRUD
* Geração automática de IDs
* `PreparedStatement`
* `ResultSet`

## 🚀 Próximos passos

* ~~Configurar PostgreSQL~~ ✅
* ~~Configurar JDBC~~ ✅
* ~~Criar `ConnectionFactory`~~ ✅
* ~~Criar `UsuarioDAO`~~ ✅
* ~~Implementar `INSERT` de usuários~~ ✅
* ~~Implementar `SELECT` de usuários~~ ✅
* ~~Implementar `UPDATE` de usuários~~ ✅
* ~~Implementar `DELETE` de usuários~~ ✅
* ~~Integrar `UsuarioDAO` ao `BibliotecaService`~~ ✅
* ~~Criar `LivroDAO`~~ ✅
* ~~Implementar CRUD de livros~~ ✅
* ~~Integrar `LivroDAO` ao `BibliotecaService`~~ ✅
* Criar `EmprestimoDAO`
* Persistir empréstimos no banco
* Persistir devoluções no banco
* Finalizar integração entre Service e DAOs
* Melhorar a aplicação principal
* Criar uma interface para o sistema

## 👨‍💻 Status

**Em desenvolvimento.**

O projeto está sendo desenvolvido como projeto de estudo para praticar **Java, JDBC, SQL, PostgreSQL e desenvolvimento de aplicações orientadas a objetos**.

Atualmente, as entidades **Usuário** e **Livro** possuem CRUD completo com persistência no PostgreSQL e integração com o `BibliotecaService`.

O próximo grande passo é implementar a persistência dos **Empréstimos** no banco de dados.

# 📚 Sistema de Biblioteca

Sistema de gerenciamento de biblioteca desenvolvido em **Java**, com persistência de dados utilizando **PostgreSQL** e comunicação com o banco através de **JDBC**.

O projeto foi desenvolvido como prática de **Programação Orientada a Objetos**, organização de código em camadas, implementação do padrão **DAO**, tratamento de exceções e integração de uma aplicação Java com banco de dados relacional.

A aplicação funciona através do terminal e permite gerenciar usuários, livros e empréstimos, mantendo os dados persistidos no PostgreSQL.

---

## 🎯 Objetivo do projeto

O objetivo do projeto é desenvolver um sistema capaz de controlar as principais operações de uma biblioteca, mantendo uma separação clara entre:

* Regras de negócio;
* Modelos das entidades;
* Acesso ao banco de dados;
* Conexão com o PostgreSQL;
* Tratamento de exceções.

Além da aplicação das estruturas de Programação Orientada a Objetos, o projeto também foi utilizado para praticar **SQL, JDBC, CRUD e persistência de dados**.

---

## ⚙️ Funcionalidades

### 👤 Usuários

O sistema permite realizar operações relacionadas aos usuários da biblioteca.

* Cadastrar usuário;
* Listar usuários;
* Buscar usuário por ID;
* Atualizar usuário;
* Deletar usuário;
* Validar nome e email;
* Impedir IDs duplicados;
* Persistir os dados no PostgreSQL.

---

### 📖 Livros

O sistema permite controlar os livros disponíveis na biblioteca.

* Cadastrar livro;
* Listar livros;
* Buscar livro por ID;
* Atualizar livro;
* Deletar livro;
* Informar título;
* Informar autor;
* Informar ano de publicação;
* Controlar quantidade disponível;
* Impedir quantidade negativa;
* Atualizar a quantidade após empréstimos e devoluções;
* Persistir os dados no PostgreSQL.

---

### 📋 Empréstimos

O sistema possui controle de empréstimos associados a usuários e livros.

* Realizar empréstimo;
* Verificar se o usuário existe;
* Verificar se o livro existe;
* Verificar disponibilidade do livro;
* Registrar a data do empréstimo;
* Registrar a data prevista para devolução;
* Registrar a data efetiva da devolução;
* Alterar o status do empréstimo;
* Impedir a devolução de um empréstimo já devolvido;
* Diminuir a quantidade disponível do livro;
* Restaurar a quantidade após a devolução;
* Persistir os empréstimos no PostgreSQL.

Os empréstimos possuem dois estados:

```text
ATIVO
DEVOLVIDO
```

---

# 🏗️ Arquitetura do projeto

O projeto foi organizado separando as responsabilidades de cada parte da aplicação.

```text
Main
 │
 ▼
BibliotecaService
 │
 ├───────────────┐
 ▼               ▼
Model            DAO
                  │
                  ▼
             PostgreSQL
```

### Fluxo geral

```text
Usuário
   ↓
Main
   ↓
BibliotecaService
   ↓
Regras de negócio
   ↓
DAO
   ↓
JDBC
   ↓
PostgreSQL
```

Essa organização evita colocar toda a lógica dentro da classe `Main` e facilita a manutenção do projeto.

---

# 📁 Estrutura do projeto

```text
SistemaBiblioteca/
│
├── .vscode/
│
├── lib/
│   └── postgresql-42.7.13.jar
│
├── src/
│   └── main/
│       └── java/
│           │
│           ├── connection/
│           │   └── ConnectionFactory.java
│           │
│           ├── DAO/
│           │   ├── UsuarioDAO.java
│           │   ├── LivroDAO.java
│           │   └── EmprestimoDAO.java
│           │
│           ├── exception/
│           │   ├── EmprestimoInvalidoException.java
│           │   ├── EmprestimoJaDevolvidoException.java
│           │   ├── IdDuplicadoException.java
│           │   ├── LivroIndisponivelException.java
│           │   ├── LivroInvalidoException.java
│           │   └── UsuarioInvalidoException.java
│           │
│           ├── model/
│           │   ├── Usuario.java
│           │   ├── Livro.java
│           │   ├── Emprestimo.java
│           │   └── StatusEmprestimo.java
│           │
│           ├── service/
│           │   └── BibliotecaService.java
│           │
│           └── Main.java
│
└── README.md
```

---

# 🧩 Camada Model

A pasta `model` contém as classes que representam as entidades utilizadas pelo sistema.

## Usuario

A classe `Usuario` representa uma pessoa cadastrada na biblioteca.

### Atributos

```text
id
nome
email
```

O `id` identifica o usuário no banco de dados.

---

## Livro

A classe `Livro` representa um livro cadastrado na biblioteca.

### Atributos

```text
id
titulo
autor
anoPublicacao
qtd
```

O atributo `qtd` representa a quantidade disponível daquele livro.

Durante um empréstimo, essa quantidade é reduzida.

Durante uma devolução, ela é restaurada.

---

## Emprestimo

A classe `Emprestimo` representa um empréstimo realizado.

### Atributos

```text
id
usuario
livro
dataEmprestimo
dataDevolucao
status
```

O empréstimo mantém referências para o `Usuario` e o `Livro` envolvidos na operação.

Também possui o método:

```java
devolver()
```

Esse método altera o status do empréstimo para:

```text
DEVOLVIDO
```

---

## StatusEmprestimo

`StatusEmprestimo` é um `enum` utilizado para representar o estado atual do empréstimo.

Possui os valores:

```java
ATIVO
DEVOLVIDO
```

O uso de `enum` evita trabalhar com valores de texto espalhados pelo sistema e deixa os estados possíveis do empréstimo bem definidos.

---

# 🗄️ Camada DAO

A pasta `DAO` contém as classes responsáveis pelo acesso ao banco de dados.

O padrão DAO foi utilizado para separar as operações SQL das regras de negócio.

---

## UsuarioDAO

Responsável pelo acesso à tabela `usuarios`.

Implementa operações de:

```text
CREATE
READ
UPDATE
DELETE
```

Métodos:

```java
cadastrar()
listarTodos()
atualizar()
deletar()
```

O cadastro utiliza `RETURN_GENERATED_KEYS` para recuperar o ID gerado automaticamente pelo PostgreSQL.

---

## LivroDAO

Responsável pelo acesso à tabela `livros`.

Métodos:

```java
cadastrar()
listarTodos()
atualizar()
deletar()
```

Também utiliza o ID gerado pelo PostgreSQL no cadastro.

Além das operações CRUD, o `LivroDAO` é utilizado pelo `BibliotecaService` para persistir alterações na quantidade disponível dos livros.

---

## EmprestimoDAO

Responsável pelo acesso à tabela `emprestimos`.

Métodos:

```java
cadastrar()
listarTodos()
atualizar()
deletar()
```

O `EmprestimoDAO` também realiza consultas relacionando:

```text
usuarios
        ↓
emprestimos
        ↓
livros
```

Dessa forma, ao carregar um empréstimo do banco, o sistema consegue reconstruir o `Usuario` e o `Livro` associados.

---

# 🔌 ConnectionFactory

A classe:

```text
connection/ConnectionFactory.java
```

é responsável por criar as conexões JDBC com o PostgreSQL.

A aplicação utiliza uma conexão semelhante a:

```text
jdbc:postgresql://localhost:5432/Biblioteca
```

A `ConnectionFactory` centraliza a criação da conexão, evitando repetir o código de conexão em cada DAO.

---

# 🧠 BibliotecaService

A classe `BibliotecaService` concentra as principais regras de negócio da aplicação.

Ela trabalha com:

```text
UsuarioDAO
LivroDAO
EmprestimoDAO
```

e mantém as listas utilizadas pela aplicação:

```text
usuarios
livros
emprestimos
```

Ao iniciar o sistema, o `BibliotecaService` carrega os dados existentes no PostgreSQL.

---

## Cadastro de usuários

Antes de cadastrar um usuário, o serviço verifica:

* Se o nome foi informado;
* Se o email foi informado;
* Se o ID não está duplicado.

Depois da validação, o `UsuarioDAO` realiza o cadastro no PostgreSQL.

---

## Cadastro de livros

Antes de cadastrar um livro, o serviço verifica:

* Se o título foi informado;
* Se o autor foi informado;
* Se a quantidade não é negativa;
* Se o ID não está duplicado.

Depois da validação, o `LivroDAO` realiza o cadastro.

---

## Realização de empréstimo

Para realizar um empréstimo, o sistema:

1. Localiza o usuário;
2. Localiza o livro;
3. Verifica se ambos existem;
4. Verifica a quantidade disponível;
5. Cria o objeto `Emprestimo`;
6. Registra a data do empréstimo;
7. Define a data prevista para devolução;
8. Reduz a quantidade do livro;
9. Atualiza o livro no PostgreSQL;
10. Salva o empréstimo no PostgreSQL.

O empréstimo recebe seu ID automaticamente através do PostgreSQL.

---

## Devolução

Ao devolver um livro, o sistema:

1. Verifica se o empréstimo já foi devolvido;
2. Altera o status para `DEVOLVIDO`;
3. Registra a data de devolução;
4. Aumenta a quantidade disponível do livro;
5. Atualiza o livro no PostgreSQL;
6. Atualiza o empréstimo no PostgreSQL.

Caso o empréstimo já esteja devolvido, é lançada uma exceção específica.

---

# ⚠️ Exceções personalizadas

O projeto possui exceções específicas para diferentes situações.

## UsuarioInvalidoException

Utilizada quando os dados necessários de um usuário não são válidos.

---

## LivroInvalidoException

Utilizada quando os dados de um livro não são válidos.

Exemplo:

```text
Quantidade negativa
Título vazio
Autor vazio
```

---

## LivroIndisponivelException

Utilizada quando não existem exemplares disponíveis para empréstimo.

---

## EmprestimoInvalidoException

Utilizada quando um empréstimo não pode ser realizado.

Exemplos:

```text
Usuário não encontrado
Livro não encontrado
```

---

## EmprestimoJaDevolvidoException

Utilizada quando existe uma tentativa de devolver novamente um empréstimo que já possui o status:

```text
DEVOLVIDO
```

---

## IdDuplicadoException

Utilizada quando ocorre tentativa de cadastrar uma entidade com um ID já existente.

---

# 🐘 Banco de dados

O sistema utiliza:

```text
PostgreSQL
```

Banco:

```text
Biblioteca
```

Tabelas:

```text
usuarios
livros
emprestimos
```

---

## Relacionamento entre as tabelas

A tabela `emprestimos` possui duas chaves estrangeiras:

```text
id_usuario
id_livro
```

Relacionando:

```text
usuarios
   │
   │ 1:N
   ▼
emprestimos
   ▲
   │ N:1
   │
livros
```

Um usuário pode possuir vários empréstimos.

Um livro pode aparecer em vários empréstimos ao longo do tempo.

---

# 🗃️ SQL utilizado

## Tabela usuarios

```sql
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL
);
```

---

## Tabela livros

```sql
CREATE TABLE livros (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(150) NOT NULL,
    ano_publicacao INT NOT NULL,
    qtd INT NOT NULL
);
```

---

## Tabela emprestimos

```sql
CREATE TABLE emprestimos (
    id SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_livro INT NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE NULL,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY (id_livro) REFERENCES livros(id)
);
```

---

# 🔗 JDBC

O projeto utiliza o driver oficial JDBC do PostgreSQL.

Arquivo utilizado:

```text
postgresql-42.7.13.jar
```

Localização:

```text
lib/postgresql-42.7.13.jar
```

O JDBC permite que a aplicação Java execute comandos SQL e receba os resultados do PostgreSQL.

---

# 🔄 Exemplo do fluxo de um empréstimo

Quando um usuário solicita um livro:

```text
Main
 │
 ▼
BibliotecaService
 │
 ├── verifica usuário
 │
 ├── verifica livro
 │
 ├── verifica quantidade
 │
 ├── cria empréstimo
 │
 └── diminui quantidade
 │
 ▼
LivroDAO
 │
 ▼
PostgreSQL
```

Depois:

```text
BibliotecaService
 │
 ▼
EmprestimoDAO
 │
 ▼
PostgreSQL
```

O registro do empréstimo é persistido na tabela:

```text
emprestimos
```

---

# 🔄 Exemplo do fluxo de devolução

```text
Main
 │
 ▼
BibliotecaService
 │
 ├── verifica status
 │
 ├── altera para DEVOLVIDO
 │
 ├── registra data
 │
 └── aumenta quantidade
 │
 ├───────────────┐
 ▼               ▼
LivroDAO      EmprestimoDAO
 │               │
 └───────┬───────┘
         ▼
     PostgreSQL
```

---

# 🖥️ Main

A classe `Main` é responsável por executar e demonstrar o funcionamento do sistema através do terminal.

Ela inicializa o:

```java
BibliotecaService
```

e apresenta informações sobre:

* Usuários;
* Livros;
* Empréstimos;
* Novo empréstimo;
* Devolução.

A aplicação não utiliza interface gráfica. O projeto foi mantido como uma aplicação Java executada pelo terminal.

---

# 🚀 Como executar

## 1. Pré-requisitos

É necessário ter instalado:

* Java;
* PostgreSQL;
* VS Code ou outra IDE compatível;
* Git, caso queira clonar o projeto.

Verifique a instalação do Java:

```bash
java --version
```

Verifique o PostgreSQL:

```bash
psql --version
```

---

## 2. Clonar o projeto

```bash
git clone URL_DO_REPOSITORIO
```

Entre na pasta:

```bash
cd SistemaBiblioteca
```

---

## 3. Criar o banco

No PostgreSQL, crie o banco:

```text
Biblioteca
```

Depois execute os comandos SQL presentes neste README para criar as tabelas.

---

## 4. Configurar a conexão

A configuração da conexão está em:

```text
src/main/java/connection/ConnectionFactory.java
```

Configure o acesso ao PostgreSQL de acordo com o seu ambiente.

A aplicação utiliza:

```text
Banco: Biblioteca
Usuário: postgres
Host: localhost
Porta: 5432
```

> **Importante:** não publique senha do PostgreSQL no GitHub. Utilize variáveis de ambiente ou outra forma segura de configuração.

---

## 5. Configurar o JDBC

O driver:

```text
postgresql-42.7.13.jar
```

deve estar dentro de:

```text
lib/
```

O projeto já possui o arquivo utilizado durante o desenvolvimento.

---

## 6. Executar

Execute:

```text
src/main/java/Main.java
```

A aplicação será executada pelo terminal.

---

# 🧪 Exemplo de execução

Uma execução do sistema pode apresentar:

```text
=================================
       SISTEMA DE BIBLIOTECA
=================================

Usuários cadastrados: 1
Livros cadastrados: 1
Empréstimos registrados: 2

----------- USUÁRIOS -----------

ID: 1 | Nome: Joao | Email: ...

------------- LIVROS ------------

ID: 1 | Título: Clean Code | Autor: Robert C. Martin | Quantidade: 4

--------- NOVO EMPRÉSTIMO ---------

ID: 4
Usuário: Joao
Livro: Clean Code
Data do empréstimo: 2026-09-06
Data prevista para devolução: 2026-09-13
Status: ATIVO

--------- DEVOLUÇÃO ---------

Livro: Clean Code
Data de devolução: 2026-09-06
Status: DEVOLVIDO

=================================
        SISTEMA FINALIZADO
=================================
```

---

# 🛠️ Conceitos aplicados

O desenvolvimento do projeto envolveu diversos conceitos importantes da programação:

### Java

* Classes;
* Objetos;
* Encapsulamento;
* Getters e setters;
* Enum;
* Listas;
* `ArrayList`;
* `LocalDate`;
* Tratamento de exceções;
* Exceções personalizadas;
* Organização em pacotes.

### Banco de dados

* PostgreSQL;
* SQL;
* CRUD;
* `SELECT`;
* `INSERT`;
* `UPDATE`;
* `DELETE`;
* `PRIMARY KEY`;
* `FOREIGN KEY`;
* Relacionamentos;
* `SERIAL`.

### JDBC

* `Connection`;
* `PreparedStatement`;
* `ResultSet`;
* `SQLException`;
* `RETURN_GENERATED_KEYS`;
* Conexão Java/PostgreSQL.

### Arquitetura

* Separação entre Model, DAO e Service;
* Padrão DAO;
* Separação de responsabilidades;
* Regras de negócio centralizadas no Service.

---

# 📌 Estado atual do projeto

Atualmente o projeto possui:

* [x] Modelos de usuário, livro e empréstimo
* [x] Enum de status do empréstimo
* [x] Exceções personalizadas
* [x] ConnectionFactory
* [x] PostgreSQL configurado
* [x] JDBC configurado
* [x] CRUD de usuários
* [x] CRUD de livros
* [x] CRUD de empréstimos
* [x] UsuarioDAO integrado ao Service
* [x] LivroDAO integrado ao Service
* [x] EmprestimoDAO integrado ao Service
* [x] Persistência de empréstimos
* [x] Persistência de devoluções
* [x] Controle da quantidade de livros
* [x] Main para demonstração do sistema

---

# 📈 Possíveis melhorias futuras

O projeto pode ser expandido futuramente com funcionalidades como:

* Interface gráfica;
* Menu interativo no terminal;
* Pesquisa de livros;
* Pesquisa de usuários;
* Listagem de empréstimos ativos;
* Histórico de empréstimos;
* Controle de usuários com mais informações;
* Relatórios;
* Validações adicionais;
* Testes automatizados;
* Uso de transações para operações que envolvem múltiplas alterações no banco.

Essas funcionalidades não fazem parte da versão atual e podem ser implementadas em versões futuras.

---

# 👨‍💻 Autor: Flávio da Silva Franca Filho

Projeto desenvolvido como parte dos estudos em **Ciência da Computação**, com foco em Java, Programação Orientada a Objetos, JDBC, PostgreSQL e desenvolvimento de aplicações com persistência de dados.

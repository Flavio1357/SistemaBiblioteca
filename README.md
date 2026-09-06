# Sistema de Biblioteca

Sistema de gerenciamento de biblioteca desenvolvido em Java, com persistência de dados utilizando PostgreSQL e JDBC.

O projeto foi desenvolvido com foco em Programação Orientada a Objetos, organização em camadas e operações de cadastro, atualização, exclusão, empréstimo e devolução de livros.

## Tecnologias utilizadas

- Java
- JDBC
- PostgreSQL
- SQL
- Git
- GitHub
- VS Code

## Funcionalidades

### Usuários

- Cadastrar usuário
- Listar usuários
- Atualizar usuário
- Deletar usuário
- Buscar usuário por ID

### Livros

- Cadastrar livro
- Listar livros
- Atualizar livro
- Deletar livro
- Buscar livro por ID
- Controle da quantidade disponível

### Empréstimos

- Realizar empréstimo
- Verificar disponibilidade do livro
- Registrar data do empréstimo
- Registrar data prevista para devolução
- Realizar devolução
- Registrar data de devolução
- Alterar status do empréstimo
- Persistir empréstimos no PostgreSQL
- Persistir alterações na quantidade de livros

## Estrutura do projeto

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

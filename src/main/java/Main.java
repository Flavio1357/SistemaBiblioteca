package main.java;

import main.java.model.Usuario;
import main.java.model.Livro;
import main.java.service.BibliotecaService;
import main.java.model.Emprestimo;
import java.time.LocalDate;
import main.java.exception.LivroIndisponivelException;
import main.java.exception.UsuarioInvalidoException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Sistema de Biblioteca");

        Usuario usuario = new Usuario(1, "Flávio", "flavio@email.com");
        Livro livro = new Livro(
            1,
            "Clean Code",
            "Robert C. Martin",
            2008,
            5
        );

        BibliotecaService biblioteca = new BibliotecaService();
        try {
            biblioteca.cadastrarUsuario(usuario);
            System.out.println("Usuário Cadastrado com sucesso.");
        } catch (UsuarioInvalidoException e) {
            System.out.println(e.getMessage());
        }
        biblioteca.cadastrarLivro(livro);


        Emprestimo emprestimo = new Emprestimo(
            1,
            usuario,
            livro,
            LocalDate.now(),
            LocalDate.now().plusDays(7)
        );
        try {
            biblioteca.realizarEmprestimo(emprestimo);

            System.out.println("Empréstimo realizado com sucesso!");
            System.out.println("Livros disponiveis: " + livro.getQtd());
        } catch (LivroIndisponivelException e){
            System.out.println(e.getMessage());
        }

        biblioteca.devolverLivro(emprestimo);

System.out.println("Status do empréstimo: "
        + emprestimo.getStatus());

System.out.println("Livros disponíveis após devolução: "
        + livro.getQtd());

        biblioteca.devolverLivro(emprestimo);

System.out.println("Livros disponíveis após segunda devolução: "
        + livro.getQtd());

        System.out.println("Empréstimos realizados: " + biblioteca.getEmprestimos().size());

        System.out.println("Livros disponíveis: " + livro.getQtd());
        System.out.println("Usuários cadastrados: " + biblioteca.getUsuarios().size());
        System.out.println("Livros cadastrados: " + biblioteca.getLivros().size());


    }
}

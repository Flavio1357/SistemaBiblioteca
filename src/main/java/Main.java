package main.java;

import main.java.model.Usuario;
import main.java.model.Livro;
import main.java.model.Emprestimo;
import main.java.service.BibliotecaService;
import main.java.exception.IdDuplicadoException;
import main.java.exception.LivroIndisponivelException;
import main.java.exception.UsuarioInvalidoException;
import main.java.exception.LivroInvalidoException;
import main.java.exception.EmprestimoInvalidoException;

public class Main {

    public static void main(String[] args) {

        System.out.println("Sistema de Biblioteca");

        BibliotecaService biblioteca = new BibliotecaService();

        Usuario usuario = new Usuario(
            1,
            "Flávio",
            "flavio@email.com"
        );

        Livro livro = new Livro(
            1,
            "Clean Code",
            "Robert C. Martin",
            2008,
            5
        );

        try {
            biblioteca.cadastrarUsuario(usuario);
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (UsuarioInvalidoException | IdDuplicadoException e) {
            System.out.println(e.getMessage());
        }

        try {
            biblioteca.cadastrarLivro(livro);
            System.out.println("Livro cadastrado com sucesso!");
        } catch (LivroInvalidoException | IdDuplicadoException e) {
            System.out.println(e.getMessage());
        }

        try {
            Emprestimo emprestimo = biblioteca.realizarEmprestimo(1, 1);

            System.out.println("Empréstimo realizado com sucesso!");
            System.out.println("Usuário: " + emprestimo.getUsuario().getNome());
            System.out.println("Livro: " + emprestimo.getLivro().getTitulo());
            System.out.println("Livros disponíveis: "
                    + emprestimo.getLivro().getQtd());

        } catch (LivroIndisponivelException | EmprestimoInvalidoException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Usuários cadastrados: "
                + biblioteca.getUsuarios().size());

        System.out.println("Livros cadastrados: "
                + biblioteca.getLivros().size());

        System.out.println("Empréstimos realizados: "
                + biblioteca.getEmprestimos().size());
    }
}
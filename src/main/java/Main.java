package main.java;

import main.java.model.Usuario;
import main.java.model.Livro;
import main.java.service.BibliotecaService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Sistema de Biblioteca");

        Usuario usuario = new Usuario(1, "Flavio", "flavio@email.com");
        Livro livro = new Livro(
            1,
            "Clean Code",
            "Robert C. Martin",
            2008,
            5
        );

        BibliotecaService biblioteca = new BibliotecaService();
        biblioteca.cadastrarUsuario(usuario);
        biblioteca.cadastrarLivro(livro);
        System.out.println("Usuários cadastrados: " + biblioteca.getUsuarios().size());
        System.out.println("Livros cadastrados: " + biblioteca.getLivros().size());


    }
}

package main.java;

import main.java.model.Usuario;
import main.java.model.Livro;

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

        System.out.println("Usuário: " + usuario.getNome());
        System.out.println("Email: " + usuario.getEmail());

        System.out.println();

        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Autor: " + livro.getAutor());
        System.out.println("Ano: " + livro.getAnoPublicacao());
        System.out.println("Quantidade: " + livro.getQtd());
    }
}

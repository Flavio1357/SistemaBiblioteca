package main.java;

import main.java.model.Usuario;

public class Main {
    public static void main(String[] args) {
        System.out.println("Sistema de Biblioteca");

        Usuario usuario = new Usuario(1, "Flavio", "flavio@email.com");
        System.out.println(usuario.getNome());
        System.out.println(usuario.getEmail());
    }
}

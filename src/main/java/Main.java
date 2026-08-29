package main.java;

import java.util.List;

import main.java.model.Usuario;
import main.java.service.BibliotecaService;

public class Main {
public static void main(String[] args) {

    try {

        // Cria o Service e carrega os usuários do banco
        BibliotecaService biblioteca = new BibliotecaService();

        System.out.println("Usuários carregados do banco:");

        List<Usuario> usuarios = biblioteca.getUsuarios();

        for (Usuario u : usuarios) {
            System.out.println(
                u.getId() + " - " +
                u.getNome() + " - " +
                u.getEmail()
            );
        }

        // =====================================
        // TESTE DE CADASTRO PELO SERVICE
        // =====================================

        Usuario novoUsuario = new Usuario(
            0,
            "Teste Git Pull",
            "testegitpull@email.com"
        );

        biblioteca.cadastrarUsuario(novoUsuario);

        System.out.println("\nUsuário cadastrado pelo Service!");
        System.out.println("ID gerado: " + novoUsuario.getId());

        // =====================================
        // LISTAR APÓS CADASTRO
        // =====================================

        usuarios = biblioteca.getUsuarios();

        System.out.println("\nUsuários após cadastro:");

        for (Usuario u : usuarios) {
            System.out.println(
                u.getId() + " - " +
                u.getNome() + " - " +
                u.getEmail()
            );
        }

    } catch (Exception e) {

        System.out.println("\nErro durante o teste:");
        e.printStackTrace();
        }
    }

}

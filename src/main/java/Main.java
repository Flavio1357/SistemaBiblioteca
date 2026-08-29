package main.java;

import java.util.List;

import main.java.model.Usuario;
import main.java.service.BibliotecaService;

public class Main {
public static void main(String[] args) {

    try {

        // Cria o Service e carrega os usuários do banco
        BibliotecaService biblioteca = new BibliotecaService();

        // Cria um novo usuário
        Usuario usuario = new Usuario(
            0,
            "Pedro",
            "pedro@email.com"
        );

        // Cadastra através do Service
        biblioteca.cadastrarUsuario(usuario);

        System.out.println("Usuário cadastrado pelo Service!");
        System.out.println("ID gerado: " + usuario.getId());

        // Lista os usuários
        List<Usuario> usuarios = biblioteca.getUsuarios();

        System.out.println("\nUsuários:");

        for (Usuario u : usuarios) {
            System.out.println(
                u.getId() + " - " +
                u.getNome() + " - " +
                u.getEmail()
            );
        }

    } catch (Exception e) {

        System.out.println("Erro:");
        e.printStackTrace();
        }
    }
}

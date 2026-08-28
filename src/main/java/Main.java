package main.java;

import java.util.List;

import main.java.DAO.UsuarioDAO;
import main.java.model.Usuario;

public class Main {

    public static void main(String[] args) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {

            // =====================================
            // 1. CADASTRAR
            // =====================================

            Usuario usuario = new Usuario(
                0,
                "Carlos",
                "carlos@email.com"
            );

            usuarioDAO.cadastrar(usuario);

            System.out.println("Usuário cadastrado com sucesso!");
            System.out.println("ID gerado: " + usuario.getId());


            // =====================================
            // 2. LISTAR
            // =====================================

            List<Usuario> usuarios = usuarioDAO.listarTodos();

            System.out.println("\nUsuários cadastrados:");

            for (Usuario u : usuarios) {
                System.out.println(
                    u.getId() + " - " +
                    u.getNome() + " - " +
                    u.getEmail()
                );
            }


            // =====================================
            // 3. ATUALIZAR
            // =====================================

            Usuario usuarioAtualizado = new Usuario(
                usuario.getId(),
                "Carlos Atualizado",
                "carlos.atualizado@email.com"
            );

            usuarioDAO.atualizar(usuarioAtualizado);

            System.out.println("\nUsuário atualizado com sucesso!");


            // =====================================
            // 4. LISTAR APÓS ATUALIZAÇÃO
            // =====================================

            usuarios = usuarioDAO.listarTodos();

            System.out.println("\nUsuários após atualização:");

            for (Usuario u : usuarios) {
                System.out.println(
                    u.getId() + " - " +
                    u.getNome() + " - " +
                    u.getEmail()
                );
            }


            // =====================================
            // 5. DELETAR
            // =====================================

            usuarioDAO.deletar(usuario.getId());

            System.out.println("\nUsuário deletado com sucesso!");


            // =====================================
            // 6. LISTAR APÓS EXCLUSÃO
            // =====================================

            usuarios = usuarioDAO.listarTodos();

            System.out.println("\nUsuários após exclusão:");

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
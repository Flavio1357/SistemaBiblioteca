package main.java;

import main.java.model.Livro;
import main.java.service.BibliotecaService;

public class Main {

    public static void main(String[] args) {

        try {

            BibliotecaService biblioteca = new BibliotecaService();

            // =========================
            // 1. LISTAR LIVROS
            // =========================

            System.out.println("=== LIVROS ANTES DA ATUALIZAÇÃO ===");

            for (Livro livro : biblioteca.getLivros()) {
                System.out.println(
                    livro.getId() + " - " +
                    livro.getTitulo() + " - " +
                    livro.getAutor() + " - " +
                    livro.getAnoPublicacao() + " - " +
                    livro.getQtd() + " disponíveis"
                );
            }

            // =========================
            // 2. ATUALIZAR LIVRO
            // =========================

            Livro livro = biblioteca.buscarLivroPorId(4);

            if (livro != null) {

                livro.setTitulo("Java Efetivo - 3ª Edição");
                livro.setAutor("Joshua Bloch");
                livro.setAnoPublicacao(2018);
                livro.setQtd(10);

                biblioteca.atualizarLivro(livro);

                System.out.println("\nLivro atualizado com sucesso!");

            } else {
                System.out.println("\nLivro com ID 4 não encontrado.");
            }

            // =========================
            // 3. MOSTRAR APÓS ATUALIZAR
            // =========================

            System.out.println("\n=== LIVROS APÓS A ATUALIZAÇÃO ===");

            for (Livro l : biblioteca.getLivros()) {
                System.out.println(
                    l.getId() + " - " +
                    l.getTitulo() + " - " +
                    l.getAutor() + " - " +
                    l.getAnoPublicacao() + " - " +
                    l.getQtd() + " disponíveis"
                );
            }

            // =========================
            // 4. DELETAR LIVRO
            // =========================

            biblioteca.deletarLivro(4);

            System.out.println("\nLivro deletado com sucesso!");

            // =========================
            // 5. MOSTRAR APÓS DELETAR
            // =========================

            System.out.println("\n=== LIVROS APÓS A EXCLUSÃO ===");

            for (Livro l : biblioteca.getLivros()) {
                System.out.println(
                    l.getId() + " - " +
                    l.getTitulo() + " - " +
                    l.getAutor() + " - " +
                    l.getAnoPublicacao() + " - " +
                    l.getQtd() + " disponíveis"
                );
            }

        } catch (Exception e) {

            System.out.println("\nErro:");
            e.printStackTrace();
        }
    }
}
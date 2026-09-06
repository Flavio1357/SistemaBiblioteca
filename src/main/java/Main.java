package main.java;

import main.java.model.Emprestimo;
import main.java.service.BibliotecaService;

public class Main {

	public static void main(String[] args) {

		BibliotecaService service = new BibliotecaService();

		System.out.println("=================================");
		System.out.println("       SISTEMA DE BIBLIOTECA");
		System.out.println("=================================");

		System.out.println();
		System.out.println("Usuários cadastrados: " + service.getUsuarios().size());
		System.out.println("Livros cadastrados: " + service.getLivros().size());
		System.out.println("Empréstimos registrados: " + service.getEmprestimos().size());

		System.out.println();
		System.out.println("----------- USUÁRIOS -----------");

		service.getUsuarios().forEach(usuario -> {
			System.out.println(
				"ID: " + usuario.getId() +
				" | Nome: " + usuario.getNome() +
				" | Email: " + usuario.getEmail()
			);
		});

		System.out.println();
		System.out.println("------------- LIVROS ------------");

		service.getLivros().forEach(livro -> {
			System.out.println(
				"ID: " + livro.getId() +
				" | Título: " + livro.getTitulo() +
				" | Autor: " + livro.getAutor() +
				" | Quantidade: " + livro.getQtd()
			);
		});

		if (!service.getUsuarios().isEmpty() && !service.getLivros().isEmpty()) {

			int idUsuario = service.getUsuarios().get(0).getId();
			int idLivro = service.getLivros().get(0).getId();

			try {

				Emprestimo emprestimo =
					service.realizarEmprestimo(idUsuario, idLivro);

				System.out.println();
				System.out.println("--------- NOVO EMPRÉSTIMO ---------");
				System.out.println("ID: " + emprestimo.getId());
				System.out.println("Usuário: " + emprestimo.getUsuario().getNome());
				System.out.println("Livro: " + emprestimo.getLivro().getTitulo());
				System.out.println("Data do empréstimo: " + emprestimo.getDataEmprestimo());
				System.out.println("Data prevista para devolução: " + emprestimo.getDataDevolucao());
				System.out.println("Status: " + emprestimo.getStatus());

				service.devolverLivro(emprestimo);

				System.out.println();
				System.out.println("--------- DEVOLUÇÃO ---------");
				System.out.println("Livro: " + emprestimo.getLivro().getTitulo());
				System.out.println("Data de devolução: " + emprestimo.getDataDevolucao());
				System.out.println("Status: " + emprestimo.getStatus());

			} catch (Exception e) {
				System.out.println();
				System.out.println("Erro: " + e.getMessage());
			}
		}

		System.out.println();
		System.out.println("=================================");
		System.out.println("        SISTEMA FINALIZADO");
		System.out.println("=================================");
	}
}
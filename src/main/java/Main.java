package main.java;

import main.java.model.Emprestimo;
import main.java.service.BibliotecaService;

public class Main {

	public static void main(String[] args) {

		BibliotecaService service = new BibliotecaService();

		System.out.println("Usuários: " + service.getUsuarios().size());
		System.out.println("Livros: " + service.getLivros().size());
		System.out.println("Empréstimos: " + service.getEmprestimos().size());

		if (!service.getUsuarios().isEmpty() && !service.getLivros().isEmpty()) {

			int idUsuario = service.getUsuarios().get(0).getId();
			int idLivro = service.getLivros().get(0).getId();

			try {

				Emprestimo emprestimo =
					service.realizarEmprestimo(idUsuario, idLivro);

				System.out.println();
				System.out.println("Empréstimo realizado!");
				System.out.println("ID do empréstimo: " + emprestimo.getId());
				System.out.println("Livro: " + emprestimo.getLivro().getTitulo());
				System.out.println("Usuário: " + emprestimo.getUsuario().getNome());
				System.out.println("Status: " + emprestimo.getStatus());

				try {

					service.devolverLivro(emprestimo);

					System.out.println();
					System.out.println("Empréstimo devolvido!");
					System.out.println("Status após devolução: " + emprestimo.getStatus());
					System.out.println("Data de devolução: " + emprestimo.getDataDevolucao());

				} catch (Exception e) {
					System.out.println("Erro ao devolver: " + e.getMessage());
				}

			} catch (Exception e) {
				System.out.println("Erro ao realizar empréstimo: " + e.getMessage());
			}

		} else {
			System.out.println("É necessário ter pelo menos 1 usuário e 1 livro.");
		}
	}
}
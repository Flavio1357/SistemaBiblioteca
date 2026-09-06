package main.java;

import main.java.DAO.EmprestimoDAO;

public class Main {

	public static void main(String[] args) {

		try {

			EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

			emprestimoDAO.deletar(1);

			System.out.println("Empréstimo deletado com sucesso!");

		} catch (Exception e) {

			System.out.println("Erro:");
			e.printStackTrace();
		}
	}
}
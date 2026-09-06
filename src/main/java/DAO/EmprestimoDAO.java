package main.java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.connection.ConnectionFactory;
import main.java.model.Emprestimo;

public class EmprestimoDAO {

	public void cadastrar(Emprestimo emprestimo) throws SQLException {

		String sql = "INSERT INTO emprestimos " +
					 "(id_usuario, id_livro, data_emprestimo, data_devolucao, status) " +
					 "VALUES (?, ?, ?, ?, ?)";

		try (Connection conexao = ConnectionFactory.getConnection();
			 PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setInt(1, emprestimo.getUsuario().getId());
			stmt.setInt(2, emprestimo.getLivro().getId());
			stmt.setDate(3, java.sql.Date.valueOf(emprestimo.getDataEmprestimo()));

			if (emprestimo.getDataDevolucao() != null) {
				stmt.setDate(4, java.sql.Date.valueOf(emprestimo.getDataDevolucao()));
			} else {
				stmt.setNull(4, java.sql.Types.DATE);
			}

			stmt.setString(5, emprestimo.getStatus().name());

			stmt.executeUpdate();
		}
	}

	public List<Emprestimo> listarTodos() throws SQLException {

		List<Emprestimo> emprestimos = new ArrayList<>();

		String sql = "SELECT id, id_usuario, id_livro, data_emprestimo, " +
					 "data_devolucao, status FROM emprestimos";

		try (Connection conexao = ConnectionFactory.getConnection();
			 PreparedStatement stmt = conexao.prepareStatement(sql);
			 ResultSet resultado = stmt.executeQuery()) {

			while (resultado.next()) {

				Emprestimo emprestimo = new Emprestimo(
					resultado.getInt("id"),
					null,
					null,
					resultado.getDate("data_emprestimo").toLocalDate(),
					resultado.getObject("data_devolucao") != null
						? resultado.getDate("data_devolucao").toLocalDate()
						: null
				);

				emprestimos.add(emprestimo);
			}
		}

		return emprestimos;
	}

    public void atualizar(Emprestimo emprestimo) throws SQLException{

        String sql = "UPDATE emprestimos SET " +
				 "id_usuario = ?, " +
				 "id_livro = ?, " +
				 "data_emprestimo = ?, " +
				 "data_devolucao = ?, " +
				 "status = ? " +
				 "WHERE id = ?";

        try (Connection  conexao = ConnectionFactory.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql)){
            
            stmt.setInt(1, emprestimo.getUsuario().getId());
            stmt.setInt(2, emprestimo.getLivro().getId());
            stmt.setDate(3, java.sql.Date.valueOf(emprestimo.getDataEmprestimo()));
            
            if(emprestimo.getDataDevolucao() != null){
                stmt.setDate(4, java.sql.Date.valueOf(emprestimo.getDataDevolucao()));

            } else{
                stmt.setNull(4, java.sql.Types.DATE);
            }

            stmt.setString(5, emprestimo.getStatus().name());
            stmt.setInt(6, emprestimo.getId());

            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException{
        String sql = "DELETE FROM emprestimos WHERE id = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }
}
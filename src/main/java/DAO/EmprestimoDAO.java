package main.java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.connection.ConnectionFactory;
import main.java.model.Emprestimo;
import main.java.model.Livro;
import main.java.model.Usuario;

public class EmprestimoDAO {

	public void cadastrar(Emprestimo emprestimo) throws SQLException {

		String sql = "INSERT INTO emprestimos " +
					 "(id_usuario, id_livro, data_emprestimo, data_devolucao, status) " +
					 "VALUES (?, ?, ?, ?, ?)";

		try (Connection conexao = ConnectionFactory.getConnection();
	 		PreparedStatement stmt = conexao.prepareStatement(
		 		sql,
		 		java.sql.Statement.RETURN_GENERATED_KEYS
	 		)) {

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

			try (ResultSet resultado = stmt.getGeneratedKeys()) {
				if (resultado.next()) {
					emprestimo.setId(resultado.getInt(1));
				}
			}
		}
	}

	public List<Emprestimo> listarTodos() throws SQLException {

	List<Emprestimo> emprestimos = new ArrayList<>();

	String sql = "SELECT e.id, e.data_emprestimo, e.data_devolucao, e.status, " +
				 "u.id AS usuario_id, u.nome, u.email, " +
				 "l.id AS livro_id, l.titulo, l.autor, l.ano_publicacao, l.qtd " +
				 "FROM emprestimos e " +
				 "JOIN usuarios u ON e.id_usuario = u.id " +
				 "JOIN livros l ON e.id_livro = l.id";

	try (Connection conexao = ConnectionFactory.getConnection();
		 PreparedStatement stmt = conexao.prepareStatement(sql);
		 ResultSet resultado = stmt.executeQuery()) {

		while (resultado.next()) {

			Usuario usuario = new Usuario(
				resultado.getInt("usuario_id"),
				resultado.getString("nome"),
				resultado.getString("email")
			);

			Livro livro = new Livro(
				resultado.getInt("livro_id"),
				resultado.getString("titulo"),
				resultado.getString("autor"),
				resultado.getInt("ano_publicacao"),
				resultado.getInt("qtd")
			);

			Emprestimo emprestimo = new Emprestimo(
				resultado.getInt("id"),
				usuario,
				livro,
				resultado.getDate("data_emprestimo").toLocalDate(),
				resultado.getObject("data_devolucao") != null
					? resultado.getDate("data_devolucao").toLocalDate()
					: null
			);

			if (resultado.getString("status").equals("DEVOLVIDO")) {
				emprestimo.devolver();
			}

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
package main.java.DAO;

import main.java.connection.ConnectionFactory;
import main.java.model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void cadastrar(Livro livro) throws SQLException {

        String sql = "INSERT INTO livros (titulo, autor, ano_publicacao, qtd) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql,java.sql.Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setInt(4, livro.getQtd());

            stmt.executeUpdate();

            try (ResultSet resultado = stmt.getGeneratedKeys()) {

                if (resultado.next()) {
                    livro.setId(resultado.getInt(1));
                }
            }
        }
    }

    public List<Livro> listarTodos() throws SQLException {

        List<Livro> livros = new ArrayList<>();

        String sql = "SELECT id, titulo, autor, ano_publicacao, qtd FROM livros";

        try (Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {

                Livro livro = new Livro(
                resultado.getInt("id"),
                resultado.getString("titulo"),
                resultado.getString("autor"),
                resultado.getInt("ano_publicacao"),
                resultado.getInt("qtd")
            );

                livros.add(livro);
            }
        }

        return livros;
    }

    public void atualizar(Livro livro) throws SQLException {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, ano_publicacao = ?, qtd = ? WHERE id = ?";

        try (Connection conexao = ConnectionFactory.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setInt(4, livro.getQtd());
            stmt.setInt(5, livro.getId());

            stmt.executeUpdate();

        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM livros WHERE id = ?";

        try (Connection conexao = ConnectionFactory.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }
}

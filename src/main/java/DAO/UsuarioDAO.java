package main.java.DAO;

import main.java.connection.ConnectionFactory;
import main.java.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void cadastrar(Usuario usuario) throws SQLException {

    String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";

    try (Connection conexao = ConnectionFactory.getConnection();
         PreparedStatement stmt = conexao.prepareStatement(
             sql,
             java.sql.Statement.RETURN_GENERATED_KEYS
         )) {

        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());

        stmt.executeUpdate();

        try (ResultSet resultado = stmt.getGeneratedKeys()) {

            if (resultado.next()) {
                usuario.setId(resultado.getInt(1));
            }
        }
    }
}

    public List<Usuario> listarTodos() throws SQLException {

        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT id, nome, email FROM usuarios";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {

                Usuario usuario = new Usuario(
                    resultado.getInt("id"),
                    resultado.getString("nome"),
                    resultado.getString("email")
                );

                usuarios.add(usuario);
            }
        }

        return usuarios;
    }

    public void atualizar(Usuario usuario) throws SQLException {

    String sql = "UPDATE usuarios SET nome = ?, email = ? WHERE id = ?";

    try (Connection conexao = ConnectionFactory.getConnection();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setInt(3, usuario.getId());

        stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {

    String sql = "DELETE FROM usuarios WHERE id = ?";

    try (Connection conexao = ConnectionFactory.getConnection();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setInt(1, id);

        stmt.executeUpdate();
        }
    }
}

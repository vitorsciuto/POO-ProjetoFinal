package com.mack.clinica.model;

import com.mack.clinica.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO para operações de usuário.
 */
public class UsuarioDAO {

    /**
     * Consulta o usuário pelo email e senha no banco.
     * @param email Email do usuário.
     * @param senha Senha do usuário.
     * @param realPathBase Caminho real da aplicação para localizar o banco.
     * @return Objeto Usuario encontrado, com id, nome e tipo, ou null se não encontrado.
     */
    public static Usuario buscarUsuario(String email, String senha, String realPathBase) {
        String sql = "SELECT id, nome, tipo FROM usuarios WHERE email = ? AND senha = ?";

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setTipo(rs.getString("tipo"));
                    return usuario;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário no banco de dados.", e);
        }

        return null;
    }

    /**
     * Insere um novo usuário no banco e retorna o ID gerado.
     * @param u Objeto Usuario contendo nome, cpf, celular, email, senha e tipo.
     * @param realPathBase Caminho real da aplicação para localizar o banco.
     * @return ID do usuário recém-criado.
     * @throws SQLException se ocorrer erro na inserção.
     */
    public static int cadastrarUsuario(Usuario u, String realPathBase) throws SQLException {
        String sql = """
            INSERT INTO usuarios (nome, cpf, celular, email, senha, tipo)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getCpf());
            stmt.setString(3, u.getCelular());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getSenha());
            stmt.setString(6, u.getTipo());

            int affected = stmt.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Falha ao inserir usuário, nenhuma linha afetada.");
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("Falha ao obter ID do usuário.");
                }
            }
        }
    }

    /**
     * Busca o usuário pelo ID, trazendo todos os campos para o perfil.
     * @param id ID do usuário.
     * @param realPathBase Caminho real da aplicação para localizar o banco.
     * @return Objeto Usuario completo, ou null se não existir.
     * @throws SQLException em caso de erro de acesso ao banco.
     */
    public static Usuario buscarPorId(int id, String realPathBase) throws SQLException {
        String sql = "SELECT id, nome, email, cpf, celular, tipo FROM usuarios WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setNome(rs.getString("nome"));
                    u.setEmail(rs.getString("email"));
                    u.setCpf(rs.getString("cpf"));
                    u.setCelular(rs.getString("celular"));
                    u.setTipo(rs.getString("tipo"));
                    return u;
                }
            }
        }
        return null;
    }

    /**
     * Atualiza nome, email, cpf e celular do usuário no banco.
     * @param u Objeto Usuario com ID definido e novos valores de campos.
     * @param realPathBase Caminho real da aplicação para localizar o banco.
     * @return true se a atualização afetou alguma linha; false caso contrário.
     * @throws SQLException em caso de erro de acesso ao banco.
     */
    public static boolean atualizarUsuario(Usuario u, String realPathBase) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, cpf = ?, celular = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getCpf());
            ps.setString(4, u.getCelular());
            ps.setInt(5, u.getId());
            return ps.executeUpdate() > 0;
        }
    }
}

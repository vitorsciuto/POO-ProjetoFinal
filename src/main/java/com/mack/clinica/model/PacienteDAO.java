package com.mack.clinica.model;

import com.mack.clinica.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para CRUD de pacientes (tipo = 'paciente').
 */
public class PacienteDAO {
    private final String realPathBase;

    public PacienteDAO(String realPathBase) {
        this.realPathBase = realPathBase;
    }

    public void salvar(Usuario u) throws SQLException {
        String sql = """
            INSERT INTO usuarios
              (nome, email, senha, cpf, celular, tipo)
            VALUES (?, ?, '', ?, ?, 'paciente')
            """;
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getCpf());
            ps.setString(4, u.getCelular());
            ps.executeUpdate();
        }
    }

    public void atualizar(Usuario u) throws SQLException {
        String sql = """
            UPDATE usuarios
               SET nome = ?, email = ?, cpf = ?, celular = ?
             WHERE id = ? AND tipo = 'paciente'
            """;
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getCpf());
            ps.setString(4, u.getCelular());
            ps.setInt(5, u.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ? AND tipo = 'paciente'";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = """
            SELECT id, nome, email, cpf, celular
              FROM usuarios
             WHERE tipo = 'paciente'
            """;
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setCpf(rs.getString("cpf"));
                u.setCelular(rs.getString("celular"));
                u.setTipo("paciente");
                lista.add(u);
            }
        }
        return lista;
    }

    public Usuario buscarPorId(int id) throws SQLException {
        String sql = """
            SELECT id, nome, email, cpf, celular
              FROM usuarios
             WHERE id = ? AND tipo = 'paciente'
            """;
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
                    u.setTipo("paciente");
                    return u;
                }
            }
        }
        return null;
    }
}
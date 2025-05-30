package com.mack.clinica.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mack.clinica.util.DatabaseConnection;

public class MedicoDAO {
    private Connection conn;

    /**
     * Construtor que recebe o realPath da aplicação e
     * instancia a conexão SQLite corretamente.
     */
    public MedicoDAO(String realPath) {
        this.conn = DatabaseConnection.getConnection(realPath);
    }

    /**
     * Lista todos os médicos que já existem no banco.
     */
    public List<Medico> listar() throws SQLException {
        String sql = "SELECT id, nome, email, cpf, celular, senha "
                   + "FROM usuarios "
                   + "WHERE tipo = 'medico'";
        List<Medico> medicos = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Medico m = new Medico();
                m.setId(rs.getInt("id"));
                m.setNome(rs.getString("nome"));
                m.setEmail(rs.getString("email"));
                m.setCpf(rs.getString("cpf"));
                m.setCelular(rs.getString("celular"));
                m.setSenha(rs.getString("senha"));
                medicos.add(m);
            }
        }
        return medicos;
    }

    /**
     * Busca um médico por ID (e garante que seja do tipo 'medico').
     */
    public Medico buscaPorId(int id) throws SQLException {
        String sql = "SELECT id, nome, email, cpf, celular, senha "
                   + "FROM usuarios "
                   + "WHERE id = ? AND tipo = 'medico'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Medico m = new Medico();
                    m.setId(rs.getInt("id"));
                    m.setNome(rs.getString("nome"));
                    m.setEmail(rs.getString("email"));
                    m.setCpf(rs.getString("cpf"));
                    m.setCelular(rs.getString("celular"));
                    m.setSenha(rs.getString("senha"));
                    return m;
                }
            }
        }
        return null;
    }

    /**
     * Insere um novo médico, gravando o tipo corretamente como 'medico'.
     */
    public void inserir(Medico m) throws SQLException {
        String sql = "INSERT INTO usuarios "
                   + "(nome, email, cpf, celular, tipo, senha, created_at) "
                   + "VALUES (?, ?, ?, ?, 'medico', ?, datetime('now'))";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getEmail());
            stmt.setString(3, m.getCpf());
            stmt.setString(4, m.getCelular());
            stmt.setString(5, m.getSenha());
            stmt.executeUpdate();
        }
    }

    /**
     * Atualiza os dados de um médico existente.
     */
    public void atualizar(Medico m) throws SQLException {
        String sql = "UPDATE usuarios "
                   + "SET nome = ?, email = ?, cpf = ?, celular = ?, senha = ? "
                   + "WHERE id = ? AND tipo = 'medico'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getEmail());
            stmt.setString(3, m.getCpf());
            stmt.setString(4, m.getCelular());
            stmt.setString(5, m.getSenha());
            stmt.setInt(6, m.getId());
            stmt.executeUpdate();
        }
    }

    /**
     * Exclui um médico pelo ID.
     */
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM usuarios "
                   + "WHERE id = ? AND tipo = 'medico'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

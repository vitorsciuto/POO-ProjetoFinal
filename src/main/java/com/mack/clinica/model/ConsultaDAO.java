package com.mack.clinica.model;

import com.mack.clinica.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    private final String realPathBase;

    public ConsultaDAO(String realPathBase) {
        this.realPathBase = realPathBase;
    }

    /**
     * Lista as consultas agendadas de um paciente, com nome do médico.
     */
    public List<Consulta> listarPorPaciente(int pacienteId) {
        List<Consulta> resultado = new ArrayList<>();
        String sql =
            "SELECT c.id, c.data_hora, um.nome AS medico " +
            "FROM consultas c " +
            "  JOIN usuarios um ON c.profissional_id = um.id " +
            "WHERE c.paciente_id = ? " +
            "ORDER BY c.data_hora";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pacienteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Consulta c = new Consulta();
                    c.setId(rs.getInt("id"));

                    String dh = rs.getString("data_hora").replace('T', ' ');
                    if (dh.length() == 16) {
                        dh += ":00";
                    }
                    c.setDataHora(Timestamp.valueOf(dh));

                    c.setNomeMedico(rs.getString("medico"));
                    resultado.add(c);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar consultas por paciente", e);
        }
        return resultado;
    }

    /**
     * Lista todas as consultas agendadas, com dados de paciente e médico,
     * ambos vindos da tabela usuarios.
     */
    public List<Consulta> getAllConsultas() {
        List<Consulta> consultas = new ArrayList<>();
        String sql =
            "SELECT c.id, c.data_hora, up.nome AS paciente, um.nome AS medico " +
            "FROM consultas c " +
            "  JOIN usuarios up ON c.paciente_id     = up.id " +
            "  JOIN usuarios um ON c.profissional_id = um.id " +
            "ORDER BY c.data_hora";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Consulta c = new Consulta();
                c.setId(rs.getInt("id"));

                String dh = rs.getString("data_hora").replace('T', ' ');
                if (dh.length() == 16) {
                    dh += ":00";
                }
                c.setDataHora(Timestamp.valueOf(dh));

                c.setNomePaciente(rs.getString("paciente"));
                c.setNomeMedico(rs.getString("medico"));
                consultas.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as consultas", e);
        }
        return consultas;
    }

    /**
     * Exclui a consulta de ID fornecido.
     */
    public void excluirConsulta(int consultaId) {
        String sql = "DELETE FROM consultas WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, consultaId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir consulta", e);
        }
    }
}
// Fim do código
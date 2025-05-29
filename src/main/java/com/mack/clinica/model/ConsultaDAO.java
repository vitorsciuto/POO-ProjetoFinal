package com.mack.clinica.model;

import com.mack.clinica.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    private String realPathBase;

    public ConsultaDAO(String realPathBase) {
        this.realPathBase = realPathBase;
    }

    /**
     * Lista as consultas agendadas de um paciente, com nome do médico.
     */
    public List<Consulta> listarPorPaciente(int pacienteId) {
        List<Consulta> resultado = new ArrayList<>();
        String sql = "SELECT c.id, c.data_hora, u.nome AS medico "
                   + "FROM consultas c "
                   + "JOIN usuarios u ON c.profissional_id = u.id "
                   + "WHERE c.paciente_id = ? "
                   + "ORDER BY c.data_hora";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, pacienteId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Consulta c = new Consulta();
                c.setId(rs.getInt("id"));

                // ——— ajuste para parsing de "2025-04-19T01:40" ———
                String dataHoraStr = rs.getString("data_hora")
                                       .replace('T',' ');          // troca T por espaço
                // se vier sem segundos (exatamente 16 caracteres), adiciona ":00"
                if (dataHoraStr.length() == 16) {
                    dataHoraStr += ":00";
                }
                // agora formata "yyyy-MM-dd HH:mm:ss" -> Timestamp
                c.setDataHora(java.sql.Timestamp.valueOf(dataHoraStr));

                c.setNomeMedico(rs.getString("medico"));
                resultado.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar consultas", e);
        }
        return resultado;
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


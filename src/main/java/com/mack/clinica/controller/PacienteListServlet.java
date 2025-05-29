package com.mack.clinica.controller;

import com.mack.clinica.model.PacienteDAO;
import com.mack.clinica.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/pacientes")
public class PacienteListServlet extends HttpServlet {
    private PacienteDAO dao;

    @Override
    public void init() {
        String realPath = getServletContext().getRealPath("");
        dao = new PacienteDAO(realPath);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<Usuario> pacientes = dao.listarTodos();
            req.setAttribute("pacientes", pacientes);
            req.getRequestDispatcher("/paciente_list.jsp")
               .forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Erro ao listar pacientes", e);
        }
    }
}
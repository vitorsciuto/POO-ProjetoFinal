package com.mack.clinica.controller;

import com.mack.clinica.model.PacienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/pacientes/excluir")
public class PacienteDeleteServlet extends HttpServlet {
    private PacienteDAO dao;

    @Override
    public void init() {
        String realPath = getServletContext().getRealPath("");
        dao = new PacienteDAO(realPath);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            dao.excluir(id);
            resp.sendRedirect(req.getContextPath() + "/pacientes");
        } catch (SQLException e) {
            throw new ServletException("Erro ao excluir paciente", e);
        }
    }
}
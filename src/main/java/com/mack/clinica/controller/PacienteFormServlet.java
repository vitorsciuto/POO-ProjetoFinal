package com.mack.clinica.controller;

import com.mack.clinica.model.PacienteDAO;
import com.mack.clinica.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet({"/pacientes/novo","/pacientes/editar"})
public class PacienteFormServlet extends HttpServlet {
    private PacienteDAO dao;

    @Override
    public void init() {
        String realPath = getServletContext().getRealPath("");
        dao = new PacienteDAO(realPath);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.endsWith("/editar")) {
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                Usuario u = dao.buscarPorId(id);
                req.setAttribute("paciente", u);
            } catch (SQLException e) {
                throw new ServletException("Erro ao carregar paciente", e);
            }
        }
        req.getRequestDispatcher("/paciente_form.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Usuario u = new Usuario();
        u.setNome(req.getParameter("nome"));
        u.setEmail(req.getParameter("email"));
        u.setCpf(req.getParameter("cpf"));
        u.setCelular(req.getParameter("celular"));

        String idStr = req.getParameter("id");
        try {
            if (idStr == null || idStr.isEmpty()) {
                dao.salvar(u);
            } else {
                u.setId(Integer.parseInt(idStr));
                dao.atualizar(u);
            }
            resp.sendRedirect(req.getContextPath() + "/pacientes");
        } catch (SQLException e) {
            throw new ServletException("Erro ao salvar paciente", e);
        }
    }
}
package com.mack.clinica.controller;

import com.mack.clinica.model.Medico;
import com.mack.clinica.model.MedicoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/medicos")
public class MedicoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MedicoDAO dao;

    @Override
    public void init() throws ServletException {
        String realPath = getServletContext().getRealPath("");
        dao = new MedicoDAO(realPath);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if (action == null || action.isEmpty()) {
                list(req, resp);
            } else if ("new".equals(action)) {
                req.getRequestDispatcher("/medico_form.jsp").forward(req, resp);
            } else if ("insert".equals(action)) {
                insert(req, resp);
            } else if ("edit".equals(action)) {
                showEditForm(req, resp);
            } else if ("update".equals(action)) {
                update(req, resp);
            } else if ("delete".equals(action)) {
                delete(req, resp);
            } else {
                list(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        List<Medico> medicos = dao.listar();
        req.setAttribute("medicos", medicos);
        req.getRequestDispatcher("/medico_list.jsp")
           .forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Medico medico = dao.buscaPorId(id);
        req.setAttribute("medico", medico);
        req.getRequestDispatcher("/medico_form.jsp")
           .forward(req, resp);
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        Medico m = new Medico(
            req.getParameter("nome"),
            req.getParameter("email"),
            req.getParameter("cpf"),
            req.getParameter("celular"),
            req.getParameter("senha")
        );
        dao.inserir(m);
        resp.sendRedirect(req.getContextPath() + "/medicos");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        Medico m = new Medico(
            req.getParameter("nome"),
            req.getParameter("email"),
            req.getParameter("cpf"),
            req.getParameter("celular"),
            req.getParameter("senha")
        );
        m.setId(Integer.parseInt(req.getParameter("id")));
        dao.atualizar(m);
        resp.sendRedirect(req.getContextPath() + "/medicos");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        dao.excluir(id);
        resp.sendRedirect(req.getContextPath() + "/medicos");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}

package com.mack.clinica.controller;

import com.mack.clinica.model.Consulta;
import com.mack.clinica.model.ConsultaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/agendamentos")
public class AgendamentoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ConsultaDAO consultaDAO;

    @Override
    public void init() throws ServletException {
        String realPath = getServletContext().getRealPath("/");
        this.consultaDAO = new ConsultaDAO(realPath);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        // se não estiver logado, volta para a página de login (index.jsp)
        if (session == null || session.getAttribute("id") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        // carrega todas as consultas
        List<Consulta> consultas = consultaDAO.getAllConsultas();
        request.setAttribute("consultas", consultas);

        // encaminha para a view
        request.getRequestDispatcher("/agendamentos.jsp")
       .forward(request, response);
    }
}

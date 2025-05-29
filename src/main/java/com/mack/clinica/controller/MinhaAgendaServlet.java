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

@WebServlet("/minha_agenda")
public class MinhaAgendaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ConsultaDAO consultaDAO;

    @Override
    public void init() throws ServletException {
        // obtém o caminho físico da aplicação para abrirmos o SQLite
        String realPath = getServletContext().getRealPath("/");
        this.consultaDAO = new ConsultaDAO(realPath);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("id") == null) {
            // redireciona ao login (index.jsp) se não houver sessão
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        int pacienteId = (Integer) session.getAttribute("id");
        // o LoginActionServlet definiu session.setAttribute("id", usuario.getId())
        List<Consulta> consultas = consultaDAO.listarPorPaciente(pacienteId);
        request.setAttribute("consultas", consultas);
        request.getRequestDispatcher("/minha_agenda.jsp")
               .forward(request, response);
    }
}

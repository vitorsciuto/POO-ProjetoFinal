package com.mack.clinica.controller;

import com.mack.clinica.model.ConsultaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/deleta_consulta")
public class DeleteConsultaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ConsultaDAO consultaDAO;

    @Override
    public void init() throws ServletException {
        String realPath = getServletContext().getRealPath("/");
        this.consultaDAO = new ConsultaDAO(realPath);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String param = request.getParameter("consultaId");
        if (param != null) {
            int id = Integer.parseInt(param);
            consultaDAO.excluirConsulta(id);
        }
        // volta para a lista
        response.sendRedirect(request.getContextPath() + "/minha_agenda");
    }
}
package com.mack.clinica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ficha_clinica")
public class FichaClinicaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Apenas exibe o formulário
        req.getRequestDispatcher("ficha_clinica.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Nesta fase, não vamos persistir: 
        // você pode redirecionar para uma página de sucesso ou
        // mostrar uma mensagem de que o form foi submetido.
        resp.sendRedirect("mensagem_sucesso.jsp?mensagem=Ficha+salva+com+sucesso");
    }
}


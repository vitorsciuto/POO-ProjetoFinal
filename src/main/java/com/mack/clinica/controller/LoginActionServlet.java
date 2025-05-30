package com.mack.clinica.controller;

import java.io.IOException;
import com.mack.clinica.model.Usuario;
import com.mack.clinica.model.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet responsável por processar o login do usuário.
 */
@WebServlet("/loginAction")
public class LoginActionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String realPathBase = request.getServletContext().getRealPath("/");

        Usuario usuario = UsuarioDAO.buscarUsuario(email, senha, realPathBase);

        if (usuario != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("id", usuario.getId());
            session.setAttribute("nome", usuario.getNome());
            session.setAttribute("tipo", usuario.getTipo());
            // para compatibilidade com outros servlets que checam "role"
            session.setAttribute("role", usuario.getTipo());
            session.setAttribute("usuario", usuario);

            String tipo = usuario.getTipo().toLowerCase();
            switch (tipo) {
                case "admin":
                    response.sendRedirect(request.getContextPath() + "/admin_dashboard");
                    break;
                case "medico":
                    response.sendRedirect(request.getContextPath() + "/medico_dashboard");
                    break;
                case "paciente":
                    response.sendRedirect(request.getContextPath() + "/paciente_dashboard");
                    break;
                default:
                    // tipo inesperado
                    response.sendRedirect(request.getContextPath() + "/index.jsp?erro=tipo");
                    break;
            }
        } else {
            // credenciais inválidas
            response.sendRedirect(request.getContextPath() + "/index.jsp?erro=login");
        }
    }
}

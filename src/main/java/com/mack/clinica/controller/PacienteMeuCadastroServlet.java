package com.mack.clinica.controller;

import com.mack.clinica.model.Usuario;
import com.mack.clinica.model.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/paciente_meu_cadastro")
public class PacienteMeuCadastroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        // Agora checamos o atributo "usuario" que realmente existe na sessão
        if (session == null || session.getAttribute("usuario") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        Usuario sessUser = (Usuario) session.getAttribute("usuario");

        try {
            String realPath = getServletContext().getRealPath("/");
            Usuario usuario = UsuarioDAO.buscarPorId(sessUser.getId(), realPath);
            req.setAttribute("usuario", usuario);
            req.getRequestDispatcher("/paciente_meu_cadastro.jsp")
               .forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Erro ao carregar dados do usuário", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id        = Integer.parseInt(req.getParameter("id"));
        String nome   = req.getParameter("nome");
        String email  = req.getParameter("email");
        String cpf    = req.getParameter("cpf");
        String celular= req.getParameter("celular");

        Usuario u = new Usuario();
        u.setId(id);
        u.setNome(nome);
        u.setEmail(email);
        u.setCpf(cpf);
        u.setCelular(celular);

        try {
            String realPath = getServletContext().getRealPath("/");
            boolean sucesso = UsuarioDAO.atualizarUsuario(u, realPath);

            if (sucesso) {
                // Atualiza o objeto usuario na sessão
                HttpSession session = req.getSession();
                session.setAttribute("usuario", u);
                // Redirect para mostrar ?success=1
                resp.sendRedirect(req.getContextPath()
                    + "/paciente_meu_cadastro?success=1");
            } else {
                req.setAttribute("error", "Não foi possível atualizar seus dados.");
                doGet(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException("Erro ao atualizar usuário", e);
        }
    }
}

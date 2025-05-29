package com.mack.clinica.controller;

import com.mack.clinica.model.Usuario;
import com.mack.clinica.model.UsuarioDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/cadastroAction")
public class CadastroServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1) Captura parâmetros do form
        req.setCharacterEncoding("UTF-8");
        String nome     = req.getParameter("nome");
        String cpf      = req.getParameter("cpf");
        String celular  = req.getParameter("celular");
        String email    = req.getParameter("email");
        String senha    = req.getParameter("senha");
        String confirma = req.getParameter("confirmaSenha");

        // 2) Valida senhas
        if (!senha.equals(confirma)) {
            req.setAttribute("erro", "As senhas não coincidem.");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }

        // 3) Define tipo padrão
        String tipo = "paciente";

        // 4) Monta objeto Usuario
        Usuario user = new Usuario(nome, email, senha, cpf, celular, tipo);

        // 5) Recupera o realPathBase para o DAO
        String realPathBase = getServletContext().getRealPath("/");

        try {
            // 6) Chama o DAO passando também o caminho físico
            int novoId = UsuarioDAO.cadastrarUsuario(user, realPathBase);

            // 7) Armazena na sessão (opcional)
            HttpSession session = req.getSession();
            session.setAttribute("userId", novoId);
            session.setAttribute("userTipo", tipo);

            // 8) Redireciona com parâmetros de sucesso
            resp.sendRedirect("index.jsp?cadastro=sucesso&id=" + novoId + "&tipo=" + tipo);

        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar: " + e.getMessage());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}

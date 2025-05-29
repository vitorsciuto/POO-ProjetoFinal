<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mack.clinica.model.Usuario" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Agendar Consulta</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

    <!-- Menu de Navegação -->
    <div class="navbar">
        <div class="nav-links">
            <a href="paciente_dashboard">Home</a>
            <a href="agendar_consulta">Agendamento de Consultas</a>
            <a href="minha_agenda">Minha Agenda</a>
            <a href="paciente_meu_cadastro">Meu Cadastro</a>
            <a href="logout">Logout</a>
            <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
        </div>
    </div>

<!-- Conteúdo centralizado -->
<div class="content">
    <h1>Meu Cadastro</h1>
    
    <form action="paciente_meu_cadastro.jsp" method="post">
        <div class="form-group">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" value="${usuario.nome}" required>
        </div>
        
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${usuario.email}" required>
        </div>
        
        <div class="form-group">
            <label for="cpf">CPF:</label>
            <input type="text" id="cpf" name="cpf" value="${usuario.cpf}" required>
        </div>
            <div class="form-group">
            <label for="cpf">CPF:</label>
            <input type="text" id="cpf" name="cpf" value="${usuario.cpf}" required>
</div>

</body>
</html>
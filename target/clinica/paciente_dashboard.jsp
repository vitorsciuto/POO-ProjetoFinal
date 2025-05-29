<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Painel do Paciente</title>
    <!-- Importa o CSS externo -->
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

    <!-- Menu de Navegação -->
    <div class="navbar">
        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/paciente_dashboard">Home</a>
            <a href="${pageContext.request.contextPath}/agendar_consulta">Agendamento de Consultas</a>
            <a href="${pageContext.request.contextPath}/minha_agenda">Minha Agenda</a>
            <a href="${pageContext.request.contextPath}/paciente_meu_cadastro">Meu Cadastro</a>
            <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
        </div>
    </div>

    <!-- Conteúdo principal -->
    <div class="content">
        <h1>Painel do Paciente</h1>
        <p>Bem-vindo ao seu painel, onde você poderá visualizar informações e agendar consultas.</p>
    </div>

</body>
</html>

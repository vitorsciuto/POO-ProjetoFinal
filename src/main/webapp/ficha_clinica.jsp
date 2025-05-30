<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Ficha Clínica</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="navbar">
        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/admin_dashboard">Home</a>
            <a href="${pageContext.request.contextPath}/pacientes">Cadastro de Pacientes</a>
            <a href="${pageContext.request.contextPath}/medicos">Cadastro de Médicos</a>
            <a href="${pageContext.request.contextPath}/agendamentos">Agendamentos</a>
            <a href="${pageContext.request.contextPath}/ficha_clinica">Ficha Clínica</a>
            <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
        </div>
    </div>

    <div class="content">
        <h1>Ficha Clínica (Prontuário)</h1>
        <form action="${pageContext.request.contextPath}/fichaClinica" method="post">
            <label for="paciente_id">Paciente (ID):</label>
            <input type="text" id="paciente_id" name="paciente_id" placeholder="Digite o ID do paciente"/>

            <label for="profissional_id">Profissional (ID):</label>
            <input type="text" id="profissional_id" name="profissional_id" placeholder="Digite o ID do médico"/>

            <label for="data">Data da Consulta:</label>
            <input type="date" id="data" name="data"/>

            <label for="anotacoes">Anotações Médicas:</label>
            <textarea id="anotacoes" name="anotacoes_medicas" rows="5"></textarea>

            <label for="prescricoes">Prescrições:</label>
            <textarea id="prescricoes" name="prescricoes" rows="5"></textarea>

            <button type="submit">Salvar Ficha Clínica</button>
        </form>
    </div>
</body>
</html>

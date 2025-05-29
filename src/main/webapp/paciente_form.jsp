<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>${param.id == null ? "Novo Paciente" : "Editar Paciente"}</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?v=123">
</head>
<body>

  <!-- Barra de navegação -->
  <nav class="navbar">
    <ul>
        <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/pacientes">Cadastro de Pacientes</a></li>
        <li><a href="${pageContext.request.contextPath}/agendar_consulta.jsp">Agendar Consulta</a></li>
        <li><a href="${pageContext.request.contextPath}/minha_agenda.jsp">Minha Agenda</a></li>
        <li><a href="${pageContext.request.contextPath}/meu_cadastro.jsp">Meu Cadastro</a></li>
        <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
    </ul>
    </nav>

  <div class="container">
    <h2>${param.id == null ? "Novo Paciente" : "Editar Paciente"}</h2>
    <form method="post" action="">
      <input type="hidden" name="id" value="${paciente.id}" />

      <label for="nome">Nome:</label>
      <input id="nome" type="text" name="nome" value="${paciente.nome}" required/><br/>

      <label for="email">Email:</label>
      <input id="email" type="email" name="email" value="${paciente.email}" required/><br/>

      <label for="cpf">CPF:</label>
      <input id="cpf" type="text" name="cpf" value="${paciente.cpf}" required/><br/>

      <label for="celular">Celular:</label>
      <input id="celular" type="text" name="celular" value="${paciente.celular}" required/><br/>

      <button type="submit" class="btn">Salvar</button>
      <a href="${pageContext.request.contextPath}/pacientes" class="btn btn-secondary">Voltar</a>
    </form>
  </div>

</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Cadastro de Pacientes</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?v=123">
</head>
<body>

  <!-- Barra de navegação -->
  <nav class="navbar">
    <ul>
        <a href="${pageContext.request.contextPath}/home">Home</a>
        <a href="${pageContext.request.contextPath}/pacientes">Cadastro de Pacientes</a>
        <a href="${pageContext.request.contextPath}/ficha_clinica">Ficha Clínica</a>
        <a href="${pageContext.request.contextPath}/minha_agenda.jsp">Minha Agenda</a>
        <a href="${pageContext.request.contextPath}/ficha_clinica">Ficha Clínica</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </ul>
    </nav>

  <div class="container">
    <h2>Cadastro de Pacientes</h2>
    <a href="${pageContext.request.contextPath}/pacientes/novo" class="btn">Novo Paciente</a>

    <table class="styled-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>Email</th>
          <th>CPF</th>
          <th>Celular</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="p" items="${pacientes}">
          <tr>
            <td>${p.id}</td>
            <td>${p.nome}</td>
            <td>${p.email}</td>
            <td>${p.cpf}</td>
            <td>${p.celular}</td>
            <td>
              <a href="${pageContext.request.contextPath}/pacientes/editar?id=${p.id}"
                 class="btn btn-edit">Editar</a>
              <a href="${pageContext.request.contextPath}/pacientes/excluir?id=${p.id}"
                 class="btn btn-delete"
                 onclick="return confirm('Confirma exclusão?');">Excluir</a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>

</body>
</html>
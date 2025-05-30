<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Cadastro de Médicos</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?v=123"/>
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

  <div class="container">
    <h2>Cadastro de Médicos</h2>
    <!-- ALTERAÇÃO AQUI: usa ?action=new em vez de /novo -->
    <a href="${pageContext.request.contextPath}/medicos?action=new" class="btn">Novo Médico</a>

    <table class="styled-table">
      <thead>
        <tr>
          <th>ID</th><th>Nome</th><th>Email</th><th>CPF</th><th>Celular</th><th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="m" items="${medicos}">
          <tr>
            <td>${m.id}</td>
            <td>${m.nome}</td>
            <td>${m.email}</td>
            <td>${m.cpf}</td>
            <td>${m.celular}</td>
            <td>
              <a href="${pageContext.request.contextPath}/medicos?action=edit&id=${m.id}"
                 class="btn btn-edit">Editar</a>
              <a href="${pageContext.request.contextPath}/medicos?action=delete&id=${m.id}"
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

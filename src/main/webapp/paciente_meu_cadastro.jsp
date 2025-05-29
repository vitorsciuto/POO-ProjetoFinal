<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.mack.clinica.model.Usuario" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <title>Meu Cadastro</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
  <div class="navbar">
    <div class="nav-links">
      <a href="${pageContext.request.contextPath}/paciente_dashboard">Home</a>
      <a href="${pageContext.request.contextPath}/agendar_consulta">Agendar Consulta</a>
      <a href="${pageContext.request.contextPath}/minha_agenda">Minha Agenda</a>
      <a href="${pageContext.request.contextPath}/paciente_meu_cadastro">Meu Cadastro</a>
      <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
  </div>

  <div class="content">
    <h1>Meu Cadastro</h1>

    <c:if test="${param.success == '1'}">
      <div class="alert alert-success">Dados atualizados com sucesso!</div>
    </c:if>
    <c:if test="${not empty error}">
      <div class="alert alert-danger">${error}</div>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/paciente_meu_cadastro" class="form-container">
      <input type="hidden" name="id" value="${usuario.id}">
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
        <label for="celular">Celular:</label>
        <input type="text" id="celular" name="celular" value="${usuario.celular}">
      </div>
      <div class="form-group">
        <button type="submit">Finalizar</button>
      </div>
    </form>
  </div>
</body>
</html>

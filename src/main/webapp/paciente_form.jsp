<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>${param.id == null ? "Novo Paciente" : "Editar Paciente"}</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?v=123"/>
</head>
<body>

  <!-- Barra de navegação -->
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

      <label for="senha">Senha:</label>
      <input id="senha" type="password" name="senha" value="${paciente.senha}" required/>

      <button type="submit" class="btn">Salvar</button>
      <a href="${pageContext.request.contextPath}/pacientes" class="btn btn-secondary">Voltar</a>
    </form>
  </div>

</body>
</html>
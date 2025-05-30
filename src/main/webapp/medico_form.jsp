<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>${param.id == null ? "Novo Médico" : "Editar Médico"}</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?v=123"/>
</head>
<body>

  <!-- Navbar igual do paciente -->
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
    <h2>${param.id == null ? "Novo Médico" : "Editar Médico"}</h2>
    <!-- 1) action para /medicos, sem query-string -->
    <form method="post" action="${pageContext.request.contextPath}/medicos">
      <!-- 2) hidden para dizer ao servlet o que fazer -->
      <input type="hidden" name="action" value="${param.id == null ? 'insert' : 'update'}"/>
      <!-- mantém o id existente em edição -->
      <input type="hidden" name="id"    value="${medico.id}"/>

      <label for="nome">Nome:</label>
      <input id="nome" type="text" name="nome" value="${medico.nome}" required/>

      <label for="email">Email:</label>
      <input id="email" type="email" name="email" value="${medico.email}" required/>

      <label for="cpf">CPF:</label>
      <input id="cpf" type="text" name="cpf" value="${medico.cpf}" required/>

      <label for="celular">Celular:</label>
      <input id="celular" type="text" name="celular" value="${medico.celular}" required/>

      <label for="senha">Senha:</label>
      <input id="senha" type="password" name="senha" value="${medico.senha}" required/>

      <button type="submit" class="btn">Salvar</button>
      <a href="${pageContext.request.contextPath}/medicos" class="btn btn-secondary">Voltar</a>
    </form>
  </div>

</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <title>Agendamentos de Consultas</title>
  <link rel="stylesheet" href="/css/style.css">
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
    <h2>Agendamentos de Consultas</h2>

    <c:if test="${empty consultas}">
      <p>Nenhuma consulta agendada.</p>
    </c:if>

    <c:if test="${not empty consultas}">
      <table class="styled-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Data</th>
            <th>Hora</th>
            <th>Paciente</th>
            <th>Médico</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="c" items="${consultas}">
            <tr>
              <td>${c.id}</td>
              <td><fmt:formatDate value="${c.dataHora}" pattern="dd/MM/yyyy" /></td>
              <td><fmt:formatDate value="${c.dataHora}" pattern="HH:mm" /></td>
              <td>${c.nomePaciente}</td>
              <td>${c.nomeMedico}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:if>
  </div>
</body>
</html>
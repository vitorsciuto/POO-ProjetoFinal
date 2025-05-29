<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8"/>
  <title>Minha Agenda</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?v=123"/>
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
        integrity="sha512-..."
        crossorigin="anonymous"
        referrerpolicy="no-referrer"/>
</head>
<body>
  <!-- Navbar -->
  <div class="navbar">
    <div class="nav-links">
      <a href="${pageContext.request.contextPath}/paciente_dashboard">Home</a>
      <a href="${pageContext.request.contextPath}/agendar_consulta">Agendamento de Consultas</a>
      <a href="${pageContext.request.contextPath}/minha_agenda">Minha Agenda</a>
      <a href="${pageContext.request.contextPath}/paciente_meu_cadastro">Meu Cadastro</a>
      <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
    </div>
  </div>

  <!-- Container principal -->
  <div class="agenda-container">
    <div class="agenda-header">
      <h2>Minhas Consultas</h2>
    </div>

    <!-- Sem consultas -->
    <c:if test="${empty consultas}">
      <p>Você não tem consultas agendadas.</p>
    </c:if>

    <!-- Com consultas -->
    <c:if test="${not empty consultas}">
      <div class="cards-wrapper">
        <c:forEach var="c" items="${consultas}">
          <div class="consulta-card">
            <!-- Header flexível: data + botão -->
            <div class="card-header">
              <h3>
                <fmt:formatDate value="${c.dataHora}"
                                pattern="dd/MM/yyyy 'às' HH:mm"/>
              </h3>
              <form action="${pageContext.request.contextPath}/deleta_consulta"
                    method="post"
                    onsubmit="return confirm('Confirma exclusão desta consulta?');">
                <input type="hidden" name="consultaId" value="${c.id}"/>
                <button type="submit" class="btn-delete">
                  <i class="fa-solid fa-trash"></i>
                </button>
              </form>
            </div>
            <p>Médico: ${c.nomeMedico}</p>
          </div>
        </c:forEach>
      </div>
    </c:if>
  </div>
</body>
</html>



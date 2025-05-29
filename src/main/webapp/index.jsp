<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <!-- force reload do CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css?v=126" />
</head>
<body>

  <div class="login-container">
      <h2>Clínica Mack</h2>
      <form method="post" action="loginAction">
          <label for="email">E-mail</label>
          <input type="email" id="email" name="email" placeholder="seu@exemplo.com" required />

          <label for="senha">Senha</label>
          <input type="password" id="senha" name="senha" placeholder="••••••••" required />

          <button type="submit" class="btn-submit">Entrar</button>
          <button type="button" class="btn-register" id="open-cad">Cadastre-se</button>
      </form>
  </div>

  <!-- Backdrop dark -->
  <div class="modal-backdrop" id="backdrop"></div>

  <!-- Modal centralizado -->
  <div class="modal" id="cad-panel">
      <div class="modal-header">
          <h3>Cadastre-se</h3>
          <button class="close-btn" id="close-cad">&times;</button>
      </div>
      <div class="modal-body">
          <form method="post" action="cadastroAction">
              <label for="nome">Nome Completo</label>
              <input type="text" id="nome" name="nome" placeholder="Seu nome" required />

              <label for="cpf">CPF</label>
              <input type="text" id="cpf" name="cpf" placeholder="000.000.000-00" required />

              <label for="celular">Celular</label>
              <input type="tel" id="celular" name="celular" placeholder="(00) 00000-0000" required />

              <label for="cad-email">E-mail</label>
              <input type="email" id="cad-email" name="email" placeholder="seu@exemplo.com" required />

              <label for="cad-senha">Senha</label>
              <input type="password" id="cad-senha" name="senha" placeholder="••••••••" required />

              <label for="conf-senha">Confirme a Senha</label>
              <input type="password" id="conf-senha" name="confirmaSenha" placeholder="••••••••" required />

              <button type="submit" class="btn-submit">Criar Conta</button>
          </form>
      </div>
  </div>

  <script>
      const openBtn  = document.getElementById('open-cad');
      const closeBtn = document.getElementById('close-cad');
      const panel    = document.getElementById('cad-panel');
      const backdrop = document.getElementById('backdrop');

      function toggleModal(show) {
        panel.classList.toggle('visible', show);
        backdrop.classList.toggle('visible', show);
      }

      openBtn.addEventListener ('click', () => toggleModal(true));
      closeBtn.addEventListener('click', () => toggleModal(false));
      backdrop.addEventListener('click', () => toggleModal(false));
  </script>

</body>
</html>

# POO-ProjetoFinal
Projeto final de Programação Orientada à Objetos.

Integrantes:
- Felipe Seillier Navai (RA:10443912)
- Rafael de Oliveira Corrêa (RA:10438174)
- Vitor Santos Lo Sciuto (RA:10438906)

Estrutura do Projeto:
    
    Model: 
        Classes para representar as entidades (Paciente, Médico, Consulta) e DAOs, responsáveis pela in-
        teração com o banco de dados (salvar, buscar, atualizar e apagar).
    Controller: 
        Aqui estão alocados os Servlets, responsáveis por receber requisições HTTP, chamar o DAO encami-
        nhar para o JSP.
    WebApp:
        Dividido entre os arquivos CSS (responsáveis pelo estilo de cada página JSP) e os arquivos JSP,
        que contém as informações e ferramentas que que o usuário pode interagir.
    Web-Inf:
        Contém o arquivo "db.db", arquivo de banco de dados reposável por armazenar as principais infor-
        mações da lógica da clínica.
        *web.xml: configurações e URLs para Servlets

Principais funções e classes:

    Servlets:
        
        *HttpServlet
        - protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        -> Resposta a requisições do tipo GET
            
        - protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        -> Reposta a requisições do tipo POST

        Outros termos:
        - HttpServletRequest: contém dados da requisição (parâmetros, sessão, cabeçalhos).
        - HttpServletResponse: permite escrever a resposta (HTML, JSON) e controlar redirecionamentos.
        - RequestDispatcher: objeto que faz o "forward" do request para um JSP.

    DAOs:

        *public class PacienteDAO {
            public List<Paciente> listarTodos() throws SQLException { ... }
            public Paciente buscarPorId(int id) throws SQLException { ... }
            public void salvar(Paciente p) throws SQLException { ... }
            public void atualizar(Paciente p) throws SQLException { ... }
            public void deletar(int id) throws SQLException { ... }
        }

        - listarTodos() = retorna List<T> com todos registros.
        - buscarPorId(int id) = busca único registro.
        - salvar(T obj) = insere um novo registro.
        - atualizar(T obj) = altera dados existentes.
        - deletar(int id) = remove do banco de dados.

        public Usuario buscarPorId(int id) throws SQLException {
            String sql = """
                SELECT id, nome, email, cpf, celular
                FROM usuarios
                WHERE id = ? AND tipo = 'paciente'
                """;
            try (Connection conn = DatabaseConnection.getConnection(realPathBase);
                PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Usuario u = new Usuario();
                        u.setId(rs.getInt("id"));
                        u.setNome(rs.getString("nome"));
                        u.setEmail(rs.getString("email"));
                        u.setCpf(rs.getString("cpf"));
                        u.setCelular(rs.getString("celular"));
                        u.setTipo("paciente");
                        return u;
                    }
                }
                }
            return null;
        }

        - pedaço de código em SQL, para interação com o banco de dados
        """
                SELECT id, nome, email, cpf, celular
                FROM usuarios
                WHERE id = ? AND tipo = 'paciente'
                """;
        - inicializando a variável conn com uma sessão ativa no banco, usando como base o caminho 
        físico realPathBase para localizar o arquivo de dados.
    
    JSPs:

        Algumas páginas:

            - admin_dashboard.jsp
                renderiza o Painel do Administrador, exibindo um menu de navegação para gerenciar pacientes, médicos, consultar agendas e acessar fichas clínicas
            - index.jsp
                formulário de login (e-mail e senha) e, via JavaScript, uma estrutura de cadastro para novos usuários
            - paciente_form.jsp
                mostra um formulário para criar ou editar dados de um paciente, ajustando o título entre “Novo Paciente” e “Editar Paciente” conforme o parâmetro id, e pré-preenchendo os campos com valores do objeto paciente usando Expression Language. Além disso, inclui uma barra de navegação e botões para “Salvar” e “Voltar” ao cadastro.
            - paciente_list.jsp
                exibe a lista de pacientes em uma tabela e mostra colunas como ID, nome, e-mail, CPF e telefone, além de botões “Editar” e “Excluir” que passam o id do paciente pela URL. Ela também inclui um link “Novo Paciente” para abrir o formulário de cadastro.

Aprendizado sobre comandos ligados ao Github:

    -> Commit
        - git add .
        - git commit -m ""
        - git push
    
    -> Uso de diferentes Branches e Merge 
        - Estando na main: git merge {NomeDaBranch}
        - git push --set-upstream origin Features_V1
        - git pull

Como rodar o código:

    -> mvn clean install
    -> mvn jetty:run
    -> abrimos o código no localhost:8080








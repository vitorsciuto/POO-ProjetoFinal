package com.mack.clinica.model;

public class Usuario {
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String celular;
    private String tipo;       // ← novo campo

    public Usuario() {}

    /** Para cadastro (sem id ainda) */
    public Usuario(String nome, String email, String senha, String cpf, String celular, String tipo) {
        this.nome    = nome;
        this.email   = email;
        this.senha   = senha;
        this.cpf     = cpf;
        this.celular = celular;
        this.tipo    = tipo;
    }

    /** Construtor completo (com id) */
    public Usuario(Integer id, String nome, String email, String senha, String cpf, String celular, String tipo) {
        this(nome, email, senha, cpf, celular, tipo);
        this.id = id;
    }

    // getters/setters de todos os campos...
    public Integer getId()            { return id; }
    public void setId(Integer id)     { this.id = id; }

    public String getNome()           { return nome; }
    public void setNome(String nome)  { this.nome = nome; }

    public String getEmail()          { return email; }
    public void setEmail(String email){ this.email = email; }

    public String getSenha()          { return senha; }
    public void setSenha(String senha){ this.senha = senha; }

    public String getCpf()            { return cpf; }
    public void setCpf(String cpf)    { this.cpf = cpf; }

    public String getCelular()        { return celular; }
    public void setCelular(String celular){ this.celular = celular; }

    public String getTipo()           { return tipo; }
    public void setTipo(String tipo)  { this.tipo = tipo; }
}
// Fim do arquivo Usuario.java
package com.mack.clinica.model;

public class Medico {
    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String celular;
    private String senha;

    // Construtor vazio
    public Medico() {}

    // Construtor completo (sem id, para inserção)
    public Medico(String nome, String email, String cpf, String celular, String senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.celular = celular;
        this.senha = senha;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}

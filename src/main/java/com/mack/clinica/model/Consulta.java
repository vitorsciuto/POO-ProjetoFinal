package com.mack.clinica.model;

import java.sql.Timestamp;

public class Consulta {
    private int id;
    private Timestamp dataHora;
    private String nomeMedico;
    private String nomePaciente;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }
    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }
    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }
}



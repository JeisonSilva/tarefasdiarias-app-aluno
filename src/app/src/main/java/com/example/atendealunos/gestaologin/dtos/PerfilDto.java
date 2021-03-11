package com.example.atendealunos.gestaologin.dtos;

import java.io.Serializable;

public class PerfilDto implements Serializable {
    String email;
    String nome;
    String emailProfessor;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmailProfessor(String emailProfessor) {
        this.emailProfessor = emailProfessor;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmailProfessor() {
        return emailProfessor;
    }
}

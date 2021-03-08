package com.example.atendealunos.gestaologin.dtos;

import java.io.Serializable;

public class PerfilDto implements Serializable {
    String email;
    String nome;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }
}

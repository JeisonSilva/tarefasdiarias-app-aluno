package com.example.atendealunos.gestaologin.dtos;

import java.io.Serializable;

public class SenhaDto implements Serializable {
    String senha;

    public SenhaDto(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }
}

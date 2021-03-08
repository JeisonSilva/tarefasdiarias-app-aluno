package com.example.atendealunos.gestaologin.dtos;

public class LoginDto {
    String email;
    String senha;

    public LoginDto(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}

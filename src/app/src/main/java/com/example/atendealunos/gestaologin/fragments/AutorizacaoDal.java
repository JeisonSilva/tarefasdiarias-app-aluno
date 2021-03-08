package com.example.atendealunos.gestaologin.fragments;

import com.example.atendealunos.gestaologin.dtos.LoginDto;

public interface AutorizacaoDal {
    LoginDto obterLogin();

    void finalizarProgressoLogin();

    void iniciarProgressoLogin();
}

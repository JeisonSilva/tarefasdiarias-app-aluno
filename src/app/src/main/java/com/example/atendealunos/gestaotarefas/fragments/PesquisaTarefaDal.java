package com.example.atendealunos.gestaotarefas.fragments;

import com.example.atendealunos.gestaotarefas.dtos.EdicaoTarefaDto;

public interface PesquisaTarefaDal {
    void carregarTarefaPorId(EdicaoTarefaDto edicao);
}

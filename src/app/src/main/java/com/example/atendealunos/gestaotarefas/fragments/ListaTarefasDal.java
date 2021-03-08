package com.example.atendealunos.gestaotarefas.fragments;

import com.example.atendealunos.gestaotarefas.dtos.TarefaDisponivelDto;

public interface ListaTarefasDal {
    void carregarTarefasDisponiveis(TarefaDisponivelDto[] tarefasEmDigitacao);
}

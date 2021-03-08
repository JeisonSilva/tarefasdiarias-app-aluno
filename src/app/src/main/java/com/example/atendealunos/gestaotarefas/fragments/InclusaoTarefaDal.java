package com.example.atendealunos.gestaotarefas.fragments;

import com.example.atendealunos.gestaotarefas.dtos.NovaTarefaDto;

public interface InclusaoTarefaDal {
    void criarNovaTarefa(NovaTarefaDto novaTarefaDto);
}

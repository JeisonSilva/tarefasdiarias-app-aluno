package com.example.atendealunos.gestaotarefas.fragments;

import com.example.atendealunos.gestaotarefas.dtos.EdicaoTarefaDto;

public interface EdicaoTarefaDal {
    void editarTarefa(EdicaoTarefaDto tarefa);
    void carregarTarefa(PesquisaTarefaDal pesquisaTarefaDal, int tarefaId);
}

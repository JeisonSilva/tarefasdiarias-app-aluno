package com.example.atendealunos.gestaotarefas.fragments;

public interface ListaTarefasDisponiveisDal {
    void exibirTelaParaCriarNovaTarefa();
    void exibirTelaParaEditarTarefa(int tarefaId);
    void exibirNotificacaoExclusaoTarefa(int id);
    void exibirNotificacaoEnvioTarefaProfessor(int id);

    void carregarTarefasDisponiveis(ListaTarefasDal listaTarefasDal);
}

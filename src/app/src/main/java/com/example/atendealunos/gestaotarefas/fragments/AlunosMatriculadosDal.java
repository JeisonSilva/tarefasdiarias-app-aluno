package com.example.atendealunos.gestaotarefas.fragments;

import com.example.atendealunos.gestaotarefas.dtos.AlunoMatriculadoDto;

public interface AlunosMatriculadosDal {
    void carregarListaDeAlunos(AlunoMatriculadoDto[] alunoMatriculadoDtos);
}

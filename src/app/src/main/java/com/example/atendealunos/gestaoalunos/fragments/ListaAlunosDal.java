package com.example.atendealunos.gestaoalunos.fragments;

import com.example.atendealunos.gestaotarefas.dtos.AlunoMatriculadoDto;

public interface ListaAlunosDal {
    void carregarListaDeAlunos(AlunoMatriculadoDto[] alunoMatriculadoDtos);
}

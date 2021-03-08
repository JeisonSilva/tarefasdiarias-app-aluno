package com.example.atendealunos.gestaotarefas.fragments;

import com.example.atendealunos.gestaotarefas.dtos.DisciplinaDto;

public interface DisciplinasAtivasDal {
    void carregarDisciplinas(DisciplinaDto[] disciplinasDtos);
}

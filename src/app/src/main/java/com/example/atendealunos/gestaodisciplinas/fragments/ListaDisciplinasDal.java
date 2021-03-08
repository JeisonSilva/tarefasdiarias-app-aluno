package com.example.atendealunos.gestaodisciplinas.fragments;

import com.example.atendealunos.gestaodisciplinas.DisciplinaDto;

public interface ListaDisciplinasDal {
    void carregarDisciplinas(DisciplinaDto[] disciplinasDtos);
}

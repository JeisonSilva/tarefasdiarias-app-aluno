package com.example.atendealunos;

import android.view.MenuItem;

import com.example.atendealunos.gestaoalunos.fragments.ListaAlunosDal;
import com.example.atendealunos.gestaodisciplinas.fragments.ListaDisciplinasDal;
import com.example.atendealunos.gestaologin.dtos.PerfilDto;

public interface HomeDal {
    void listarTarefasEmDigitacao();
    void exibirTela(MenuItem item);

    PerfilDto obterPerfil();

    void listarDisiplinas(ListaDisciplinasDal listaDisciplinasDal);

    void listarAlunosMatriculados(ListaAlunosDal listaAlunosDal);
}

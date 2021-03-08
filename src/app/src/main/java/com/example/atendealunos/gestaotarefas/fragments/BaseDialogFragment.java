package com.example.atendealunos.gestaotarefas.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public abstract class BaseDialogFragment extends DialogFragment implements DisciplinasAtivasDal, AlunosMatriculadosDal{

    private final ListagemAlunosDal listagemAlunosDal;
    private final ListagemDisciplinaDal listagemDisciplinaDal;

    public BaseDialogFragment(ListagemAlunosDal listagemAlunosDal, ListagemDisciplinaDal listagemDisciplinaDal) {
        this.listagemAlunosDal = listagemAlunosDal;
        this.listagemDisciplinaDal = listagemDisciplinaDal;
    }

    public abstract View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    protected void carregarDisciplinas() {
        this.listagemDisciplinaDal.listarDisciplinas((DisciplinasAtivasDal)this);
    }

    protected void carregarAlunos() {
        this.listagemAlunosDal.listarAlunosDoProfessor((AlunosMatriculadosDal)this);
    }

}

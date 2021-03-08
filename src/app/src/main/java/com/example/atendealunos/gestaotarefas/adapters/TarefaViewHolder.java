package com.example.atendealunos.gestaotarefas.adapters;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atendealunos.R;

public class TarefaViewHolder extends RecyclerView.ViewHolder {
    public final AppCompatTextView tv_titulo;
    public final AppCompatTextView tv_aluno;
    public final AppCompatTextView tv_professor;
    public final AppCompatTextView tv_disciplina;
    public final AppCompatTextView tv_pontuacao;
    public final AppCompatImageButton bt_envio_aluno;

    public TarefaViewHolder(@NonNull View itemView) {
        super(itemView);

        this.tv_titulo = itemView.findViewById(R.id.tv_titulo);
        this.tv_aluno = itemView.findViewById(R.id.tv_aluno);
        this.tv_professor = itemView.findViewById(R.id.tv_professor);
        this.tv_disciplina = itemView.findViewById(R.id.tv_disciplina);
        this.tv_pontuacao = itemView.findViewById(R.id.tv_pontuacao);

        this.bt_envio_aluno = itemView.findViewById(R.id.imgb_envio_aluno);
    }
}

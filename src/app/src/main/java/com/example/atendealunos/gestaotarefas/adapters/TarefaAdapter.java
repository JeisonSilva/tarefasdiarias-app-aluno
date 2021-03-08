package com.example.atendealunos.gestaotarefas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atendealunos.R;
import com.example.atendealunos.gestaotarefas.dtos.TarefaDisponivelDto;
import com.example.atendealunos.gestaotarefas.fragments.ListaTarefasDisponiveisDal;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaViewHolder> {

    private final TarefaDisponivelDto[] tarefasDisponiveis;
    private final Context context;

    public TarefaAdapter(Context context, TarefaDisponivelDto[] tarefasDisponiveis) {
        this.tarefasDisponiveis = tarefasDisponiveis;
        this.context = context;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarefa_card, parent, false);
        return new TarefaViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, final int position) {

        String titulo = String.format("%s",this.tarefasDisponiveis[position].getTitulo());
        String aluno = String.format("Aluno: %s",this.tarefasDisponiveis[position].getAluno());
        String professor = String.format("Professor: %s",this.tarefasDisponiveis[position].getProfessor());
        String disciplina = String.format("Disciplina: %s",this.tarefasDisponiveis[position].getDisciplina());
        String pontuacao = String.format("Pontuação: %s",this.tarefasDisponiveis[position].getPontuacao());

        holder.tv_titulo.setText(titulo);
        holder.tv_aluno.setText(aluno);
        holder.tv_professor.setText(professor);
        holder.tv_disciplina.setText(disciplina);
        holder.tv_pontuacao.setText(pontuacao);

        holder.bt_envio_aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListaTarefasDisponiveisDal listaTarefasDisponiveisDal =(ListaTarefasDisponiveisDal) context;
                listaTarefasDisponiveisDal.exibirNotificacaoEnvioTarefaProfessor(tarefasDisponiveis[position].getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.tarefasDisponiveis.length;
    }
}

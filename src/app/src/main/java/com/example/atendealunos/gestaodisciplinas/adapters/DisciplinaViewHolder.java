package com.example.atendealunos.gestaodisciplinas.adapters;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atendealunos.R;

public class DisciplinaViewHolder extends RecyclerView.ViewHolder {
    public final AppCompatTextView text_view_id;
    public final AppCompatTextView text_view_descricao;

    public DisciplinaViewHolder(@NonNull View itemView) {
        super(itemView);

        this.text_view_id = itemView.findViewById(R.id.text_view_id);
        this.text_view_descricao = itemView.findViewById(R.id.text_view_descricao);
    }
}

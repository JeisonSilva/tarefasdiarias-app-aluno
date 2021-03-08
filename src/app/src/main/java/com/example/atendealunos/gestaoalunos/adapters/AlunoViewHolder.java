package com.example.atendealunos.gestaoalunos.adapters;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atendealunos.R;

public class AlunoViewHolder extends RecyclerView.ViewHolder {
    public final AppCompatTextView text_view_id;
    public final AppCompatTextView text_view_descricao;
    public final ImageView img_logo;

    public AlunoViewHolder(@NonNull View itemView) {
        super(itemView);

        this.img_logo = itemView.findViewById(R.id.img_logo);
        this.text_view_id = itemView.findViewById(R.id.text_view_id);
        this.text_view_descricao = itemView.findViewById(R.id.text_view_descricao);
    }
}

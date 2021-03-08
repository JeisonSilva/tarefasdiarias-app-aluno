package com.example.atendealunos.gestaodisciplinas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atendealunos.HomeDal;
import com.example.atendealunos.R;
import com.example.atendealunos.gestaodisciplinas.DisciplinaDto;

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaViewHolder> {

    private final DisciplinaDto[] disciplinaDto;
    private final HomeDal homeDal;

    public DisciplinaAdapter (Context context, DisciplinaDto[] disciplinaDto) {
        this.disciplinaDto = disciplinaDto;
        this.homeDal = (HomeDal)context;
    }

    @NonNull
    @Override
    public DisciplinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_card, parent, false);
        return new DisciplinaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisciplinaViewHolder holder, int position) {
        int id = this.disciplinaDto[position].getId();
        String descricao = this.disciplinaDto[position].getDescricao();

        holder.text_view_id.setText(String.format("ID: %s", id));
        holder.text_view_descricao.setText(String.format("Descrição: %s", descricao));
    }

    @Override
    public int getItemCount() {
        return this.disciplinaDto.length;
    }
}

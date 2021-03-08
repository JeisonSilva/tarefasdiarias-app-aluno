package com.example.atendealunos.gestaotarefas.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atendealunos.R;
import com.example.atendealunos.gestaotarefas.adapters.TarefaAdapter;
import com.example.atendealunos.gestaotarefas.dtos.TarefaDisponivelDto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaTarefasDisponiveisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaTarefasDisponiveisFragment extends Fragment implements ListaTarefasDal{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TITULO = "titulo";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rc_tarefasEmDigitacao;
    private FloatingActionButton fb_nova_tarefa;
    private ListaTarefasDisponiveisDal listaTarefasDisponiveisDal;
    private SwipeRefreshLayout swipeRefreshLayout;

    public ListaTarefasDisponiveisFragment() {
        // Required empty public constructor
    }

    public static ListaTarefasDisponiveisFragment newInstance(String titulo) {
        ListaTarefasDisponiveisFragment fragment = new ListaTarefasDisponiveisFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITULO, titulo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            getActivity().setTitle(getArguments().getString(ARG_TITULO));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_tarefas_em_digitacao, container, false);
        this.configurar(view);
        this.carregarTarefasDisponiveis();


        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        this.listaTarefasDisponiveisDal = (ListaTarefasDisponiveisDal)context;
    }
    
    private void configurar(View view) {
        this.fb_nova_tarefa = view.findViewById(R.id.fb_nova_tarefa);
        this.fb_nova_tarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaTarefasDisponiveisDal.exibirTelaParaCriarNovaTarefa();
            }
        });

        this.swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        this.rc_tarefasEmDigitacao = view.findViewById(R.id.rc_lista_tarefas_em_digitacao);
        this.rc_tarefasEmDigitacao.setLayoutManager(new LinearLayoutManager(getContext()));

        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                carregarTarefasDisponiveis();
            }
        });

        this.swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    private void carregarTarefasDisponiveis() {
        this.listaTarefasDisponiveisDal.carregarTarefasDisponiveis((ListaTarefasDal)this);
    }

    @Override
    public void carregarTarefasDisponiveis(TarefaDisponivelDto[] tarefasEmDigitacao) {
        TarefaAdapter adapter = new TarefaAdapter(getContext(), tarefasEmDigitacao);
        this.rc_tarefasEmDigitacao.setAdapter(adapter);
        this.swipeRefreshLayout.setRefreshing(false);
    }
}
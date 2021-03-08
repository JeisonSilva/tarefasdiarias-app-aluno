package com.example.atendealunos.gestaoalunos.fragments;

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

import com.example.atendealunos.HomeDal;
import com.example.atendealunos.R;
import com.example.atendealunos.gestaoalunos.adapters.AlunoAdapter;
import com.example.atendealunos.gestaotarefas.dtos.AlunoMatriculadoDto;

public class ListaAlunosFragment extends Fragment implements ListaAlunosDal {
    private HomeDal homeDal;
    private RecyclerView rc_lista_alunos;
    private SwipeRefreshLayout swipeRefreshLayout;

    private static final String ARG_TITULO = "TITULO";

    public ListaAlunosFragment() {
        // Required empty public constructor
    }

    public static ListaAlunosFragment newInstance(Context context, String titulo) {
        ListaAlunosFragment fragment = new ListaAlunosFragment();
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
        View view = inflater.inflate(R.layout.fragment_lista_alunos, container, false);
        this.configurar(view);
        this.homeDal.listarAlunosMatriculados((ListaAlunosDal) this);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        this.homeDal = (HomeDal)context;
    }

    private void configurar(View view) {
        this.rc_lista_alunos = view.findViewById(R.id.rc_lista_alunos);
        this.swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homeDal.listarAlunosMatriculados((ListaAlunosDal) ListaAlunosFragment.this);
            }
        });

        this.swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public void carregarListaDeAlunos(AlunoMatriculadoDto[] alunoMatriculadoDtos) {
        AlunoAdapter adapter = new AlunoAdapter(getContext(), alunoMatriculadoDtos);
        this.rc_lista_alunos.setLayoutManager(new LinearLayoutManager(getContext()));
        this.rc_lista_alunos.setAdapter(adapter);
    }
}
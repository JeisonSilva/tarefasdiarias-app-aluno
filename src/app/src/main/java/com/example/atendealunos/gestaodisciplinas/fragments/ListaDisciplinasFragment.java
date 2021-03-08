package com.example.atendealunos.gestaodisciplinas.fragments;

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
import com.example.atendealunos.gestaodisciplinas.DisciplinaDto;
import com.example.atendealunos.gestaodisciplinas.adapters.DisciplinaAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaDisciplinasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaDisciplinasFragment extends Fragment implements ListaDisciplinasDal{

    private static final String ARG_TITULO = "TITULO";

    private HomeDal homeDal;
    private RecyclerView rc_lista_disciplinas;
    private SwipeRefreshLayout swipeRefreshLayout;

    public ListaDisciplinasFragment() {
        // Required empty public constructor
    }

    public static ListaDisciplinasFragment newInstance(String titulo) {
        ListaDisciplinasFragment fragment = new ListaDisciplinasFragment();
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
        View view = inflater.inflate(R.layout.fragment_lista_disciplinas, container, false);
        this.configurar(view);
        this.homeDal.listarDisiplinas((ListaDisciplinasDal)this);
        return view;
    }

    private void configurar(View view) {
        this.rc_lista_disciplinas = view.findViewById(R.id.rc_lista_disciplinas);
        this.swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homeDal.listarDisiplinas((ListaDisciplinasDal)ListaDisciplinasFragment.this);
            }
        });

        this.swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        this.homeDal = (HomeDal)context;
    }

    @Override
    public void carregarDisciplinas(DisciplinaDto[] disciplinasDtos) {
        DisciplinaAdapter adapter = new DisciplinaAdapter(getContext(), disciplinasDtos);
        this.rc_lista_disciplinas.setLayoutManager(new LinearLayoutManager(getContext()));
        this.rc_lista_disciplinas.setAdapter(adapter);
        this.swipeRefreshLayout.setRefreshing(false);
    }
}
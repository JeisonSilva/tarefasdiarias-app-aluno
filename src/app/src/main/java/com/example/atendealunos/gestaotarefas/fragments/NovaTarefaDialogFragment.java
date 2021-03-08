package com.example.atendealunos.gestaotarefas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.example.atendealunos.HomeDal;
import com.example.atendealunos.R;
import com.example.atendealunos.gestaotarefas.dtos.AlunoMatriculadoDto;
import com.example.atendealunos.gestaotarefas.dtos.DisciplinaDto;
import com.example.atendealunos.gestaotarefas.dtos.NovaTarefaDto;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class NovaTarefaDialogFragment extends BaseDialogFragment {

    private final HomeDal homeDal;
    private TextInputLayout text_input_titulo;
    private TextInputLayout text_input_pontuacao;
    private MaterialBetterSpinner bs_aluno;
    private MaterialBetterSpinner bs_disciplina;
    private TextInputEditText edit_input_titulo;
    private TextInputEditText edit_input_pontuacao;
    private AppCompatButton bt_cancelar;
    private AppCompatButton bt_salvar;
    private InclusaoTarefaDal inclusaoTarefaDal;
    private AlunoMatriculadoDto alunoSelecionado;
    private DisciplinaDto disciplinaSelecionada;

    public NovaTarefaDialogFragment(ListagemAlunosDal listagemAlunosDal, ListagemDisciplinaDal listagemDisciplinaDal, HomeDal homeDal) {
        super(listagemAlunosDal, listagemDisciplinaDal);

        this.homeDal = homeDal;
    }

    public static NovaTarefaDialogFragment newInstance(Context context) {

        Bundle args = new Bundle();

        NovaTarefaDialogFragment fragment = new NovaTarefaDialogFragment((ListagemAlunosDal)context, (ListagemDisciplinaDal)context, (HomeDal)context);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nova_tarefa, container, false);
        this.configurar(view);
        this.carregarAlunos();
        this.carregarDisciplinas();

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        this.inclusaoTarefaDal = (InclusaoTarefaDal) context;
    }

    private void configurar(View view) {
        this.text_input_titulo = view.findViewById(R.id.text_input_titulo);
        this.text_input_pontuacao = view.findViewById(R.id.text_input_pontuacao);
        
        this.bs_aluno = view.findViewById(R.id.bs_aluno);
        this.bs_disciplina = view.findViewById(R.id.bs_disciplina);
        this.edit_input_titulo = view.findViewById(R.id.edit_input_titulo);
        this.edit_input_pontuacao = view.findViewById(R.id.edit_input_pontuacao);
        
        this.bt_cancelar = view.findViewById(R.id.bt_cancelar);
        this.bt_salvar = view.findViewById(R.id.bt_salvar);

        this.bs_aluno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                alunoSelecionado = (AlunoMatriculadoDto)adapterView.getItemAtPosition(i);
            }
        });

        this.bs_disciplina.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                disciplinaSelecionada = (DisciplinaDto)adapterView.getItemAtPosition(i);
            }
        });

        this.bt_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        this.bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NovaTarefaDto novaTarefaDto = criarNovaTarefa();
                inclusaoTarefaDal.criarNovaTarefa(novaTarefaDto);
                dismiss();
            }
        });

        this.bt_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private NovaTarefaDto criarNovaTarefa() {
        String titulo = String.valueOf(this.edit_input_titulo.getText());
        AlunoMatriculadoDto aluno = alunoSelecionado;
        DisciplinaDto disciplina = disciplinaSelecionada;
        double pontuacao = Double.valueOf(this.edit_input_pontuacao.getText().toString());

        return new NovaTarefaDto(disciplina.getId(), aluno.getEmail(), homeDal.obterPerfil().getEmail(), pontuacao, titulo);
    }

    @Override
    public void carregarListaDeAlunos(AlunoMatriculadoDto[] alunoMatriculadoDtos) {
        ArrayAdapter<AlunoMatriculadoDto> adater = new ArrayAdapter<AlunoMatriculadoDto>(getContext(), android.R.layout.simple_dropdown_item_1line, alunoMatriculadoDtos);
        bs_aluno.setAdapter(adater);
    }

    @Override
    public void carregarDisciplinas(DisciplinaDto[] disciplinasDtos) {
        ArrayAdapter<DisciplinaDto> adater = new ArrayAdapter<DisciplinaDto>(getContext(), android.R.layout.simple_dropdown_item_1line, disciplinasDtos);
        bs_disciplina.setAdapter(adater);
    }
}

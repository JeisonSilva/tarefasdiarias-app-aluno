package com.example.atendealunos.gestaologin.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.atendealunos.R;
import com.example.atendealunos.gestaologin.dtos.LoginDto;
import com.example.atendealunos.LoginDal;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AutorizacaoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AutorizacaoFragment extends Fragment implements AutorizacaoDal{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AppCompatButton bt_login;
    private LoginDal loginDal;
    private TextInputLayout layout_input_email;
    private TextInputLayout layout_input_senha;
    private TextInputEditText edit_input_email;
    private TextInputEditText edit_input_senha;
    private ProgressBar progressbar;
    private LoginDto loginDto;

    public AutorizacaoFragment() {
        // Required empty public constructor
    }


    public static AutorizacaoFragment newInstance() {
        AutorizacaoFragment fragment = new AutorizacaoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_autorizacao, container, false);
        this.configurar(view);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        this.loginDal = (LoginDal) context;
    }

    private void configurar(View view) {
        this.layout_input_email = view.findViewById(R.id.text_input_email);
        this.layout_input_senha = view.findViewById(R.id.text_input_senha);
        this.edit_input_email = view.findViewById(R.id.edit_input_email);
        this.edit_input_senha = view.findViewById(R.id.edit_input_senha);
        this.bt_login = view.findViewById(R.id.bt_login);
        this.progressbar = view.findViewById(R.id.progressbar);
        this.finalizarProgressoLogin();

        this.bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(edit_input_email.getText());
                String senha = String.valueOf(edit_input_senha.getText());
                loginDto = new LoginDto(email, senha);
                loginDal.logar(AutorizacaoFragment.this);

            }
        });
    }

    @Override
    public LoginDto obterLogin() {
        return this.loginDto;
    }

    @Override
    public void finalizarProgressoLogin() {
        this.progressbar.setVisibility(View.GONE);
    }

    @Override
    public void iniciarProgressoLogin() {
        this.progressbar.setVisibility(View.VISIBLE);
    }
}
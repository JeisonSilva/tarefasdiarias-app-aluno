package com.example.atendealunos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.atendealunos.gestaoalunos.fragments.ListaAlunosDal;
import com.example.atendealunos.gestaoalunos.fragments.ListaAlunosFragment;
import com.example.atendealunos.gestaodisciplinas.fragments.ListaDisciplinasDal;
import com.example.atendealunos.gestaodisciplinas.fragments.ListaDisciplinasFragment;
import com.example.atendealunos.gestaologin.dtos.PerfilDto;
import com.example.atendealunos.gestaotarefas.dtos.AlunoMatriculadoDto;
import com.example.atendealunos.gestaotarefas.dtos.DisciplinaDto;
import com.example.atendealunos.gestaotarefas.dtos.EdicaoTarefaDto;
import com.example.atendealunos.gestaotarefas.dtos.NovaTarefaDto;
import com.example.atendealunos.gestaotarefas.dtos.TarefaDisponivelDto;
import com.example.atendealunos.gestaotarefas.fragments.AlunosMatriculadosDal;
import com.example.atendealunos.gestaotarefas.fragments.DisciplinasAtivasDal;
import com.example.atendealunos.gestaotarefas.fragments.EdicaoTarefaDal;
import com.example.atendealunos.gestaotarefas.fragments.EdicaoTarefaDialogFragment;
import com.example.atendealunos.gestaotarefas.fragments.InclusaoTarefaDal;
import com.example.atendealunos.gestaotarefas.fragments.ListaTarefasDal;
import com.example.atendealunos.gestaotarefas.fragments.ListaTarefasDisponiveisDal;
import com.example.atendealunos.gestaotarefas.fragments.ListaTarefasDisponiveisFragment;
import com.example.atendealunos.gestaotarefas.fragments.ListagemAlunosDal;
import com.example.atendealunos.gestaotarefas.fragments.ListagemDisciplinaDal;
import com.example.atendealunos.gestaotarefas.fragments.ManutencaoDadosTarefaDal;
import com.example.atendealunos.gestaotarefas.fragments.NovaTarefaDialogFragment;
import com.example.atendealunos.gestaotarefas.fragments.PesquisaTarefaDal;
import com.example.atendealunos.utils.HttpsTrustManager;
import com.example.atendealunos.utils.UrlApi;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener,
        HomeDal,
        ListaTarefasDisponiveisDal,
        ManutencaoDadosTarefaDal,
        InclusaoTarefaDal,
        EdicaoTarefaDal,
        ListagemDisciplinaDal,
        ListagemAlunosDal {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private RequestQueue requestQueue;
    private PerfilDto perfil;
    private AppCompatTextView text_view_email_perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.toolbar = findViewById(R.id.toolbar);
        this.navigationView = findViewById(R.id.navigation_view);
        this.drawerLayout = findViewById(R.id.drawerLayout);

        this.configurar(this.toolbar);
        this.configurar(this.navigationView);
        this.configurar(this.drawerLayout, this.toolbar);

        this.requestQueue = Volley.newRequestQueue(this);

        if(getIntent().getExtras() != null) {
            Gson gson = new Gson();
            this.perfil = gson.fromJson(getIntent().getExtras().getString("perfil"), PerfilDto.class);
            this.carregarPerfil(this.perfil);
        }
    }

    private void carregarPerfil(PerfilDto perfil) {
        this.text_view_email_perfil = navigationView.getHeaderView(0).findViewById(R.id.nav_header_textView);
        this.text_view_email_perfil.setText(perfil.getEmail());
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.listarTarefasEmDigitacao();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        this.exibirTela(item);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_sair:
                logout(this.perfil.getEmail());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout(String email) {
        HttpsTrustManager.allowAllSSL();
        String URL = String.format(UrlApi.URL_PERFIL_LOGOF, email);
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        this.requestQueue.add(stringRequest);
    }

    private void configurar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }

    private void configurar(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void configurar(DrawerLayout drawerLayout, Toolbar toolbar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();
    }


    @Override
    public void listarTarefasEmDigitacao() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, ListaTarefasDisponiveisFragment.newInstance(getString(R.string.listatarefasdisponiveis_titulo)))
                .commit();
    }

    @Override
    public void exibirTela(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_gestao_tarefas:
                this.listarTarefasEmDigitacao();
                break;
            case R.id.nav_lista_disciplinas:
                this.exibirDisciplinas();
                break;
            case R.id.nav_lista_alunos:
                this.exibirAlunos();
                break;
        }
    }

    private void exibirAlunos() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, ListaAlunosFragment.newInstance(getBaseContext(), getString(R.string.listagemAluno_titulo)))
                .commit();
    }

    private void exibirDisciplinas() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, ListaDisciplinasFragment.newInstance(getString(R.string.listagemDisciplina_titulo)))
                .commit();
    }

    @Override
    public PerfilDto obterPerfil() {
        if(this.perfil != null) {
            return this.perfil;
        }
        return new PerfilDto();
    }

    @Override
    public void listarDisiplinas(final ListaDisciplinasDal listaDisciplinasDal) {
        HttpsTrustManager.allowAllSSL();
        String URL = UrlApi.URL_LISTA_DISCIPLINAS;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                com.example.atendealunos.gestaodisciplinas.DisciplinaDto[] disciplinasDtos = gson.fromJson(response, com.example.atendealunos.gestaodisciplinas.DisciplinaDto[].class);
                listaDisciplinasDal.carregarDisciplinas(disciplinasDtos);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        this.requestQueue.add(stringRequest);
    }

    @Override
    public void listarAlunosMatriculados(final ListaAlunosDal listaAlunosDal) {
        HttpsTrustManager.allowAllSSL();
        String URL = String.format(UrlApi.URL_LISTA_ALUNOS_MATRICULADOS, this.perfil.getEmail());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                AlunoMatriculadoDto[] alunoMatriculadoDtos = gson.fromJson(response, AlunoMatriculadoDto[].class);
                listaAlunosDal.carregarListaDeAlunos(alunoMatriculadoDtos);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        this.requestQueue.add(stringRequest);
    }

    @Override
    public void criarNovaTarefa(final NovaTarefaDto novaTarefaDto) {
        HttpsTrustManager.allowAllSSL();
        String URL = UrlApi.URL_SALVAR_TAREFA;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Tarefa registrada", Toast.LENGTH_LONG).show();
                listarTarefasEmDigitacao();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public byte[] getBody() {
                Gson gson = new Gson();
                String json = gson.toJson(novaTarefaDto);
                return json.getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        this.requestQueue.add(stringRequest);
    }

    @Override
    public void exibirTelaParaCriarNovaTarefa() {
        DialogFragment novaTarefaFragment = NovaTarefaDialogFragment.newInstance(this);
        novaTarefaFragment.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void exibirTelaParaEditarTarefa(int tarefaId) {
        DialogFragment edicaoFragment = EdicaoTarefaDialogFragment.newInstance(this, tarefaId);
        edicaoFragment.show(getSupportFragmentManager(), "dialog");

    }

    @Override
    public void exibirNotificacaoExclusaoTarefa(final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setMessage(R.string.dialog_exclusao)
                .setPositiveButton(R.string.dialog_OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                excluir(id);

            }
        }).setNegativeButton(R.string.dialog_Cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }

    @Override
    public void exibirNotificacaoEnvioTarefaProfessor(final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setMessage(R.string.dialog_notificacao_aluno)
                .setPositiveButton(R.string.dialog_OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        notificarTarefaPronta(id);

                    }
                }).setNegativeButton(R.string.dialog_Cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }

    private void notificarTarefaPronta(int id) {
        HttpsTrustManager.allowAllSSL();
        String URL = String.format(UrlApi.URL_NOTIFICAR_TAREFA_ENTREGUE, id);
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listarTarefasEmDigitacao();
                Toast.makeText(getApplicationContext(), "Parabéns! tarefa concluída", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        this.requestQueue.add(stringRequest);
    }

    @Override
    public void carregarTarefasDisponiveis(final ListaTarefasDal listaTarefasDal) {

        Cursor cursor = getContentResolver().query(
                Uri.parse("content://com.example.tarefasdiarias/tarefas"),
                new String[]{"ID", "TITULO", "PROFESSOR", "ALUNO", "DISCIPLINA", "PONTUACAO"}, "ALUNO=?", new String[]{perfil.getNome()}, null);
        cursor.moveToFirst();

        List<TarefaDisponivelDto> tarefaDisponivelDtos = new ArrayList<>();
        while (cursor.moveToNext()){
            TarefaDisponivelDto digitacaoDto = new TarefaDisponivelDto(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(3),
                    cursor.getString(2),
                    cursor.getString(4),
                    cursor.getDouble(5));

            tarefaDisponivelDtos.add(digitacaoDto);
        }

        listaTarefasDal.carregarTarefasDisponiveis(tarefaDisponivelDtos.toArray(new TarefaDisponivelDto[tarefaDisponivelDtos.size()]));
    }

    @Override
    public void editarTarefa(final EdicaoTarefaDto tarefa) {
        HttpsTrustManager.allowAllSSL();
        String URL = String.format(UrlApi.URL_EDITAR_TAREFA, tarefa.getId());
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Tarefa atualizada", Toast.LENGTH_LONG).show();
                listarTarefasEmDigitacao();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public byte[] getBody() {
                Gson gson = new Gson();
                String json = gson.toJson(tarefa);
                return json.getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        this.requestQueue.add(stringRequest);
    }

    @Override
    public void carregarTarefa(final PesquisaTarefaDal pesquisaTarefaDal, int tarefaId) {
        HttpsTrustManager.allowAllSSL();
        String URL = String.format(UrlApi.URL_LISTA_TAREFAS_EDICAO, tarefaId);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                EdicaoTarefaDto edicaoTarefaDto = gson.fromJson(response, EdicaoTarefaDto.class);
                pesquisaTarefaDal.carregarTarefaPorId(edicaoTarefaDto);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        this.requestQueue.add(stringRequest);

    }

    @Override
    public void excluir(int id) {
        HttpsTrustManager.allowAllSSL();
        String URL = String.format(UrlApi.URL_EXCLUIR_TAREFA, id);
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listarTarefasEmDigitacao();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        this.requestQueue.add(stringRequest);
    }

    @Override
    public void enviarTarefaParaAluno(int id) {

    }

    @Override
    public void listarAlunosDoProfessor(final AlunosMatriculadosDal alunosMatriculadosDal) {
        HttpsTrustManager.allowAllSSL();
        String URL = String.format(UrlApi.URL_LISTA_ALUNOS_MATRICULADOS, this.perfil.getEmail());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                AlunoMatriculadoDto[] alunoMatriculadoDtos = gson.fromJson(response, AlunoMatriculadoDto[].class);
                alunosMatriculadosDal.carregarListaDeAlunos(alunoMatriculadoDtos);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        this.requestQueue.add(stringRequest);
    }

    @Override
    public void listarDisciplinas(final DisciplinasAtivasDal disciplinasAtivasDal) {
        HttpsTrustManager.allowAllSSL();
        String URL = UrlApi.URL_LISTA_DISCIPLINAS;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                DisciplinaDto[] disciplinasDtos = gson.fromJson(response, DisciplinaDto[].class);
                disciplinasAtivasDal.carregarDisciplinas(disciplinasDtos);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        this.requestQueue.add(stringRequest);
    }
}
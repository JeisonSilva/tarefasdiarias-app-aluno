package com.example.atendealunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.atendealunos.gestaologin.dtos.SenhaDto;
import com.example.atendealunos.gestaologin.fragments.AutorizacaoDal;
import com.example.atendealunos.gestaologin.fragments.AutorizacaoFragment;
import com.example.atendealunos.utils.HttpsTrustManager;
import com.example.atendealunos.utils.UrlApi;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity implements LoginDal {

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.requestQueue = Volley.newRequestQueue(getApplicationContext());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, AutorizacaoFragment.newInstance())
                .commit();
    }

    @Override
    public void logar(final AutorizacaoDal autorizacaoDal) {
        final SenhaDto senhaDto = new SenhaDto(autorizacaoDal.obterLogin().getSenha());
        autorizacaoDal.iniciarProgressoLogin();
        HttpsTrustManager.allowAllSSL();
        String URL = String.format(UrlApi.URL_AUTENTICACAO_USUARIO, autorizacaoDal.obterLogin().getEmail());
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                autorizacaoDal.finalizarProgressoLogin();
                abrirPainelComPerfil(autorizacaoDal.obterLogin().getEmail());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse == null) {
                    Toast.makeText(getBaseContext(), "Não conseguimos encontrar o servidor!",Toast.LENGTH_LONG).show();
                    autorizacaoDal.finalizarProgressoLogin();
                    return;
                }


                switch (error.networkResponse.statusCode){
                    case 400:
                        alertar("Login inválido");
                        break;
                    case 404:
                        alertar("Login inválido");
                        break;
                    case 500:
                        alertar("Falha em nossos servidores");
                        autorizacaoDal.finalizarProgressoLogin();
                        break;
                }

            }
        }){
            @Override
            public byte[] getBody() {
                Gson gson = new Gson();
                String json = gson.toJson(senhaDto);
                return json.getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        this.requestQueue.add(stringRequest);
    }

    private void alertar(String mensagem) {
        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();
    }

    private void abrirPainelComPerfil(String email) {
        HttpsTrustManager.allowAllSSL();
        String URL = String.format(UrlApi.URL_PERFIL, email);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                intent.putExtra("perfil", response);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        this.requestQueue.add(stringRequest);
    }
}
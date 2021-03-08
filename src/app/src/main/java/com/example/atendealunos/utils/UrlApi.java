package com.example.atendealunos.utils;

public class UrlApi {
    private static final String URL_BASE = "https://192.168.1.102:8443";
    public static final String URL_LISTA_ALUNOS_MATRICULADOS = URL_BASE + "/api/v1/alunos/professor/%s";
    public static final String URL_LISTA_DISCIPLINAS = URL_BASE + "/api/v1/disciplinas";
    public static final String URL_LISTA_TAREFAS_EM_DIGITACAO = URL_BASE + "/api/v1/tarefas/professor/%s";
    public static final String URL_LISTA_TAREFAS_EDICAO = URL_BASE + "/api/v1/tarefas/%d";
    public static final String URL_SALVAR_TAREFA = URL_BASE + "/api/v1/tarefas";
    public static final String URL_EDITAR_TAREFA = URL_BASE + "/api/v1/tarefas/%d";
    public static final String URL_EXCLUIR_TAREFA = URL_BASE + "/api/v1/tarefas/%d";
    public static final String URL_NOTIFICAR_TAREFA_DISPONIVEL = URL_BASE + "/api/v1/tarefas/%d/finalizado";
    public static final String URL_NOTIFICAR_TAREFA_ENTREGUE = URL_BASE + "/api/v1/tarefas/%d/entrega";

    public static final String URL_AUTENTICACAO_USUARIO = URL_BASE + "/api/v1/autenticacoes/%s/autenticacao";
    public static final String URL_PERFIL = URL_BASE + "/api/v1/autenticacoes/%s/perfil";
    public static final String URL_PERFIL_LOGOF = URL_BASE + "/api/v1/autenticacoes/%s/logout";
}

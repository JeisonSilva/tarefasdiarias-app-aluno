package com.example.atendealunos.gestaotarefas.dtos;

public class NovaTarefaDto {
    int disciplina;
    String emailAluno;
    String emailProfessor;
    double pontuacao;
    String titulo;

    public NovaTarefaDto(int disciplina, String emailAluno, String emailProfessor, double pontuacao, String titulo) {
        this.disciplina = disciplina;
        this.emailAluno = emailAluno;
        this.emailProfessor = emailProfessor;
        this.pontuacao = pontuacao;
        this.titulo = titulo;
    }

    public int getDisciplina() {
        return disciplina;
    }

    public String getEmailAluno() {
        return emailAluno;
    }

    public String getEmailProfessor() {
        return emailProfessor;
    }

    public double getPontuacao() {
        return pontuacao;
    }

    public String getTitulo() {
        return titulo;
    }
}

package com.alancrist.agenda.model;

import java.sql.Time;

public class Ligacao {

    private long id;
    private String numero;
    private Contato contato;
    private int qntLigacao;
    private String hora;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public int getQntLigacao() {
        return qntLigacao;
    }

    public void setQntLigacao(int qntLigacao) {
        this.qntLigacao = qntLigacao;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}

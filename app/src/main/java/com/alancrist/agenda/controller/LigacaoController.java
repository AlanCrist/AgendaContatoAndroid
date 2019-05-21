package com.alancrist.agenda.controller;

import com.alancrist.agenda.dao.LigacaoDAO;
import com.alancrist.agenda.dbHelper.ConexaoSQLite;
import com.alancrist.agenda.model.Contato;
import com.alancrist.agenda.model.Ligacao;

import java.util.List;

public class LigacaoController {

    private final LigacaoDAO ligacaoDAO;

    public LigacaoController(ConexaoSQLite pConexaoSQLite){
        ligacaoDAO = new LigacaoDAO(pConexaoSQLite);
    }

    public long salvarLigacaoController(Ligacao pLigacao){
        return this.ligacaoDAO.salvarLigacaoDAO(pLigacao);
    }

    public List<Ligacao> getListaLigacaoController(){
        return this.ligacaoDAO.getListaLigacao();
    }

    public Contato getContatoNumeroContent(String pNumero){
        return this.ligacaoDAO.getContatoNumeroContent(pNumero);
    }

    public boolean excluirLigacaoController(long pIdLigacao){
        return this.ligacaoDAO.excluirLigacaoDAO(pIdLigacao);
    }

    public boolean atualizarLigacaoController(Ligacao pLigacao){
        return this.ligacaoDAO.atualizarLigacaoDAO(pLigacao);
    }

}

package com.alancrist.agenda.controller;

import com.alancrist.agenda.dao.ContatoDAO;
import com.alancrist.agenda.dbHelper.ConexaoSQLite;
import com.alancrist.agenda.model.Contato;

import java.util.List;

public class ContatoController {

    private final ContatoDAO contatoDAO;

    public ContatoController(ConexaoSQLite pConexaoSQLite){
        contatoDAO = new ContatoDAO(pConexaoSQLite);
    }

    public long salvarContatoController(Contato pContato){
        return this.contatoDAO.salvarContatoDAO(pContato);
    }

    public List<Contato> getListaContatosController(){
        return this.contatoDAO.getListaContatos();
    }

    public boolean excluirContatoController(long pIdContato){
        return this.contatoDAO.excluirContatoDAO(pIdContato);
    }

    public boolean atualizarContatoController(Contato pContato){
        return this.contatoDAO.atualizarContatoDAO(pContato);
    }

}

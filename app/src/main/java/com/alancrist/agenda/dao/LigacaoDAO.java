package com.alancrist.agenda.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.alancrist.agenda.dbHelper.ConexaoSQLite;
import com.alancrist.agenda.model.Contato;
import com.alancrist.agenda.model.Ligacao;

import java.util.ArrayList;
import java.util.List;

public class LigacaoDAO {

    private final ConexaoSQLite conexaoSQLite;

    public LigacaoDAO(ConexaoSQLite conexaoSQLite) {
        this.conexaoSQLite = conexaoSQLite;
    }

    public long salvarLigacaoDAO(Ligacao pLigacao){

        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();

        try {

            ContentValues contentValues = new ContentValues();
            contentValues.put("numero", pLigacao.getNumero());
            contentValues.put("id_contato", pLigacao.getContato().getId());
            contentValues.put("quantidade_ligacao", pLigacao.getQntLigacao());
            contentValues.put("hora", pLigacao.getHora());

            long idLigacaoInserido = db.insert("ligacao",null,contentValues);

            return idLigacaoInserido;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (db != null){
                db.close();
            }
        }
        return 0;
    }

    public Contato getContatoNumeroContent(String numero){

        SQLiteDatabase db = null;
        Cursor cursor;
        Contato contato = null;
        String query = "SELECT * FROM contato WHERE numero = ?";

        try {
            db = this.conexaoSQLite.getReadableDatabase();
            cursor = db.rawQuery(query, new String[]{numero});
            if (cursor != null){
                cursor.moveToFirst();
                if (cursor.getCount() > 0){
                    contato = new Contato();
                    contato.setId(cursor.getLong(0));
                    contato.setNome(cursor.getString(1));
                    contato.setSobrenome(cursor.getString(2));
                    contato.setNumero(cursor.getString(3));
                }
            }
        } catch (Exception e){
            Log.e("ERRO CONTATO", "Erro ao retornar o contato");
            return null;
        } finally {
            if (db != null){
                db.close();
            }
        }
        return contato;
    }

    public List<Ligacao> getListaLigacao(){

        List<Ligacao> listaLigacoes = new ArrayList<>();

        SQLiteDatabase db = null;
        Cursor cursor;

        String query = "SELECT * FROM ligacao";

        try {
            db = this.conexaoSQLite.getReadableDatabase();

            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()){

                Ligacao ligacao = null;

                do {

                    ligacao = new Ligacao();
                    ligacao.setId(cursor.getLong(0));
                    ligacao.setNumero(cursor.getString(1));
                    ligacao.setContato(getContatoNumeroContent(ligacao.getNumero()));
                    ligacao.setQntLigacao(cursor.getInt(3));
                    ligacao.setHora(cursor.getString(4));

                    listaLigacoes.add(ligacao);

                } while (cursor.moveToNext());

            }
        } catch (Exception e){
            Log.e("ERRO LISTA LIGACOES", "Erro ao retornar as ligacoes");
            return null;
        } finally {
            if (db != null){
                db.close();
            }
        }
        return listaLigacoes;
    }

    public boolean excluirLigacaoDAO(long pIdLigacao){

        SQLiteDatabase db = null;

        try {

            db = this.conexaoSQLite.getWritableDatabase();

            db.delete(
                    "ligacao",
                    "id = ?",
                    new String[]{String.valueOf(pIdLigacao)}
            );

        } catch (Exception e){

            Log.e("LIGACAODAO", "NÃO FOI POSSÍVEL DELETAR LIGACAO");
            return false;
        } finally {

            if (db != null){
                db.close();
            }
        }

        return true;

    }

    public boolean atualizarLigacaoDAO(Ligacao pLigacao){

        SQLiteDatabase db = null;

        try {

            db = this.conexaoSQLite.getWritableDatabase();

            ContentValues ligacaoAtributos = new ContentValues();
            ligacaoAtributos.put("numero", pLigacao.getNumero());
            ligacaoAtributos.put("id_contato", pLigacao.getContato().getId());
            ligacaoAtributos.put("quantidade_ligacao", pLigacao.getQntLigacao());
            ligacaoAtributos.put("hora", pLigacao.getHora());
            int atualizado = db.update("ligacao",
                    ligacaoAtributos,
                    "id = ?",
                    new String[]{String.valueOf(pLigacao.getId())}
            );
            if (atualizado > 0){
                return true;
            }

        }catch (Exception e){
            Log.e("LIGACAODAO", "NÃO FOI POSSIVEL ATUALIZAR A LIGACAO");

        }finally {
            if (db != null){
                db.close();
            }
        }

        return false;

    }

}

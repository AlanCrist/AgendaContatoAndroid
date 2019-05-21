package com.alancrist.agenda.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.alancrist.agenda.dbHelper.ConexaoSQLite;
import com.alancrist.agenda.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    private final ConexaoSQLite conexaoSQLite;

    public ContatoDAO(ConexaoSQLite conexaoSQLite) {
        this.conexaoSQLite = conexaoSQLite;
    }

    public long salvarContatoDAO(Contato pContato){

        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();

        try {

            ContentValues contentValues = new ContentValues();
            contentValues.put("nome", pContato.getNome());
            contentValues.put("sobrenome", pContato.getSobrenome());
            contentValues.put("numero", pContato.getNumero());

            long idContatoInserido = db.insert("contato",null,contentValues);

            return idContatoInserido;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (db != null){
                db.close();
            }
        }
        return 0;
    }

    public List<Contato> getListaContatos(){

        List<Contato> listaContatos = new ArrayList<>();

        SQLiteDatabase db = null;
        Cursor cursor;

        String query = "SELECT * FROM contato";

        try {
            db = this.conexaoSQLite.getReadableDatabase();

            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()){

                Contato contato = null;

                do {

                    contato = new Contato();
                    contato.setId(cursor.getLong(0));
                    contato.setNome(cursor.getString(1));
                    contato.setSobrenome(cursor.getString(2));
                    contato.setNumero(cursor.getString(3));

                    listaContatos.add(contato);

                } while (cursor.moveToNext());

            }
        } catch (Exception e){
            Log.e("ERRO LISTA CONTATOS", "Erro ao retornar os contatos");
            return null;
        } finally {
            if (db != null){
                db.close();
            }
        }
        return listaContatos;
    }

    public boolean excluirContatoDAO(long pIdContato){

        SQLiteDatabase db = null;

        try {

            db = this.conexaoSQLite.getWritableDatabase();

            db.delete(
                    "contato",
                    "id = ?",
                    new String[]{String.valueOf(pIdContato)}
            );

        } catch (Exception e){

            Log.e("CONTATODAO", "NÃO FOI POSSÍVEL DELETAR CONTATO");
            return false;
        } finally {

            if (db != null){
                db.close();
            }
        }

        return true;

    }

    public boolean atualizarContatoDAO(Contato pContato){

        SQLiteDatabase db = null;

        try {

            db = this.conexaoSQLite.getWritableDatabase();

            ContentValues contatoAtributos = new ContentValues();
            contatoAtributos.put("nome",pContato.getNome());
            contatoAtributos.put("sobrenome", pContato.getSobrenome());
            contatoAtributos.put("numero", pContato.getNumero());
            int atualizado = db.update("contato",
                    contatoAtributos,
                    "id = ?",
                    new String[]{String.valueOf(pContato.getId())}
                    );
            if (atualizado > 0){
                return true;
            }

        }catch (Exception e){
            Log.e("CONTATODAO", "NÃO FOI POSSIVEL ATUALIZAR O CONTATO");

        }finally {
            if (db != null){
                db.close();
            }
        }

        return false;

    }
}

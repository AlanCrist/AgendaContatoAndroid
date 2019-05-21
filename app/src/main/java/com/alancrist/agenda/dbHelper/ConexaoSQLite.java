package com.alancrist.agenda.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoSQLite extends SQLiteOpenHelper {

    private static ConexaoSQLite INSTANCIA_CONEXAO;
    private static final int VERSAO_DB = 1;
    private static final String NOME_DB = "agenda_app";

    public ConexaoSQLite(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    public static ConexaoSQLite getInstance(Context context){

        if(INSTANCIA_CONEXAO == null){
            INSTANCIA_CONEXAO = new ConexaoSQLite(context);
        }
        return INSTANCIA_CONEXAO;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlTableContato =
                "CREATE TABLE IF NOT EXISTS contato" +
                        "("  +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nome TEXT," +
                        "sobrenome TEXT," +
                        "numero TEXT" +
                        ")";

        db.execSQL(sqlTableContato);

        String sqlTableLigacao =
                "CREATE TABLE IF NOT EXISTS ligacao" +
                        "("  +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "numero TEXT," +
                        "id_contato INTEGER," +
                        "quantidade_ligacao INTEGER," +
                        "hora TEXT" +
                        ")";

        db.execSQL(sqlTableLigacao);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

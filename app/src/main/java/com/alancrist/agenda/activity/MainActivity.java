package com.alancrist.agenda.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alancrist.agenda.R;
import com.alancrist.agenda.dbHelper.ConexaoSQLite;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexaoSQLite.getInstance(this);

    }

    public void abrirDiscador(View view){

        Intent intent = new Intent(this, DiscadorActivity.class);
        startActivity(intent);

    }

    public void abrirContatos(View view){

        Intent intent = new Intent(this, ContatoListActivity.class);
        startActivity(intent);

    }

    public void abrirNumerosDiscados(View view){

        Intent intent = new Intent(this, UltimasLigacoesActivity.class);
        startActivity(intent);

    }
}

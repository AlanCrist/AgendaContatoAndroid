package com.alancrist.agenda.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alancrist.agenda.R;
import com.alancrist.agenda.adapter.LigacaoAdapter;
import com.alancrist.agenda.controller.LigacaoController;
import com.alancrist.agenda.dbHelper.ConexaoSQLite;
import com.alancrist.agenda.model.Contato;
import com.alancrist.agenda.model.Ligacao;

public class LigacaoActivity extends AppCompatActivity {

    private TextView nomeLigacao;
    private TextView numeroLigacao;
    private Contato ligacaoContato;
    private Ligacao ligacao = new Ligacao();

    Bundle dados = null;

    private LigacaoController ligacaoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ligacao);


        dados = getIntent().getExtras();
        String numero = dados.getString("NUMERO");
        numeroLigacao = findViewById(R.id.numeroLigacao);
        numeroLigacao.setText(numero);
        nomeLigacao = findViewById(R.id.nomeLigacao);

        ligacaoController = new LigacaoController(ConexaoSQLite.getInstance(LigacaoActivity.this));
        ligacaoContato = this.ligacaoController.getContatoNumeroContent(numero);

        this.ligacao.setHora("00:00");
        this.ligacao.setQntLigacao(1);

        if (ligacaoContato != null){
            nomeLigacao.setText(ligacaoContato.getNome());
            this.ligacao.setNumero(ligacaoContato.getNumero());
            this.ligacao.setContato(ligacaoContato);
            ligacaoController.salvarLigacaoController(this.ligacao);
        } else{
            nomeLigacao.setText("Desconhecido");
            this.ligacao.setNumero(numeroLigacao.getText().toString());
            this.ligacao.setContato(null);
            ligacaoController.salvarLigacaoController(this.ligacao);
        }

    }

    public void cancelaLigacao(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}

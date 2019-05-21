package com.alancrist.agenda.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alancrist.agenda.R;
import com.alancrist.agenda.adapter.LigacaoAdapter;
import com.alancrist.agenda.controller.LigacaoController;
import com.alancrist.agenda.dbHelper.ConexaoSQLite;
import com.alancrist.agenda.model.Contato;
import com.alancrist.agenda.model.Ligacao;

import java.util.List;

public class UltimasLigacoesActivity extends AppCompatActivity {

    private ListView listLigacoes;
    private List<Ligacao> ligacoes;
    private LigacaoAdapter adapterLigacao;
    private LigacaoController ligacaoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimas_ligacoes);

        ligacaoController = new LigacaoController(ConexaoSQLite.getInstance(UltimasLigacoesActivity.this));
        ligacoes = ligacaoController.getListaLigacaoController();

        this.listLigacoes = findViewById(R.id.listViewLigacoes);

        this.adapterLigacao = new LigacaoAdapter(UltimasLigacoesActivity.this, this.ligacoes);

        this.listLigacoes.setAdapter(this.adapterLigacao);

        this.listLigacoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final Ligacao ligacaoSelecionada = (Ligacao) adapterLigacao.getItem(position);

                AlertDialog.Builder janelaDeEscolha = new AlertDialog.Builder(UltimasLigacoesActivity.this);

                janelaDeEscolha.setTitle("Escolha:");
                janelaDeEscolha.setMessage("O que deseja fazer com a ligação selecionada?");

                janelaDeEscolha.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                janelaDeEscolha.setNegativeButton("Apagar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        boolean excluiu = ligacaoController.excluirLigacaoController(ligacaoSelecionada.getId());

                        dialog.cancel();

                        if (excluiu){

                            adapterLigacao.removerLigacao(position);

                            Toast.makeText(UltimasLigacoesActivity.this, "Ligação excluída", Toast.LENGTH_SHORT).show();

                        } else{

                            Toast.makeText(UltimasLigacoesActivity.this, "Erro ao excluir ligação", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

                janelaDeEscolha.setPositiveButton("Ligar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        Bundle bundleDadosContato = new Bundle();

                        bundleDadosContato.putString("NUMERO", ligacaoSelecionada.getNumero());


                        Intent intentContatoEdit = new Intent(UltimasLigacoesActivity.this, LigacaoActivity.class);
                        intentContatoEdit.putExtras(bundleDadosContato);
                        startActivity(intentContatoEdit);
                    }
                });


                janelaDeEscolha.create().show();

            }
        });

    }

    public void clickBotaoAtualizarLista(View view){
        this.adapterLigacao.atualizar(this.ligacaoController.getListaLigacaoController());
    }

}

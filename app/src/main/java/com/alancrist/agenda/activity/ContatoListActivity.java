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
import com.alancrist.agenda.adapter.contatoAdapter;
import com.alancrist.agenda.controller.ContatoController;
import com.alancrist.agenda.dbHelper.ConexaoSQLite;
import com.alancrist.agenda.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoListActivity extends AppCompatActivity {

    private ListView listContatos;
    private List<Contato> contatos;
    private contatoAdapter adapterContato;
    private ContatoController contatoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato_list);

        contatoController = new ContatoController(ConexaoSQLite.getInstance(ContatoListActivity.this));
        contatos = contatoController.getListaContatosController();

        this.listContatos = findViewById(R.id.listContatos);

        this.adapterContato = new contatoAdapter(ContatoListActivity.this, this.contatos);

        this.listContatos.setAdapter(this.adapterContato);

        this.listContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final Contato contatoSelecionado = (Contato) adapterContato.getItem(position);

                AlertDialog.Builder janelaDeEscolha = new AlertDialog.Builder(ContatoListActivity.this);

                janelaDeEscolha.setTitle("Escolha:");
                janelaDeEscolha.setMessage("O que deseja fazer com o contato selecionado?");

                janelaDeEscolha.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                janelaDeEscolha.setNegativeButton("Apagar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        boolean excluiu = contatoController.excluirContatoController(contatoSelecionado.getId());

                        dialog.cancel();

                        if (excluiu == true){

                            adapterContato.removerContato(position);

                            Toast.makeText(ContatoListActivity.this, "Contato excluído", Toast.LENGTH_SHORT).show();

                        } else{

                            Toast.makeText(ContatoListActivity.this, "Erro ao excluir contato", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

                janelaDeEscolha.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        Bundle bundleDadosContato = new Bundle();

                        bundleDadosContato.putString("nome_contato", contatoSelecionado.getNome());
                        bundleDadosContato.putString("sobrenome_contato", contatoSelecionado.getSobrenome());
                        bundleDadosContato.putString("numero_contato", contatoSelecionado.getNumero());


                        Intent intentContatoEdit = new Intent(ContatoListActivity.this, ContatoEditActivity.class);
                        intentContatoEdit.putExtras(bundleDadosContato);
                        startActivity(intentContatoEdit);
                    }
                });


                janelaDeEscolha.create().show();

            }
        });

    }

    public void clickBotaoAtualizarLista(View view){
        this.adapterContato.atualizar(this.contatoController.getListaContatosController());
    }

}

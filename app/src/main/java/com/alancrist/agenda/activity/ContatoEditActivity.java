package com.alancrist.agenda.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alancrist.agenda.R;
import com.alancrist.agenda.controller.ContatoController;
import com.alancrist.agenda.dbHelper.ConexaoSQLite;
import com.alancrist.agenda.model.Contato;

public class ContatoEditActivity extends AppCompatActivity {

    private EditText edtNome, edtSobrenome, edtNumero;
    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato_edit);

        edtNome = findViewById(R.id.edtNome);
        edtSobrenome = findViewById(R.id.edtSobrenome);
        edtNumero = findViewById(R.id.edtNumero);

        Bundle bundleDadosContato = getIntent().getExtras();

        Contato contato = new Contato();

        contato.setNome(bundleDadosContato.getString("nome_contato"));
        contato.setSobrenome(bundleDadosContato.getString("sobrenome_contato"));
        contato.setNumero(bundleDadosContato.getString("numero_contato"));

        this.setDadosContato(contato);
    }

    private void setDadosContato(Contato contato){

        this.edtNome.setText(contato.getNome());
        this.edtSobrenome.setText(contato.getSobrenome());
        this.edtNumero.setText(contato.getNumero());

    }

    public void cancelarAlteracao(View view){
        Intent intentContatoList = new Intent(ContatoEditActivity.this, ContatoListActivity.class);
        startActivity(intentContatoList);
    }

    public void salvarAlteracao(View view){

        Contato contatoAtualizar = getDadosContatoForm();

        if (contatoAtualizar != null){

            ContatoController contatoController = new ContatoController(ConexaoSQLite.getInstance(ContatoEditActivity.this));
            boolean atualizarContato = contatoController.atualizarContatoController(contatoAtualizar);

            if (atualizarContato){

                Toast.makeText(this, "Contato salvo!", Toast.LENGTH_LONG).show();
                Intent intentContatoList = new Intent(ContatoEditActivity.this, ContatoListActivity.class);
                startActivity(intentContatoList);

            } else{

                Toast.makeText(this, "Contato não foi salvo", Toast.LENGTH_LONG).show();

            }
        } else{
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
        }


    }

    private Contato getDadosContatoForm(){

        this.contato = new Contato();

        if(!this.edtNome.getText().toString().isEmpty() || !this.edtNome.getText().toString().equals(" ")){
            this.contato.setNome(edtNome.getText().toString());
        } else{
            return null;
        }
        if(!this.edtSobrenome.getText().toString().isEmpty() || !this.edtSobrenome.getText().toString().equals(" ")){
            this.contato.setSobrenome(edtSobrenome.getText().toString());
        } else{
            return null;
        }
        if(!this.edtNumero.getText().toString().isEmpty() || !this.edtNumero.getText().toString().equals(" ")){
            this.contato.setNumero(edtNumero.getText().toString());
        } else{
            return null;
        }

        return this.contato;

    }

}

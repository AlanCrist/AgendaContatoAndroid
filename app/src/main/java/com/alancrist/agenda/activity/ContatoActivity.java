package com.alancrist.agenda.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alancrist.agenda.R;
import com.alancrist.agenda.controller.ContatoController;
import com.alancrist.agenda.dbHelper.ConexaoSQLite;
import com.alancrist.agenda.model.Contato;

public class ContatoActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etSobrenome;
    private EditText etNumero;
    private Contato contato;

    Bundle dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        etNome = findViewById(R.id.edtNome);
        etSobrenome = findViewById(R.id.edtSobrenome);
        etNumero = findViewById(R.id.edtNumero);
        dados = getIntent().getExtras();
        etNumero.setText(dados.getString("NUMERO"));


    }

    public void clickBotaoSalvarListener(View view){

        Contato contatoCadastrar = getDadosContatoForm();

        if (contatoCadastrar != null){

            ContatoController contatoController = new ContatoController(ConexaoSQLite.getInstance(ContatoActivity.this));
            long idContato = contatoController.salvarContatoController(contatoCadastrar);

            if (idContato > 0){

                Toast.makeText(this, "Contato salvo!", Toast.LENGTH_LONG).show();

            } else{

                Toast.makeText(this, "Contato não foi salvo", Toast.LENGTH_LONG).show();

            }
        } else{
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
        }

    }

    public void clickBotaoCancelar(View view){

        etNome.setText("");
        etSobrenome.setText("");
        etNumero.setText("");

    }

    private Contato getDadosContatoForm(){

        this.contato = new Contato();

        if(!this.etNome.getText().toString().isEmpty() || !this.etNome.getText().toString().equals(" ")){
            this.contato.setNome(etNome.getText().toString());
        } else{
            return null;
        }
        if(!this.etSobrenome.getText().toString().isEmpty() || !this.etSobrenome.getText().toString().equals(" ")){
            this.contato.setSobrenome(etSobrenome.getText().toString());
        } else{
            return null;
        }
        if(!this.etNumero.getText().toString().isEmpty() || !this.etNumero.getText().toString().equals(" ")){
            this.contato.setNumero(etNumero.getText().toString());
        } else{
            return null;
        }

        return this.contato;

    }

}

package com.alancrist.agenda.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alancrist.agenda.R;
import com.alancrist.agenda.model.Contato;
import java.util.List;

public class contatoAdapter extends BaseAdapter {

    private Context context;
    private List<Contato> contatos;

    public contatoAdapter(Context context, List<Contato> contatos) {
        this.context = context;
        this.contatos = contatos;
    }

    @Override
    public int getCount() {
        return this.contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removerContato(int position){
        this.contatos.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(this.context, R.layout.layout_contato,null);

        TextView txtNome = view.findViewById(R.id.txtNome);
        TextView txtSobrenome = view.findViewById(R.id.txtSobrenome);
        TextView txtTelefone = view.findViewById(R.id.txtTelefone);

        txtNome.setText(this.contatos.get(position).getNome());
        txtSobrenome.setText(this.contatos.get(position).getSobrenome());
        txtTelefone.setText(this.contatos.get(position).getNumero());

        return view;
    }

    public void atualizar(List<Contato> pContatos){
        this.contatos.clear();
        this.contatos = pContatos;
        this.notifyDataSetChanged();
    }
}

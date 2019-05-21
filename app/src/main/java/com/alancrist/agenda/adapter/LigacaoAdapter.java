package com.alancrist.agenda.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.alancrist.agenda.R;
import com.alancrist.agenda.model.Ligacao;

import java.util.List;

public class LigacaoAdapter extends BaseAdapter {

    private Context context;
    private List<Ligacao> ligacoes;

    public LigacaoAdapter(Context context, List<Ligacao> ligacoes) {
        this.context = context;
        this.ligacoes = ligacoes;
    }

    @Override
    public int getCount() {
        return this.ligacoes.size();
    }

    @Override
    public Object getItem(int position) {
        return this.ligacoes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removerLigacao(int position){
        this.ligacoes.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(this.context, R.layout.layout_ligacoes,null);

        TextView txtNomeLigacao = view.findViewById(R.id.txtNomeLigacao);
        TextView txtNumero = view.findViewById(R.id.txtNumero);
        TextView txtQuantidade = view.findViewById(R.id.txtQuantidade);
        TextView txtHora = view.findViewById(R.id.txtHora);

        txtNomeLigacao.setText(this.ligacoes.get(position).getContato().getNome() + " " +
                this.ligacoes.get(position).getContato().getSobrenome());
        txtNumero.setText(this.ligacoes.get(position).getNumero());
        txtQuantidade.setText("(" + this.ligacoes.get(position).getQntLigacao() + ")");
        txtHora.setText(this.ligacoes.get(position).getHora());

        return view;
    }


    public void atualizar(List<Ligacao> pLigacoes){
        this.ligacoes.clear();
        this.ligacoes = pLigacoes;
        this.notifyDataSetChanged();
    }
}

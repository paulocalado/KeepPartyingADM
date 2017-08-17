package com.codgin.paulo.keeppartyingadm;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by paulocalado on 17/08/17.
 */

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {
    List<Produto> listaProduto;
    Context context;

    public ProdutoAdapter(List<Produto> listaProduto, Context context){
        this.listaProduto = listaProduto;
        this.context = context;
    }

    @Override
    public ProdutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_detalhe_pedido, parent, false);
        ProdutoAdapter.ProdutoViewHolder pvh = new ProdutoAdapter.ProdutoViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ProdutoViewHolder holder, int position) {
        holder.txtTotalProduto.setText(String.valueOf(listaProduto.get(position).getTotalPorProduto()));
        holder.txtPrecoProduto.setText("Quantidade: "+String.valueOf(listaProduto.get(position).getQuantidade()));
        holder.txtNomeProduto.setText(String.valueOf(listaProduto.get(position).getNome()));
    }

    @Override
    public int getItemCount() {
        return listaProduto.size();
    }

    public static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView txtNomeProduto;
        TextView txtPrecoProduto;
        TextView txtTotalProduto;


        ProdutoViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardDetalhePedido);
            txtNomeProduto = (TextView) itemView.findViewById(R.id.txtNomeProdutoDetalhe);
            txtPrecoProduto = (TextView) itemView.findViewById(R.id.txtValorProdutoPedido);
            txtTotalProduto = (TextView) itemView.findViewById(R.id.txtTotalProdutoPedido);

            //qual itemView.setOnClickListener( itemView);
        }
    }
}

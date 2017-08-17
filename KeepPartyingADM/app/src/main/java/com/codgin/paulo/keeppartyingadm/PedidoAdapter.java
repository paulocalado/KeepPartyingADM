package com.codgin.paulo.keeppartyingadm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Paulo on 08/08/2017.
 */

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {
    List<String> listaProduto;
    Context context;

    public PedidoAdapter(List<String> listaProduto, Context context){
        this.listaProduto = listaProduto;
        this.context = context;
    }
    @Override
    public PedidoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_produtos_pedido, parent, false);
        PedidoAdapter.PedidoViewHolder pvh = new PedidoAdapter.PedidoViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PedidoViewHolder holder, final int position) {
        holder.txtNomeProduto.setText(String.valueOf(listaProduto.get(position)));
      //  holder.txtValorProduto.setText("R$ "+String.valueOf(listaProduto.get(position).getPreco()));
        //holder.txtTotalProduto.setText("R$ total neste produto: "+String.valueOf(listaProduto.get(position).getTotalPorProduto()));
        holder.btnDetalhePedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDetalhe = new Intent(context, DetalhePedidoActivity.class);
                intentDetalhe.putExtra("idPedido", String.valueOf(listaProduto.get(position)));
                context.startActivity(intentDetalhe);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProduto.size();
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView txtNomeProduto;
        Button btnDetalhePedido;

        PedidoViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardProdutosPedido);
            txtNomeProduto = (TextView) itemView.findViewById(R.id.txtNomeProdutoPedido);
            btnDetalhePedido = (Button)itemView.findViewById(R.id.btnDetalhePedido);

            //qual itemView.setOnClickListener( itemView);
        }
    }
}

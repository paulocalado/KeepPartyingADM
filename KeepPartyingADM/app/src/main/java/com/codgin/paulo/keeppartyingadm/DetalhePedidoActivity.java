package com.codgin.paulo.keeppartyingadm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetalhePedidoActivity extends AppCompatActivity {
    List<Produto> listaProduto = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_pedido);
        Intent intentId = getIntent();
        String idPedido = String.valueOf(intentId.getExtras().getString("idPedido"));
        Toast.makeText(this, idPedido, Toast.LENGTH_SHORT).show();

      final  RecyclerView rvDetalhe = (RecyclerView)findViewById(R.id.rvProdutosPedido);

        final DatabaseReference firebaseReferencia = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference produtoReferencia = firebaseReferencia.child("pedidos").child(idPedido);

        produtoReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (listaProduto.size() != 0) {
                    listaProduto.clear();
                   // totalPedido = 0;
                }
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Produto produto = new Produto();
                    produto = postSnapshot.getValue(Produto.class);
                     double totalProduto = produto.getPreco() * produto.getQuantidade();
                    //  totalPedido += totalProduto;
                    produto.setTotalPorProduto(totalProduto);
                    listaProduto.add(produto);
                }

                //txtTotalPedido.setText("Seu total é de: R$"+totalPedido);
                final ProdutoAdapter adapter = new ProdutoAdapter(listaProduto, DetalhePedidoActivity.this);
                LinearLayoutManager llm = new LinearLayoutManager(DetalhePedidoActivity.this);
                rvDetalhe.setLayoutManager(llm);
                rvDetalhe.setAdapter(adapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}

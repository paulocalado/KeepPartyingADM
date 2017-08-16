package com.codgin.paulo.keeppartyingadm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> listaProduto = new ArrayList<>();
    public double totalPedido = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView rvPedido = (RecyclerView) findViewById(R.id.rvPedidos);
        final DatabaseReference firebaseReferencia = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference produtoReferencia = firebaseReferencia.child("pedidos");

        produtoReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (listaProduto.size() != 0) {
                    listaProduto.clear();
                    totalPedido = 0;
                }
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String idPedido = new String();
                    idPedido = (String) postSnapshot.getKey();
                   // double totalProduto = produto.getPreco() * produto.getQuantidade();
                  //  totalPedido += totalProduto;
                  //  produto.setTotalPorProduto(totalProduto);
                    listaProduto.add(idPedido);
                }

                //txtTotalPedido.setText("Seu total é de: R$"+totalPedido);
                final PedidoAdapter adapter = new PedidoAdapter(listaProduto, MainActivity.this);
                LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
                rvPedido.setLayoutManager(llm);
                rvPedido.setAdapter(adapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

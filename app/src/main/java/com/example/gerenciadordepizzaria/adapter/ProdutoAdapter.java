package com.example.gerenciadordepizzaria.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.hardware.camera2.CaptureRequest;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gerenciadordepizzaria.R;
import com.example.gerenciadordepizzaria.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder> {

    private List<Produto> produtos;
    public ProdutoAdapter(List<Produto> lista){
        this.produtos = lista;
    }

    @NonNull
    @Override
    public ProdutoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_produto_adapter, parent, false);
        return new MyViewHolder(itemLista);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull ProdutoAdapter.MyViewHolder holder, int position) {
        Produto produto = produtos.get(position);
        holder.produto.setText(produto.getProduto());
        holder.quantidade.setText(produto.getQuantidade().toString());
        holder.botao_deletar = holder.itemView.findViewById(R.id.botao_deletar);
        holder.botao_deletar.setTag(produto.getId());
        holder.botao_editar = holder.itemView.findViewById(R.id.botao_editar);
        holder.botao_editar.setTag(produto.getId());
    }
    @Override
    public int getItemCount() {return this.produtos.size();}

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView botao_editar;
        ImageView botao_deletar;
        TextView produto;
        TextView quantidade;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            produto = itemView.findViewById(R.id.texto_produto);
            quantidade = itemView.findViewById(R.id.texto_quantidade);
        }
    }
    public void updateList(List<Produto> newList){
        produtos = new ArrayList<>();
        produtos.addAll(newList);
        notifyDataSetChanged();
    }
}

package com.example.gerenciadordepizzaria.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.gerenciadordepizzaria.adapter.ProdutoAdapter;
import com.example.gerenciadordepizzaria.model.Produto;
import com.example.gerenciadordepizzaria.model.ProdutoDAO;
import com.example.gerenciadordepizzaria.R;

import java.util.ArrayList;
import java.util.List;

public class Lista_produtos extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProdutoDAO dao;
    private ProdutoAdapter adaptador;
    private List<Produto> produtos;
    private List<Produto> produtosFiltrados = new ArrayList<>();

    private RecyclerView.LayoutManager layoutManager;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        recyclerView = findViewById(R.id.lista_produtos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dao = new ProdutoDAO(this);
        produtos = dao.obterProdutos();
        produtosFiltrados.addAll(produtos);
        adaptador = new ProdutoAdapter(produtos);

        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adaptador);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        View voltar = findViewById(R.id.voltar_produtos);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lista_produtos.this, Menu_principal.class);
                startActivity(intent);
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        SearchView search = (SearchView) findViewById(R.id.search_produtos);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                procurarProduto(newText);
                return false;
            }
        });
    }

    public void procurarProduto(String produto){
        produtosFiltrados.clear();
        for(Produto produto1 : produtos){
            if(produto1.getProduto().toLowerCase().contains(produto.toLowerCase())){
                produtosFiltrados.add(produto1);
            }
        }
        adaptador.updateList(produtosFiltrados);
    }

    public void onResume(){
        super.onResume();
        produtos = dao.obterProdutos();
        produtosFiltrados.clear();
        produtosFiltrados.addAll(produtos);
    }

}
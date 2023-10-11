package com.example.gerenciadordepizzaria.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
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

public class ListaIngredientesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProdutoDAO dao;
    private ProdutoAdapter adaptador;
    private List<Produto> ingredientes;
    private List<Produto> ingredientesFiltrados = new ArrayList<>();

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ingredientes);

        recyclerView = findViewById(R.id.lista_ingredientes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dao = new ProdutoDAO(this);
        ingredientes = dao.obterIngredientes();
        ingredientesFiltrados.addAll(ingredientes);

        adaptador = new ProdutoAdapter(ingredientes);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adaptador);

        View voltar = findViewById(R.id.voltar_ingredientes);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SearchView search = (SearchView) findViewById(R.id.search_ingredientes);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                procurarIngrediente(newText);
                return false;
            }
        });
    }
    public void procurarIngrediente(String ingrediente){
        ingredientesFiltrados.clear();
        for(Produto ingrediente1 : ingredientes){
            if(ingrediente1.getProduto().toLowerCase().contains(ingrediente.toLowerCase())){
                ingredientesFiltrados.add(ingrediente1);
            }
        }
        adaptador.updateList(ingredientesFiltrados);
    }
    public void onResume(){
        super.onResume();
        ingredientes = dao.obterIngredientes();
        ingredientesFiltrados.clear();
        ingredientesFiltrados.addAll(ingredientes);
    }
    public void deletarClick(View view) {
        int produtoId = (int) view.getTag();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Tem certeza de que deseja excluir este item?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dao.excluirProduto(produtoId);

                ingredientes = dao.obterIngredientes();
                ingredientesFiltrados.clear();
                ingredientesFiltrados.addAll(ingredientes);
                adaptador.updateList(ingredientesFiltrados);

                dialog.dismiss();
            }
        }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
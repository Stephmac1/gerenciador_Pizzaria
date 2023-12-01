package com.example.gerenciadordepizzaria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gerenciadordepizzaria.R;
import com.example.gerenciadordepizzaria.adapter.ProdutoAdapter;
import com.example.gerenciadordepizzaria.model.Produto;
import com.example.gerenciadordepizzaria.model.ProdutoDAO;

import java.util.ArrayList;
import java.util.List;

public class AlterarActivity extends AppCompatActivity {

    private EditText editarProduto;
    private EditText editarQuantidade;
    private EditText editarTipo;
    private ProdutoAdapter adaptador;
    private List<Produto> produtos;
    private List<Produto> produtosFiltrados = new ArrayList<>();
    private List<Produto> ingredientes;
    private List<Produto> ingredientesFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        adaptador = new ProdutoAdapter(new ArrayList<>());

        View voltar = findViewById(R.id.voltar_alterar);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        editarProduto = findViewById(R.id.alterar_produto);
        editarQuantidade = findViewById(R.id.alterar_quantidade);
        editarTipo = findViewById(R.id.alterar_tipo);

        Produto produtoParaEditar = (Produto) getIntent().getSerializableExtra("produtoParaEditar");

        if(produtoParaEditar != null) {
            editarProduto.setText(produtoParaEditar.getProduto());
            editarQuantidade.setText(String.valueOf(produtoParaEditar.getQuantidade()));
            editarTipo.setText(produtoParaEditar.getTipo());
        }
        Button botaoAlterar = findViewById(R.id.botao_alterar);
        botaoAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarProduto();
                alterarIngrediente();
            }
        });
    }
    private void alterarProduto(){
        String novoProduto = editarProduto.getText().toString();
        double novaQuantidade = Double.parseDouble(editarQuantidade.getText().toString());
        String novoTipo = editarTipo.getText().toString();

        Produto produtoParaEditar = (Produto) getIntent().getSerializableExtra("produtoParaEditar");

        produtoParaEditar.setProduto(novoProduto);
        produtoParaEditar.setQuantidade(novaQuantidade);
        produtoParaEditar.setTipo(novoTipo);

        ProdutoDAO dao = new ProdutoDAO(getApplicationContext());
        dao.atualizarProduto(produtoParaEditar);

        produtos = dao.obterProdutos();
        produtosFiltrados.clear();
        produtosFiltrados.addAll(produtos);

        if (adaptador != null) {
            adaptador.updateList(produtosFiltrados);
            adaptador.notifyDataSetChanged();
        }

        finish();
    }
    private void alterarIngrediente(){
        String novoProduto = editarProduto.getText().toString();
        double novaQuantidade = Double.parseDouble(editarQuantidade.getText().toString());
        String novoTipo = editarTipo.getText().toString();

        Produto produtoParaEditar = (Produto) getIntent().getSerializableExtra("produtoParaEditar");

        produtoParaEditar.setProduto(novoProduto);
        produtoParaEditar.setQuantidade(novaQuantidade);
        produtoParaEditar.setTipo(novoTipo);

        ProdutoDAO dao = new ProdutoDAO(getApplicationContext());
        dao.atualizarProduto(produtoParaEditar);

        ingredientes = dao.obterIngredientes();
        ingredientesFiltrados.clear();
        ingredientesFiltrados.addAll(ingredientes);

        if (adaptador != null) {
            adaptador.updateList(ingredientesFiltrados);
            adaptador.notifyDataSetChanged();
        }

        finish();
    }
}
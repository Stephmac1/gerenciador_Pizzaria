package com.example.gerenciadordepizzaria.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public ProdutoDAO(Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Produto produto) {
        ContentValues values = new ContentValues();
        values.put("produto", produto.getProduto());
        values.put("quantidade", produto.getQuantidade());
        values.put("tipo", produto.getTipo());
        return banco.insert("produtos", null, values);
    }

    public List<Produto> obterProdutos() {
        List<Produto> produtos = new ArrayList<>();
        Cursor cursor = banco.query("produtos", new String[]{"id", "produto", "quantidade", "tipo"},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            Produto produto = new Produto();
            produto.setId(cursor.getInt(0));
            produto.setProduto(cursor.getString(1));
            produto.setQuantidade(cursor.getDouble(2));
            produto.setTipo(cursor.getString(3));
            if ("Produto".equals(produto.getTipo().toLowerCase())) {
                produtos.add(produto);
            }
        }
        return produtos;
    }
    public List<Produto> obterIngredientes() {
        List<Produto> ingredientes = new ArrayList<>();
        Cursor cursor = banco.query("produtos", new String[]{"id", "produto", "quantidade", "tipo"},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            Produto produto = new Produto();
            produto.setId(cursor.getInt(0));
            produto.setProduto(cursor.getString(1));
            produto.setQuantidade(cursor.getDouble(2));
            produto.setTipo(cursor.getString(3));
            if ("Ingrediente".equals(produto.getTipo().toLowerCase())) {
                ingredientes.add(produto);
            }
        }
        return ingredientes;
    }
}
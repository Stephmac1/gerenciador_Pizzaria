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
            if ("produto".equals(produto.getTipo().toLowerCase())) {
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
            if ("ingrediente".equals(produto.getTipo().toLowerCase())) {
                ingredientes.add(produto);
            }
        }
        return ingredientes;
    }
    public void excluirProduto(int produtoId) {
        banco.delete("produtos", "id = ?", new String[] { String.valueOf(produtoId) });
    }
    public Produto obterProdutoPorId(int produtoId){
        Cursor cursor = banco.query("produtos", new String[]{"id", "produto","quantidade", "tipo"}, "id = ?",
                new String[]{String.valueOf(produtoId)}, null, null, null);
        if (cursor.moveToFirst()){
            Produto produto = new Produto();
            produto.setId(cursor.getInt(0));
            produto.setProduto(cursor.getString(1));
            produto.setQuantidade(cursor.getDouble(2));
            produto.setTipo(cursor.getString(3));
            return produto;
        }
        return null;
    }
    public void atualizarProduto(Produto produto){
        ContentValues values = new ContentValues();
        values.put("produto", produto.getProduto());
        values.put("quantidade", produto.getQuantidade());
        values.put("tipo", produto.getTipo());

        banco.update("produtos", values, "id = ?", new String[]{String.valueOf(produto.getId())});
    }
}
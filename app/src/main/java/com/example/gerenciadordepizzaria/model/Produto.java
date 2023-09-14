package com.example.gerenciadordepizzaria.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Produto implements Serializable {
    private Integer id;
    private String produto;
    private Double quantidade;
    private String tipo;

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getProduto() {return produto;}

    public void setProduto(String produto) {this.produto = produto;}

    public Double getQuantidade() {return quantidade;}

    public void setQuantidade(Double quantidade) {this.quantidade = quantidade;}

    public String getTipo() {return tipo;}

    public void setTipo(String tipo) {this.tipo = tipo;}

    public String getNomeDoProduto() {
        return produto;
    }
    public String getTipoDoProduto() {
        return tipo;
    }
}

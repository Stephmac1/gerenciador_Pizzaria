package com.example.gerenciadordepizzaria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gerenciadordepizzaria.model.Produto;
import com.example.gerenciadordepizzaria.model.ProdutoDAO;
import com.example.gerenciadordepizzaria.R;

public class MainActivity extends AppCompatActivity {
    private ProdutoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new ProdutoDAO(this);

        View voltar = findViewById(R.id.botao_voltar);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
            }
        });
    }
    public void adicionar(View view){

        EditText campoProduto = findViewById(R.id.produto);
        EditText campoQuantidade = findViewById(R.id.quantidade);
        EditText campoTipo = findViewById(R.id.tipo);

        String produtoText = campoProduto.getText().toString();
        String quantidadeText = campoQuantidade.getText().toString();
        String tipoText = campoTipo.getText().toString();

        if(TextUtils.isEmpty(produtoText) || TextUtils.isEmpty(quantidadeText) || TextUtils.isEmpty(tipoText)){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            double quantidade = Double.parseDouble(quantidadeText);

            if(quantidade <= 0){
                Toast.makeText(this, "Quantidade deve ser maior que zero", Toast.LENGTH_SHORT).show();
                return;
            }
            Produto produto1 = new Produto();
            produto1.setProduto(produtoText);
            produto1.setQuantidade(quantidade);
            produto1.setTipo(tipoText);
            long id = dao.inserir(produto1);
            Toast.makeText(this, "Produto inserido com id: " + id, Toast.LENGTH_SHORT).show();

            campoProduto.setText("");
            campoQuantidade.setText("");
            campoTipo.setText("");
        }
        catch (NumberFormatException e){
            Toast.makeText(this, "Quantidade inválida. Insira um número válido", Toast.LENGTH_SHORT).show();
        }

    }
}
package com.example.gerenciadordepizzaria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gerenciadordepizzaria.R;

public class Menu_principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        View adicionarProdutos = findViewById(R.id.button);
        View visualizarProdutos = findViewById(R.id.button2);
        View visualizarIngredientes = findViewById(R.id.button3);

        adicionarProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_principal.this, MainActivity.class);
                startActivity(intent);
            }
        });
        visualizarProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_principal.this, Lista_produtos.class);
                startActivity(intent);
            }
        });
        visualizarIngredientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_principal.this, Lista_ingredientes.class);
                startActivity(intent);
            }
        });
    }
}
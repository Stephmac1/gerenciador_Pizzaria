package com.example.gerenciadordepizzaria.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "banco.db";
    private static final int version = 1;
    public Conexao(Context context){super(context, name, null, version);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE produtos(id INTEGER PRIMARY KEY AUTOINCREMENT, Produto VARCHAR(50), Quantidade DOUBLE, Tipo VARCHAR(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

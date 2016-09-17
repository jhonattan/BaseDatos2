package com.example.android.basedatos2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ANDROID on 17/09/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "parainfo.sqlite";
    public static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // crear tabla
        db.execSQL(DataBaseManager.CREATE_AUTORES);
        db.execSQL(DataBaseManager.CREATE_FRASES);

        dataInicial(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS frases");
        db.execSQL("DROP TABLE IF EXISTS autores");

        onCreate(db);
    }

    public void dataInicial(SQLiteDatabase db) {
        List<Autores> l_autores = new LinkedList<>();
        l_autores.add(new Autores(1, "Luis Abanto"));
        l_autores.add(new Autores(2, "Ana Torres"));
        l_autores.add(new Autores(3, "Jorge Risco"));

        List<Frases> l_frase = new LinkedList<>();
        l_frase.add(new Frases(1, 1,"No todo es blanco y negro"));
        l_frase.add(new Frases(2, 1,"El camino recto no siempre es el más corto"));
        l_frase.add(new Frases(3, 3,"Todos los caminos llevan a Roma"));

        // INS autores
        for (Autores a: l_autores) {
            ContentValues values = new ContentValues();
            //values.put("idautor", a.getIdautor());
            values.put("autor", a.getAutor());

            db.insert("autores", null, values);
        }

        // INS frases
        for (Frases f: l_frase) {
            ContentValues values = new ContentValues();
            //values.put("idfrase", f.getIdfrase());
            values.put("id_autor", f.getIdautor());
            values.put("frase", f.getFrase());

            db.insert("frases", null, values);
        }
    }

}

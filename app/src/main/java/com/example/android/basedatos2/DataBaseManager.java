package com.example.android.basedatos2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ANDROID on 17/09/2016.
 */
public class DataBaseManager {

    public static final String CREATE_AUTORES =
            "CREATE TABLE autores("
                    + "id_autor integer primary key autoincrement,"
                    + "autor text not null);";

    public static final String CREATE_FRASES =
            "CREATE TABLE frases("
                    + "id_frase integer primary key autoincrement,"
                    + "id_autor integer not null,"
                    + "frase text not null," +
                    "  FOREIGN KEY(id_autor) REFERENCES autores(id_autor));";

    // operaciones CRUD
    SQLiteDatabase database;

    public DataBaseManager(Context context) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
    }
    //

    public String frasesQry() {
        StringBuilder result = new StringBuilder();

        //String[] cols = {"autor", "frase"};
        Cursor cursor = database.rawQuery(
                "select frases.id_frase, autor, frase " +
                        "from autores inner join frases " +
                        "on autores.id_autor = frases.id_autor", null);

        while(cursor.moveToNext()) {
            Integer id_frase = cursor.getInt(0);
            String autor = cursor.getString(1);
            String frase = cursor.getString(2);

            String fil = String.format("%03d %-30s\r\n%-40s", id_frase, autor, frase);
            result.append(fil).append("\r\n\r\n");
        }

        return result.toString();
    }

    public void frasesIns(Integer id_autor, String frase) {
        ContentValues values = new ContentValues();

        values.put("id_autor", id_autor);
        values.put("frase", frase);

        long ok = database.insert("frases", null, values);
        // en el segundo parámetro va el nombre del campo que tiene valor null
        // si se pone null significa ningún campo
        // si ok == -1 no pudo hacer la inserción

        /*
        String sql = "INSERT INTO frases VALUES(" +
                "null," +
                "" + id_autor + "," +
                "'" + frase + "');";
        database.execSQL(sql);
        */
    }

    public void frasesDel(Integer id) {
        String[] param = {id.toString()};
        database.delete("frases", "id_frase = ?", param);

        /*
        String sql = "DELETE FROM frases WHERE _id=" + id;
        database.execSQL(sql);
        */
    }

    public void frasesUpd(Integer id_autor, String frase, Integer id) {
        ContentValues values = new ContentValues();
        //values.put("id_autor", id_autor);
        values.put("frase", frase);

        String[] param = {id.toString()};

        database.update("frases", values, "id_frase = ?", param);

        /*
        String sql = "UPDATE frases SET frase='" + frase + "' WHERE _id=" + id;
        database.execSQL(sql);
        */
    }


}

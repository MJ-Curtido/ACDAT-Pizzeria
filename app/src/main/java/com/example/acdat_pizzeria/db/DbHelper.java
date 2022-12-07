package com.example.acdat_pizzeria.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.acdat_pizzeria.clases.Pizza;
import com.example.acdat_pizzeria.enums.TipoIngrediente;

public class DbHelper extends SQLiteOpenHelper {
    private static final int VERSIONBBDD = 1;
    private static final String NOMBREBBDD = "pizzeria.db";


    public DbHelper(@Nullable Context context) {
        super(context, NOMBREBBDD, null, VERSIONBBDD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE \"usuario\" (\n" +
                "\t\"nomUsuario\"\tTEXT NOT NULL UNIQUE,\n" +
                "\t\"contrasenya\"\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(\"nomUsuario\")\n" +
                ")");

        sqLiteDatabase.execSQL("CREATE TABLE \"pizza\" (\n" +
                "\t\"id\"\tINTEGER UNIQUE,\n" +
                "\t\"tamanyo\"\tINTEGER NOT NULL,\n" +
                "\t\"tipoSalsa\"\tINTEGER NOT NULL,\n" +
                "\t\"favorita\"\tINTEGER NOT NULL,\n" +
                "\t\"nombre\"\tINTEGER NOT NULL,\n" +
                "\t\"precio\"\tINTEGER,\n" +
                "\t\"nomUsuario\"\tTEXT NOT NULL,\n" +
                "\tFOREIGN KEY(\"nomUsuario\") REFERENCES \"usuario\"(\"nomUsuario\"),\n" +
                "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ")");

        sqLiteDatabase.execSQL("CREATE TABLE \"ingrediente\" (\n" +
                "\t\"id\"\tINTEGER UNIQUE,\n" +
                "\t\"tipoIngrediente\"\tINTEGER NOT NULL,\n" +
                "\t\"idPizza\"\tINTEGER NOT NULL,\n" +
                "\tFOREIGN KEY(\"idPizza\") REFERENCES \"pizza\"(\"id\"),\n" +
                "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ")");

        ContentValues values = new ContentValues();
        values.put("nomUsuario", "manu");
        values.put("contrasenya", "1234");

        sqLiteDatabase.insert("usuario", null, values);

        values = new ContentValues();

        values.put("nomUsuario", "pablo");
        values.put("contrasenya", "1234");

        sqLiteDatabase.insert("usuario", null, values);

        values.put("nomUsuario", "marta");
        values.put("contrasenya", "1234");

        sqLiteDatabase.insert("usuario", null, values);

        System.out.println("Base de datos creada correctamente.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertarUsuario(String nomUsuario, String contrasenya){
        long id = 0;

        try {
            SQLiteDatabase bbdd = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nomUsuario", nomUsuario);
            values.put("contrasenya", contrasenya);

            id = bbdd.insert("usuario", null, values);

            System.out.println("Se ha introducido correctamente.");
        } catch (Exception ex){
            System.err.println("Algo falló al insertar.");
        }

        return id;
    }

    public long insertarPizza(Pizza pizza){
        long id = 0;

        try {
            SQLiteDatabase bbdd = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("tamanyo", pizza.getTamanyo().ordinal());
            values.put("tipoSalsa", pizza.getSalsa().ordinal());
            values.put("favorita", pizza.isFavorita());
            values.put("nombre", pizza.getNombre().ordinal());
            values.put("precio", pizza.getPrecio());
            values.put("nomUsuario", pizza.getUsuario().getNomUsuario());

            id = bbdd.insert("pizza", null, values);

            System.out.println("Se ha introducido correctamente.");
        } catch (Exception ex){
            System.err.println("Algo falló al insertar.");
        }

        return id;
    }

    public long insertarIngrediente(Pizza pizza){
        long id = 0;

        try {
            SQLiteDatabase bbdd = this.getWritableDatabase();

            for (int i = 0; i < pizza.getIngredientes().size(); i++) {
                ContentValues values = new ContentValues();
                values.put("tipoIngrediente", pizza.getIngredientes().get(i).ordinal());
                values.put("idPizza", pizza.getId());

                id = bbdd.insert("ingrediente", null, values);
            }

            System.out.println("Se ha introducido correctamente.");
        } catch (Exception ex){
            System.err.println("Algo falló al insertar.");
        }

        return id;
    }
}
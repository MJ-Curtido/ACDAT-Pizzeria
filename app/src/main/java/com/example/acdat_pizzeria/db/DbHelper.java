package com.example.acdat_pizzeria.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.acdat_pizzeria.clases.Pizza;
import com.example.acdat_pizzeria.clases.Usuario;
import com.example.acdat_pizzeria.enums.TipoIngrediente;
import com.example.acdat_pizzeria.enums.TipoNombre;
import com.example.acdat_pizzeria.enums.TipoSalsa;
import com.example.acdat_pizzeria.enums.TipoTamanyo;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
                "\t\"tamanyo\"\tINTEGER,\n" +
                "\t\"tipoSalsa\"\tINTEGER NOT NULL,\n" +
                "\t\"favorita\"\tINTEGER,\n" +
                "\t\"nombre\"\tINTEGER NOT NULL,\n" +
                "\t\"precio\"\tINTEGER,\n" +
                "\t\"nomUsuario\"\tTEXT,\n" +
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

        insertInicioUsuario(sqLiteDatabase);

        insertInicioPizza(sqLiteDatabase);

        insertInicioIngredientes(sqLiteDatabase);

        System.out.println("Base de datos creada correctamente.");
    }

    private void insertInicioIngredientes(SQLiteDatabase sqLiteDatabase) {
        ContentValues values = new ContentValues();
        values.put("tipoIngrediente", 1);
        values.put("idPizza", 1);
        sqLiteDatabase.insert("ingrediente", null, values);

        values = new ContentValues();
        values.put("tipoIngrediente", 3);
        values.put("idPizza", 1);
        sqLiteDatabase.insert("ingrediente", null, values);

        values = new ContentValues();
        values.put("tipoIngrediente", 8);
        values.put("idPizza", 1);
        sqLiteDatabase.insert("ingrediente", null, values);

        values = new ContentValues();
        values.put("tipoIngrediente", 9);
        values.put("idPizza", 1);
        sqLiteDatabase.insert("ingrediente", null, values);

        values = new ContentValues();
        values.put("tipoIngrediente", 10);
        values.put("idPizza", 2);
        sqLiteDatabase.insert("ingrediente", null, values);

        values = new ContentValues();
        values.put("tipoIngrediente", 11);
        values.put("idPizza", 2);
        sqLiteDatabase.insert("ingrediente", null, values);

        values = new ContentValues();
        values.put("tipoIngrediente", 0);
        values.put("idPizza", 3);
        sqLiteDatabase.insert("ingrediente", null, values);

        values = new ContentValues();
        values.put("tipoIngrediente", 4);
        values.put("idPizza", 3);
        sqLiteDatabase.insert("ingrediente", null, values);

        values = new ContentValues();
        values.put("tipoIngrediente", 6);
        values.put("idPizza", 3);
        sqLiteDatabase.insert("ingrediente", null, values);

        values = new ContentValues();
        values.put("tipoIngrediente", 7);
        values.put("idPizza", 3);
        sqLiteDatabase.insert("ingrediente", null, values);
    }

    private void insertInicioUsuario(SQLiteDatabase sqLiteDatabase) {
        ContentValues values = new ContentValues();
        values.put("nomUsuario", "manu");
        values.put("contrasenya", "1234");

        sqLiteDatabase.insert("usuario", null, values);

        values = new ContentValues();

        values.put("nomUsuario", "pablo");
        values.put("contrasenya", "1234");

        sqLiteDatabase.insert("usuario", null, values);

        values = new ContentValues();

        values.put("nomUsuario", "marta");
        values.put("contrasenya", "1234");

        sqLiteDatabase.insert("usuario", null, values);
    }

    private void insertInicioPizza(SQLiteDatabase sqLiteDatabase) {
        ContentValues values = new ContentValues();

        values.put("tipoSalsa", 0);
        values.put("nombre", 1);

        sqLiteDatabase.insert("pizza", null, values);

        values = new ContentValues();

        values.put("tipoSalsa", 3);
        values.put("nombre", 3);

        sqLiteDatabase.insert("pizza", null, values);

        values = new ContentValues();

        values.put("tipoSalsa", 4);
        values.put("nombre", 2);

        sqLiteDatabase.insert("pizza", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long anyadirUsuarioContra(String nomUsuario, String contrasenya){
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

    public long anyadirPizza(Pizza pizza){
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

    public ArrayList<TipoIngrediente> obtenerIngredientes(Integer idPizza) {
        SQLiteDatabase bbdd = this.getWritableDatabase();

        ArrayList<TipoIngrediente> listaIngredientes = new ArrayList<TipoIngrediente>();
        Cursor cursorIngrediente = null;

        cursorIngrediente = bbdd.rawQuery("SELECT * FROM ingrediente WHERE idPizza = " + idPizza, null);

        if (cursorIngrediente.moveToFirst()) {
            do {
               listaIngredientes.add(TipoIngrediente.values()[cursorIngrediente.getInt(1)]);
            } while(cursorIngrediente.moveToNext());
        }
        cursorIngrediente.close();

        return listaIngredientes;
    }

    public ArrayList<Pizza> obtenerPizzasPred() {
        SQLiteDatabase bbdd = this.getWritableDatabase();

        ArrayList<Pizza> listaPizzas = new ArrayList<Pizza>();
        Pizza pizza = null;
        Cursor cursorPizza = null;

        cursorPizza = bbdd.rawQuery("SELECT * FROM pizza", null);

        if (cursorPizza.moveToFirst()) {
            do {
                if (cursorPizza.getInt(4) != 0) {
                    pizza = new Pizza();

                    pizza.setId(cursorPizza.getInt(0));
                    pizza.setSalsa(TipoSalsa.values()[cursorPizza.getInt(2)]);
                    pizza.setNombre(TipoNombre.values()[cursorPizza.getInt(4)]);
                    pizza.setIngredientes(this.obtenerIngredientes(cursorPizza.getInt(0)));

                    listaPizzas.add(pizza);
                }
            } while(cursorPizza.moveToNext());
        }
        cursorPizza.close();

        return listaPizzas;
    }

    public Usuario obtenerUsuario(String nomUsuario) {
        SQLiteDatabase bbdd = this.getWritableDatabase();

        Usuario usuario = null;
        Cursor cursorUsuario = null;

        cursorUsuario = bbdd.rawQuery("SELECT * FROM usuario WHERE nomUsuario = ''" + nomUsuario + "'", null);

        if (cursorUsuario.moveToFirst()) {
            do {
                usuario = new Usuario();

                usuario.setNomUsuario(cursorUsuario.getString(0));
                usuario.setContrasenya(cursorUsuario.getString(1));
            } while (cursorUsuario.moveToNext());
        }
        cursorUsuario.close();

        return usuario;
    }

    public Pizza obtenerPizzaFavorita(Usuario usuario) {
        SQLiteDatabase bbdd = this.getWritableDatabase();

        Pizza pizza = null;
        Cursor cursorPizza = null;

        cursorPizza = bbdd.rawQuery("SELECT * FROM pizza WHERE nomUsuario = '" + usuario.getNomUsuario() + "'", null);

        if (cursorPizza.moveToFirst()) {
            do {
                if (cursorPizza.getInt(3) != 1) {
                    pizza = new Pizza();

                    pizza.setId(cursorPizza.getInt(0));
                    pizza.setTamanyo(TipoTamanyo.values()[cursorPizza.getInt(1)]);
                    pizza.setSalsa(TipoSalsa.values()[cursorPizza.getInt(2)]);
                    pizza.setFavorita(true);
                    pizza.setNombre(TipoNombre.values()[cursorPizza.getInt(4)]);
                    pizza.setPrecio(cursorPizza.getInt(5));
                    pizza.setUsuario(this.obtenerUsuario(cursorPizza.getString(6)));
                    pizza.setIngredientes(this.obtenerIngredientes(cursorPizza.getInt(0)));
                }
            } while(cursorPizza.moveToNext());
        }
        cursorPizza.close();

        return pizza;
    }

    public void quitarFavorita(Usuario usuario) {
        SQLiteDatabase bbdd = this.getWritableDatabase();

        Pizza pizza = obtenerPizzaFavorita(usuario);

        bbdd.execSQL("UPDATE pizza SET favorita = 0 WHERE id = " + pizza.getId() + " and favorita = 1");

        bbdd.close();
    }

    public Boolean comprobarUsuarioContra(String nomUsuario, String contrasenya){
        SQLiteDatabase bbdd = this.getWritableDatabase();

        Boolean existe = false;
        Cursor cursorPizza = null;

        cursorPizza = bbdd.rawQuery("SELECT * FROM usuario WHERE nomUsuario = '" + nomUsuario + "' and contrasenya = '" + contrasenya + "'", null);

        if (cursorPizza.moveToFirst()) {
            existe = true;
        }
        cursorPizza.close();

        return existe;
    }
}
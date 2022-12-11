package com.example.acdat_pizzeria.servicio;

import android.content.Context;

import com.example.acdat_pizzeria.clases.Pizza;
import com.example.acdat_pizzeria.clases.Usuario;
import com.example.acdat_pizzeria.daos.DAOPizzas;
import com.example.acdat_pizzeria.daos.DAOUsuarios;
import com.example.acdat_pizzeria.db.DbHelper;
import com.example.acdat_pizzeria.enums.TipoNombre;

import java.util.ArrayList;

public class Servicio {
    public static Servicio servicio = null;

    public Servicio() {
    }

    public static Servicio getInstance() {
        if (servicio == null) {
            servicio = new Servicio();
        }

        return servicio;
    }

    public Boolean comprobarUsuarioContra(Context context, String usuario, String contra) {
        DbHelper db = new DbHelper(context);
        return db.comprobarUsuarioContra(usuario, contra);
    }

    public Boolean anyadirUsuarioContra(Context context, String usuario, String contra) {
        DbHelper db = new DbHelper(context);

        if (db.anyadirUsuarioContra(usuario, contra) == 0) {
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean existeFavorita(Context context, Usuario usuario) {
        if (obtenerPizzaFavorita(context, usuario) == null) {
            return false;
        }
        else {
            return true;
        }
    }

    public Pizza obtenerPizzaFavorita(Context context, Usuario usuario) {
        DbHelper db = new DbHelper(context);
        return db.obtenerPizzaFavorita(usuario);
    }

    public ArrayList<Pizza> obtenerPizzasPred(Context context) {
        DbHelper db = new DbHelper(context);
        return db.obtenerPizzasPred();
    }

    public void quitarFavorita(Context context, Usuario usuario) {
        DbHelper db = new DbHelper(context);
        db.quitarFavorita(usuario);
    }

    public void anyadirPizza(Context context, Pizza pizza) {
        DbHelper db = new DbHelper(context);
        db.anyadirPizza(pizza);
    }
}
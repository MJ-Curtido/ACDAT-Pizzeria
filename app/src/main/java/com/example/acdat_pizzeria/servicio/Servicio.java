package com.example.acdat_pizzeria.servicio;

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

    public Boolean comprobarUsuarioContra(String usuario, String contra) {
        DbHelper db = new DbHelper(null);
        return db.comprobarUsuarioContra(usuario, contra);
    }

    public Boolean anyadirUsuarioContra(String usuario, String contra) {
        DbHelper db = new DbHelper(null);

        if (db.anyadirUsuarioContra(usuario, contra) == 0) {
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean existeFavorita(Usuario usuario) {
        if (obtenerPizzaFavorita(usuario) == null) {
            return false;
        }
        else {
            return true;
        }
    }

    public Pizza obtenerPizzaFavorita(Usuario usuario) {
        DbHelper db = new DbHelper(null);
        return db.obtenerPizzaFavorita(usuario);
    }

    public ArrayList<Pizza> obtenerPizzasPred() {
        DbHelper db = new DbHelper(null);
        return db.obtenerPizzasPred();
    }

    public void quitarFavorita(Usuario usuario) {
        DbHelper db = new DbHelper(null);
        db.quitarFavorita(usuario);
    }

    public void anyadirPizza(Pizza pizza) {
        DbHelper db = new DbHelper(null);
        db.anyadirPizza(pizza);
    }
}
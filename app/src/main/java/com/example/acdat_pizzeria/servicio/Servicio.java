package com.example.acdat_pizzeria.servicio;

import com.example.acdat_pizzeria.clases.Pizza;
import com.example.acdat_pizzeria.clases.Usuario;
import com.example.acdat_pizzeria.daos.DAOPizzas;
import com.example.acdat_pizzeria.daos.DAOUsuarios;
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

    public Pizza getPizzaPred(TipoNombre nombre) {
        return DAOPizzas.getInstance().getPizzaPred(nombre);
    }

    public Boolean comprobarUsuarioContra(String usuario, String contra) {
        return DAOUsuarios.getInstance().comprobarUsuarioContra(usuario, contra);
    }

    public Boolean anyadirUsuarioContra(String usuario, String contra) {
        return DAOUsuarios.getInstance().anyadirUsuarioContra(usuario, contra);
    }

    public Boolean existeFavorita(Usuario usuario) {
        return DAOPizzas.getInstance().existeFavorita(usuario);
    }

    public Pizza obtenerPizzaFavorita(Usuario usuario) {
        return DAOPizzas.getInstance().obtenerPizzaFavorita(usuario);
    }

    public ArrayList<Pizza> obtenerPizzasPred() {
        return DAOPizzas.getInstance().obtenerPizzasPred();
    }

    public void quitarFavorita() {
        DAOPizzas.getInstance().quitarFavorita();
    }

    public void anyadirPizza(Pizza pizza) {
        DAOPizzas.getInstance().anyadirPizza(pizza);
    }
}
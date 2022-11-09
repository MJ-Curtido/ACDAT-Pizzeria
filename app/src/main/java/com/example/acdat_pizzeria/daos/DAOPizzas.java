package com.example.acdat_pizzeria.daos;

import com.example.acdat_pizzeria.clases.Pizza;
import com.example.acdat_pizzeria.clases.Usuario;
import com.example.acdat_pizzeria.enums.TipoIngrediente;
import com.example.acdat_pizzeria.enums.TipoNombre;
import com.example.acdat_pizzeria.enums.TipoSalsa;
import com.example.acdat_pizzeria.enums.TipoTamanyo;

import java.util.ArrayList;

public class DAOPizzas {
    private ArrayList<Pizza> falsaBD;
    private static DAOPizzas dao = null;

    private DAOPizzas() {
        super();
        this.falsaBD = new ArrayList<Pizza>();

        ArrayList<TipoIngrediente> ingredientes1 = new ArrayList<TipoIngrediente>();
        ingredientes1.add(TipoIngrediente.PEPERONI);
        ingredientes1.add(TipoIngrediente.TERNERA);
        ingredientes1.add(TipoIngrediente.JAMON_YORK);
        ingredientes1.add(TipoIngrediente.BACON);

        ArrayList<TipoIngrediente> ingredientes2 = new ArrayList<TipoIngrediente>();
        ingredientes2.add(TipoIngrediente.ANCHOA);
        ingredientes2.add(TipoIngrediente.PIÑA);

        ArrayList<TipoIngrediente> ingredientes3 = new ArrayList<TipoIngrediente>();
        ingredientes3.add(TipoIngrediente.ACEITUNAS_NEGRAS);
        ingredientes3.add(TipoIngrediente.CEBOLLA);
        ingredientes3.add(TipoIngrediente.PIMIENTO);
        ingredientes3.add(TipoIngrediente.ATUN);

        falsaBD.add(new Pizza(TipoSalsa.BBQ, ingredientes1, TipoNombre.SOLO_CARNE));
        falsaBD.add(new Pizza(TipoSalsa.PICANTE, ingredientes2, TipoNombre.RARITA));
        falsaBD.add(new Pizza(TipoSalsa.SIN_SALSA, ingredientes3, TipoNombre.LA_PIZZA));
    }

    public static DAOPizzas getInstance() {
        if (dao == null) {
            dao = new DAOPizzas();
        }

        return dao;
    }

    public Pizza getPizzaPred(TipoNombre nombre) {
        Pizza pizza = new Pizza();
        for (int i = 0; i < falsaBD.size(); i++) {
            if (falsaBD.get(i).getNombre() == nombre) {
                pizza = falsaBD.get(i);
            }
        }

        return pizza;
    }

    public Boolean existeFavorita(Usuario usuario) {
        Boolean existe = false;

        for (int i = 0; i < falsaBD.size() && !existe; i++) {
            if (falsaBD.get(i).isFavorita() && falsaBD.get(i).getUsuario() == usuario) {
                existe = true;
            }
        }

        return existe;
    }
}
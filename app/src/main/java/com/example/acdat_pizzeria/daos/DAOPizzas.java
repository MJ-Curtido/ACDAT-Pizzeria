package com.example.acdat_pizzeria.daos;

import com.example.acdat_pizzeria.clases.Pizza;
import com.example.acdat_pizzeria.enums.TipoSalsa;
import com.example.acdat_pizzeria.enums.TipoTamanyo;

import java.util.ArrayList;

public class DAOPizzas {
    private ArrayList<Pizza> falsaBD;
    private static DAOPizzas dao = null;

    private DAOPizzas() {
        super();
        this.falsaBD = new ArrayList<Pizza>();
        /*
        falsaBD.add(new Pizza(TipoTamanyo.PEQUEÑA, TipoSalsa.BBQ, ));
        falsaBD.add(new Pizza());
        falsaBD.add(new Pizza());
        falsaBD.add(new Pizza());
        */
    }

    public static DAOPizzas getInstance() {
        if (dao == null) {
            dao = new DAOPizzas();
        }

        return dao;
    }
}
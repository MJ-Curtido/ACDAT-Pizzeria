package com.example.acdat_pizzeria.daos;

import com.example.acdat_pizzeria.clases.Pizza;
import com.example.acdat_pizzeria.clases.Usuario;

import java.util.ArrayList;

public class DAOUsuarios {
    private ArrayList<Usuario> falsaBD;
    private static DAOUsuarios dao = null;

    private DAOUsuarios() {
        super();
        this.falsaBD = new ArrayList<Usuario>();
        /*
        falsaBD.add(new Pizza(TipoTamanyo.PEQUEÑA, TipoSalsa.BBQ, ));
        falsaBD.add(new Pizza());
        falsaBD.add(new Pizza());
        falsaBD.add(new Pizza());
        */
    }

    public static DAOUsuarios getInstance() {
        if (dao == null) {
            dao = new DAOUsuarios();
        }

        return dao;
    }
}
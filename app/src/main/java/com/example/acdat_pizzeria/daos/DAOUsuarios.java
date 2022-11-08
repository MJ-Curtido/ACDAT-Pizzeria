package com.example.acdat_pizzeria.daos;

import com.example.acdat_pizzeria.clases.Usuario;

import java.util.ArrayList;

public class DAOUsuarios {
    private ArrayList<Usuario> falsaBD;
    private static DAOUsuarios dao = null;

    private DAOUsuarios() {
        super();
        this.falsaBD = new ArrayList<Usuario>();

        falsaBD.add(new Usuario("HK17", "1234567"));
        falsaBD.add(new Usuario("Pablete", "lloron"));
        falsaBD.add(new Usuario("Martuki", "peque"));
    }

    public static DAOUsuarios getInstance() {
        if (dao == null) {
            dao = new DAOUsuarios();
        }

        return dao;
    }

    public Boolean comprobarUsuarioContra(String usuario, String contra) {
        Boolean correcto = false;

        for (int i = 0; i < falsaBD.size() && !correcto; i++) {
            if (falsaBD.get(i).getNomUsuario().equals(usuario) && falsaBD.get(i).getContrasenya().equals(contra)) {
                correcto = true;
            }
        }

        return correcto;
    }

    private Boolean existeUsuario(String usuario) {
        Boolean existe = false;

        for (int i = 0; i < falsaBD.size() && !existe; i++) {
            if (falsaBD.get(i).getNomUsuario().equals(usuario)) {
                existe = true;
            }
        }

        return existe;
    }

    public Boolean anyadirUsuarioContra(String usuario, String contra) {
        Boolean anyadido = false;

        if (!this.existeUsuario(usuario)) {
            falsaBD.add(new Usuario(usuario, contra));

            anyadido = true;
        }

        return anyadido;
    }
}
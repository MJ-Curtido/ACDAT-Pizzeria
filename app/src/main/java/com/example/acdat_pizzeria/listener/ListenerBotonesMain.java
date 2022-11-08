package com.example.acdat_pizzeria.listener;

import android.view.View;

import com.example.acdat_pizzeria.R;
import com.example.acdat_pizzeria.vista.MainActivity;

public class ListenerBotonesMain implements View.OnClickListener {
    MainActivity main;

    public ListenerBotonesMain(MainActivity main) {
        this.main = main;

        main.getBinding().btnIniciarSesion.setOnClickListener(this);
        main.getBinding().btnRegistrarse.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btnIniciarSesion) {
            main.comprobarInicioSesion();
        }
        else {
            main.irARegistro();
        }
    }
}
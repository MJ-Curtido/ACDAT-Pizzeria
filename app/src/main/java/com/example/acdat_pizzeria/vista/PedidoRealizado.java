package com.example.acdat_pizzeria.vista;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.acdat_pizzeria.clases.Usuario;
import com.example.acdat_pizzeria.databinding.ActivityPedidoRealizadoBinding;

public class PedidoRealizado extends AppCompatActivity {
    ActivityPedidoRealizadoBinding binding;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferencias = getSharedPreferences ("datosApp", Context.MODE_PRIVATE);

        if (preferencias.getBoolean("modoOscuro", false)) {
            this.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            this.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        binding = ActivityPedidoRealizadoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        usuario = (Usuario) getIntent().getSerializableExtra("usuarioActual");
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(PedidoRealizado.this, PaginaPrincipal.class);
        i.putExtra("usuarioActual", usuario);
        startActivity(i);
    }
}
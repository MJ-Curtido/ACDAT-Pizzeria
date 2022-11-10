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

import com.example.acdat_pizzeria.R;
import com.example.acdat_pizzeria.databinding.ActivityConfiguracionBinding;

public class Configuracion extends AppCompatActivity implements View.OnClickListener {
    ActivityConfiguracionBinding binding;

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

        setContentView(R.layout.activity_configuracion);
        binding = ActivityConfiguracionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnModoOscuro.setOnClickListener(this);
        binding.btnCerrarSesion.setOnClickListener(this);

        if (this.getDelegate().getLocalNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            binding.btnModoOscuro.setChecked(true);
        }
        else {
            binding.btnModoOscuro.setChecked(false);
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Configuracion.this, PaginaPrincipal.class);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnModoOscuro) {
            if (binding.btnModoOscuro.isChecked()) {
                this.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                SharedPreferences preferencias = getSharedPreferences ("datosApp", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = preferencias.edit();

                editor.putBoolean("modoOscuro", true);

                editor.commit();
            }
            else {
                this.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                SharedPreferences preferencias = getSharedPreferences ("datosApp", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = preferencias.edit();

                editor.putBoolean("modoOscuro", false);

                editor.commit();
            }
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea cerrar sesión?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SharedPreferences preferencias = getSharedPreferences ("datosApp", Context.MODE_PRIVATE);

                            SharedPreferences.Editor editor = preferencias.edit();

                            editor.putString("nombreUsuario", "");
                            editor.putString("contraUsuario", "");

                            editor.commit();

                            Intent intent = new Intent(Configuracion.this, MainActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            builder.show();
        }
    }
}
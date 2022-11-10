package com.example.acdat_pizzeria.vista;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.acdat_pizzeria.R;
import com.example.acdat_pizzeria.clases.Usuario;
import com.example.acdat_pizzeria.databinding.ActivityMainBinding;
import com.example.acdat_pizzeria.servicio.Servicio;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;

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

        if (!preferencias.getString("nombreUsuario", "").equals("")) {
            Usuario usuario = new Usuario(preferencias.getString("nombreUsuario", ""), preferencias.getString("contraUsuario", ""));

            Intent i = new Intent(MainActivity.this, PaginaPrincipal.class);
            i.putExtra("usuarioActual", usuario);
            startActivity(i);
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnIniciarSesion.setOnClickListener(this);
        binding.btnRegistrarse.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Desea salir de la aplicación?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        builder.show();
    }

    public void comprobarInicioSesion() {
        if (Servicio.getInstance().comprobarUsuarioContra(binding.tbUsuario.getText().toString(), binding.tbContra.getText().toString())) {
            Usuario usuario = new Usuario(binding.tbUsuario.getText().toString(), binding.tbContra.getText().toString());

            Toast.makeText(this, "Ha iniciado sesión correctamente.", Toast.LENGTH_SHORT).show();

            if (binding.checkRecuerdame.isChecked()) {
                SharedPreferences preferencias = getSharedPreferences ("datosApp", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = preferencias.edit();

                editor.putString("nombreUsuario", binding.tbUsuario.getText().toString());
                editor.putString("contraUsuario", binding.tbContra.getText().toString());

                editor.commit();
            }

            Intent i = new Intent(MainActivity.this, PaginaPrincipal.class);
            i.putExtra("usuarioActual", usuario);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "Usuario o contraseña introducidos incorrectos.", Toast.LENGTH_SHORT).show();

            binding.tbContra.setText("");
        }
    }

    public void irARegistro() {
        Intent i = new Intent(MainActivity.this, Registro.class);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnIniciarSesion) {
            this.comprobarInicioSesion();
        }
        else {
            this.irARegistro();
        }
    }
}
package com.example.acdat_pizzeria.vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.acdat_pizzeria.R;
import com.example.acdat_pizzeria.databinding.ActivityMainBinding;
import com.example.acdat_pizzeria.servicio.Servicio;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnIniciarSesion.setOnClickListener(this);
        binding.btnRegistrarse.setOnClickListener(this);
    }

    public ActivityMainBinding getBinding() {
        return binding;
    }

    public void comprobarInicioSesion() {
        if (Servicio.getInstance().comprobarUsuarioContra(binding.tbUsuario.getText().toString(), binding.tbContra.getText().toString())) {
            //Hacer que te mueva a la página principal pasando el usuario
        }
        else {
            Toast.makeText(this, "Usuario o contraseña introducidos incorrectos", Toast.LENGTH_SHORT).show();
            binding.tbUsuario.setText("");
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
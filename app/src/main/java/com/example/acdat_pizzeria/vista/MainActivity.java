package com.example.acdat_pizzeria.vista;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.acdat_pizzeria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public ActivityMainBinding getBinding() {
        return binding;
    }

    public void comprobarInicioSesion() {

    }

    public void irARegistro() {
    }
}
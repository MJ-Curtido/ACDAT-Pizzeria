package com.example.acdat_pizzeria.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Toast;

import com.example.acdat_pizzeria.R;
import com.example.acdat_pizzeria.databinding.ActivityRegistroBinding;
import com.example.acdat_pizzeria.servicio.Servicio;

public class Registro extends AppCompatActivity implements View.OnClickListener{
    ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnRegistrarseRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (binding.tbContraRegistroRepetir.getText().toString().equals("") || binding.tbContraRegistro.getText().toString().equals("") || binding.tbUsuarioRegistro.getText().toString().equals("")) {
            Toast.makeText(this, "Debe rellenar todos los campos para registrarse.", Toast.LENGTH_SHORT).show();
        }
        else {
            if (binding.tbContraRegistroRepetir.getText().toString().equals(binding.tbContraRegistro.getText().toString())) {
                if (Servicio.getInstance().anyadirUsuarioContra(binding.tbUsuarioRegistro.getText().toString(), binding.tbContraRegistro.getText().toString())) {
                    Toast.makeText(this, "Se ha registrado correctamente.", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(Registro.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(this, "El usuario introducido ya existe.", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this, "Las contraseñas introducidas son diferentes.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
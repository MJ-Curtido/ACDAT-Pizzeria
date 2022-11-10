package com.example.acdat_pizzeria.vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.acdat_pizzeria.clases.Pizza;
import com.example.acdat_pizzeria.clases.Usuario;
import com.example.acdat_pizzeria.databinding.ActivityPersonalizadaBinding;
import com.example.acdat_pizzeria.enums.TipoIngrediente;
import com.example.acdat_pizzeria.enums.TipoSalsa;
import com.example.acdat_pizzeria.enums.TipoTamanyo;

import java.util.ArrayList;

public class Personalizada extends AppCompatActivity implements View.OnClickListener {
    ActivityPersonalizadaBinding binding;
    Usuario usuario;
    TipoTamanyo tamanyo;

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

        binding = ActivityPersonalizadaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        usuario = (Usuario) getIntent().getSerializableExtra("usuarioActual");
        tamanyo = (TipoTamanyo) getIntent().getSerializableExtra("tamanyoPizza");

        binding.btnContinuar.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Personalizada.this, ElegirTamanyoYTipo.class);
        i.putExtra("usuarioActual", usuario);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        TipoSalsa salsa;
        if (binding.rbtnSinSalsa.isChecked()) {
            salsa = TipoSalsa.SIN_SALSA;
        }
        else if (binding.rbtnBbq.isChecked()) {
            salsa = TipoSalsa.BBQ;
        }
        else if (binding.rbtnCarbonara.isChecked()) {
            salsa = TipoSalsa.CARBONARA;
        }
        else if (binding.rbtnPicante.isChecked()) {
            salsa = TipoSalsa.PICANTE;
        }
        else {
            salsa = TipoSalsa.YOGUR;
        }

        ArrayList<TipoIngrediente> ingredientes = new ArrayList<>();
        if (binding.cbAceitunas.isChecked()) {
            ingredientes.add(TipoIngrediente.ACEITUNAS_NEGRAS);
        }
        if (binding.cbAnchoa.isChecked()) {
            ingredientes.add(TipoIngrediente.ANCHOA);
        }
        if (binding.cbAtun.isChecked()) {
            ingredientes.add(TipoIngrediente.ATUN);
        }
        if (binding.cbBacon.isChecked()) {
            ingredientes.add(TipoIngrediente.BACON);
        }
        if (binding.cbCebolla.isChecked()) {
            ingredientes.add(TipoIngrediente.CEBOLLA);
        }
        if (binding.cbChampi.isChecked()) {
            ingredientes.add(TipoIngrediente.CHAMPINYONES);
        }
        if (binding.cbJamon.isChecked()) {
            ingredientes.add(TipoIngrediente.JAMON_YORK);
        }
        if (binding.cbPeperoni.isChecked()) {
            ingredientes.add(TipoIngrediente.PEPERONI);
        }
        if (binding.cbPimiento.isChecked()) {
            ingredientes.add(TipoIngrediente.PIMIENTO);
        }
        if (binding.cbPinya.isChecked()) {
            ingredientes.add(TipoIngrediente.PINYA);
        }
        if (binding.cbPollo.isChecked()) {
            ingredientes.add(TipoIngrediente.POLLO);
        }
        if (binding.cbTernera.isChecked()) {
            ingredientes.add(TipoIngrediente.TERNERA);
        }

        Pizza pizza = new Pizza(this.tamanyo, salsa, ingredientes, usuario);

        Intent i = new Intent(Personalizada.this, Pedir.class);
        i.putExtra("usuarioActual", usuario);
        i.putExtra("tamanyoPizza", tamanyo);
        i.putExtra("pizzaPedido", pizza);
        startActivity(i);
    }
}
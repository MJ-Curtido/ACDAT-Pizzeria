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
import com.example.acdat_pizzeria.databinding.ActivityPedirBinding;
import com.example.acdat_pizzeria.enums.TipoTamanyo;
import com.example.acdat_pizzeria.servicio.Servicio;

public class Pedir extends AppCompatActivity implements View.OnClickListener {
    ActivityPedirBinding binding;
    Usuario usuario;
    TipoTamanyo tamanyo;
    Pizza pizza;

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

        binding = ActivityPedirBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        usuario = (Usuario) getIntent().getSerializableExtra("usuarioActual");
        tamanyo = (TipoTamanyo) getIntent().getSerializableExtra("tamanyoPizza");
        pizza = (Pizza) getIntent().getSerializableExtra("pizzaPedido");

        binding.txtPedido.setText(pizza.toString());
        binding.btnConfirmar.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Pedir.this, PaginaPrincipal.class);
        i.putExtra("usuarioActual", usuario);
        i.putExtra("tamanyoPizza", tamanyo);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        if (binding.cbFav.isChecked()) {
            Servicio.getInstance().quitarFavorita(Pedir.this, this.usuario);

            pizza.setFavorita(true);
        }

        Servicio.getInstance().anyadirPizza(Pedir.this, pizza);

        Intent i = new Intent(Pedir.this, PedidoRealizado.class);
        i.putExtra("usuarioActual", usuario);
        startActivity(i);
    }
}
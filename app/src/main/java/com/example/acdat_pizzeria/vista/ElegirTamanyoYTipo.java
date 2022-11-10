package com.example.acdat_pizzeria.vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.acdat_pizzeria.R;
import com.example.acdat_pizzeria.clases.Pizza;
import com.example.acdat_pizzeria.clases.Usuario;
import com.example.acdat_pizzeria.databinding.ActivityElegirTamanyoYtipoBinding;
import com.example.acdat_pizzeria.enums.TipoNombre;
import com.example.acdat_pizzeria.enums.TipoTamanyo;
import com.example.acdat_pizzeria.servicio.Servicio;

public class ElegirTamanyoYTipo extends AppCompatActivity implements View.OnClickListener {
    Usuario usuario;
    ActivityElegirTamanyoYtipoBinding binding;

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

        binding = ActivityElegirTamanyoYtipoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        usuario = (Usuario) getIntent().getSerializableExtra("usuarioActual");

        binding.btnFavorita.setOnClickListener(this);
        binding.btnPredet.setOnClickListener(this);
        binding.btnPers.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ElegirTamanyoYTipo.this, PaginaPrincipal.class);
        i.putExtra("usuarioActual", usuario);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        TipoTamanyo tamanyo;

        if (binding.rbtnPequenya.isChecked()) {
            tamanyo = TipoTamanyo.PEQUEÑA;
        }
        else if (binding.rbtnMediana.isChecked()) {
            tamanyo = TipoTamanyo.MEDIANA;
        }
        else if (binding.rbtnFamiliar.isChecked()) {
            tamanyo = TipoTamanyo.FAMILIAR;
        }
        else {
            tamanyo = TipoTamanyo.MEDIO_METRO;
        }

        if (view.getId() == R.id.btnFavorita) {
            if (Servicio.getInstance().existeFavorita(usuario)) {
                Pizza pizza = Servicio.getInstance().obtenerPizzaFavorita(usuario);
                pizza.setTamanyo(tamanyo);
                pizza.calcularPrecio();
                pizza.setNombre(TipoNombre.PERSONALIZADA);

                Intent i = new Intent(ElegirTamanyoYTipo.this, Pedir.class);
                i.putExtra("usuarioActual", usuario);
                i.putExtra("tamanyoPizza", tamanyo);
                i.putExtra("pizzaPedido", pizza);
                startActivity(i);
            }
            else {
                Toast.makeText(this, "No tiene pizza favorita agregada.", Toast.LENGTH_SHORT).show();
            }
        }
        else if (view.getId() == R.id.btnPredet) {
            Intent i = new Intent(ElegirTamanyoYTipo.this, Predeterminadas.class);
            i.putExtra("usuarioActual", usuario);
            i.putExtra("tamanyoPizza", tamanyo);
            startActivity(i);
        }
        else {
            Intent i = new Intent(ElegirTamanyoYTipo.this, Personalizada.class);
            i.putExtra("usuarioActual", usuario);
            i.putExtra("tamanyoPizza", tamanyo);
            startActivity(i);
        }
    }
}
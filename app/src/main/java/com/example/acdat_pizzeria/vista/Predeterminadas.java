package com.example.acdat_pizzeria.vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.example.acdat_pizzeria.clases.Pizza;
import com.example.acdat_pizzeria.clases.Usuario;
import com.example.acdat_pizzeria.databinding.ActivityPredeterminadasBinding;
import com.example.acdat_pizzeria.enums.TipoNombre;
import com.example.acdat_pizzeria.enums.TipoTamanyo;
import com.example.acdat_pizzeria.servicio.Servicio;

import java.util.ArrayList;

public class Predeterminadas extends AppCompatActivity {
    ActivityPredeterminadasBinding binding;
    ArrayList<Pizza> pizzasPred;
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

        usuario = (Usuario) getIntent().getSerializableExtra("usuarioActual");
        tamanyo = (TipoTamanyo) getIntent().getSerializableExtra("tamanyoPizza");

        binding = ActivityPredeterminadasBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        pizzasPred = Servicio.getInstance().obtenerPizzasPred();

        ArrayList<String> pizzasPredNom = new ArrayList<>();

        for (int i = 0; i < pizzasPred.size(); i++) {
            pizzasPredNom.add(pizzasPred.get(i).getNombre().getTxt());
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pizzasPredNom);

        binding.listaPizzaPred.setAdapter(adaptador);

        binding.listaPizzaPred.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Pizza pizza = pizzasPred.get(position);
                pizza.setTamanyo(tamanyo);
                pizza.setUsuario(usuario);
                pizza.setNombre(TipoNombre.PERSONALIZADA);
                pizza.calcularPrecio();

                Intent i = new Intent(Predeterminadas.this, Pedir.class);
                i.putExtra("usuarioActual", usuario);
                i.putExtra("tamanyoPizza", tamanyo);
                i.putExtra("pizzaPedido", pizza);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Predeterminadas.this, ElegirTamanyoYTipo.class);
        i.putExtra("usuarioActual", usuario);
        startActivity(i);
    }
}
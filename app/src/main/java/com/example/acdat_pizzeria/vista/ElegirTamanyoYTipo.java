package com.example.acdat_pizzeria.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.acdat_pizzeria.R;
import com.example.acdat_pizzeria.clases.Usuario;
import com.example.acdat_pizzeria.databinding.ActivityElegirTamanyoYtipoBinding;
import com.example.acdat_pizzeria.servicio.Servicio;

public class ElegirTamanyoYTipo extends AppCompatActivity implements View.OnClickListener {
    Usuario usuario;
    ActivityElegirTamanyoYtipoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityElegirTamanyoYtipoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle bundle = getIntent().getExtras();

        usuario = (Usuario) bundle.get("usuarioActual");

        binding.btnFavorita.setOnClickListener(this);
        binding.btnPredet.setOnClickListener(this);
        binding.btnPers.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ElegirTamanyoYTipo.this, PaginaPrincipal.class);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnFavorita) {
            if (Servicio.getInstance().existeFavorita(usuario)) {
                Intent i = new Intent(ElegirTamanyoYTipo.this, Favorita.class);
                i.putExtra("usuarioActual", usuario);
                startActivity(i);
            }
            else {
                Toast.makeText(this, "No tiene pizza favorita agregada.", Toast.LENGTH_SHORT).show();
            }
        }
        else if (view.getId() == R.id.btnPredet) {
            Intent i = new Intent(ElegirTamanyoYTipo.this, Predeterminadas.class);
            i.putExtra("usuarioActual", usuario);
            startActivity(i);
        }
        else {
            Intent i = new Intent(ElegirTamanyoYTipo.this, Personalizada.class);
            i.putExtra("usuarioActual", usuario);
            startActivity(i);
        }
    }
}
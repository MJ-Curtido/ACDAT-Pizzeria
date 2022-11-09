package com.example.acdat_pizzeria.vista;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.acdat_pizzeria.R;
import com.example.acdat_pizzeria.clases.Usuario;
import com.example.acdat_pizzeria.databinding.ActivityPaginaPrincipalBinding;

public class PaginaPrincipal extends AppCompatActivity implements View.OnClickListener {
    Usuario usuario;
    ActivityPaginaPrincipalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaginaPrincipalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle bundle = getIntent().getExtras();

        usuario = (Usuario) bundle.get("usuarioActual");

        binding.btnWeb.setOnClickListener(this);
        binding.btnPedir.setOnClickListener(this);
        binding.btnConfig.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnWeb) {
            Intent intentWeb = new Intent();
            intentWeb.setAction(Intent.ACTION_VIEW);
            intentWeb.setData(Uri.parse("https://pizzerialaroma.com/"));
            startActivity(intentWeb);
        }
        else if (view.getId() == R.id.btnPedir) {
            Intent i = new Intent(PaginaPrincipal.this, ElegirTamanyoYTipo.class);
            i.putExtra("usuarioActual", usuario);
            startActivity(i);
        }
        else {
            Intent i = new Intent(PaginaPrincipal.this, Configuracion.class);
            startActivity(i);
        }
    }
}
package com.example.acdat_pizzeria.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import com.example.acdat_pizzeria.R;
import com.example.acdat_pizzeria.databinding.ActivityMainBinding;

public class Registro extends AppCompatActivity implements View.OnClickListener{
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //View view = binding.;


    }

    @Override
    public void onClick(View view) {

    }
}
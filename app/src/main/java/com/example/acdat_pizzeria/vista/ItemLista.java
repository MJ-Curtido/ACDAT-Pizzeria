package com.example.acdat_pizzeria.vista;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

public class ItemLista {
    private Drawable fotoPizza;
    private String textoPizza;

    public Drawable getFotoPizza() {
        return fotoPizza;
    }

    public void setFotoPizza(Drawable fotoPizza) {
        this.fotoPizza = fotoPizza;
    }

    public String getTextoPizza() {
        return textoPizza;
    }

    public void setTextoPizza(String textoPizza) {
        this.textoPizza = textoPizza;
    }

    public ItemLista(Drawable fotoPizza, String textoPizza) {
        this.fotoPizza = fotoPizza;
        this.textoPizza = textoPizza;
    }
}

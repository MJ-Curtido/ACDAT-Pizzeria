package com.example.acdat_pizzeria.clases;

import com.example.acdat_pizzeria.enums.TipoIngrediente;
import com.example.acdat_pizzeria.enums.TipoSalsa;
import com.example.acdat_pizzeria.enums.TipoTamanyo;

import java.util.ArrayList;
import java.util.Objects;

public class Pizza {
    private static Integer idTemp = 0;
    private Integer id;
    private TipoTamanyo tamanyo;
    private TipoSalsa salsa;
    private ArrayList<TipoIngrediente> ingredientes;
    private Boolean favorita;
    private String nombre;
    private Usuario usuario;

    public Pizza(TipoTamanyo tamanyo, TipoSalsa salsa, ArrayList<TipoIngrediente> ingredientes, Usuario usuario) {
        this.tamanyo = tamanyo;
        this.salsa = salsa;
        this.ingredientes = ingredientes;
        this.favorita = false;
        this.usuario = usuario;
        this.nombre = "personalizada";
        this.id = idTemp;
        idTemp++;
    }

    public Pizza(TipoTamanyo tamanyo) {

    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setFavorita(Boolean favorita) {
        this.favorita = favorita;
    }

    public Boolean isFavorita() {
        return this.favorita;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setTamanyo(TipoTamanyo tamanyo) {
        this.tamanyo = tamanyo;
    }

    public TipoTamanyo getTamanyo() {
        return this.tamanyo;
    }

    public void setSalsa(TipoSalsa salsa) {
        this.salsa = salsa;
    }

    public TipoSalsa getSalsa() {
        return this.salsa;
    }

    public void setIngredientes(ArrayList<TipoIngrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public ArrayList<TipoIngrediente> getIngredientes() {
        return this.ingredientes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return id.equals(pizza.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pizza -->" + "id: " + id + ", tamaño: " + tamanyo + ", salsa: " + salsa + ", ingredientes: " + ingredientes;
    }
}
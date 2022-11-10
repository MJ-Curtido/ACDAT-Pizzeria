package com.example.acdat_pizzeria.enums;

public enum TipoNombre {
    PERSONALIZADA(1, "Pizza personalizada"), SOLO_CARNE(2, "Pizza solo carne"), LA_PIZZA(3, "☆La Pizza☆"), RARITA(4, "Pizza rarita");

    private int idEnum;
    private String txt;

    private TipoNombre (int idEnum, String txt){
        this.idEnum = idEnum;
        this.txt = txt;
    }

    public String getTxt() {
        return txt;
    }

    public int getIdEnum() {
        return idEnum;
    }
}
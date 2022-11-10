package com.example.acdat_pizzeria.enums;

public enum TipoSalsa {
    BBQ(1, "Salsa barbacoa"), CARBONARA(2, "Salsa carbonara"), YOGUR(3, "Salsa yogur"), PICANTE(4, "Salsa picante"), SIN_SALSA(5, "Sin salsa");

    private int idEnum;
    private String txt;

    private TipoSalsa (int idEnum, String txt){
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
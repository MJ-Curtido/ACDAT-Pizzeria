package com.example.acdat_pizzeria.enums;

public enum TipoIngrediente {
    CEBOLLA(1, "Cebolla"), BACON(2, "Bacon"), POLLO(3, "Pollo"), TERNERA(4, "Ternera"), PIMIENTO(5, "Pimiento"), CHAMPINYONES(6, "Champiñones"), ACEITUNAS_NEGRAS(7, "Aceitunas negras"), ATUN(8, "Atún"), PEPERONI(9, "Peperoni"), JAMON_YORK(10, "Jamon york"), PINYA(11, "Piña"), ANCHOA(12, "Anchoa");

    private int idEnum;
    private String txt;

    private TipoIngrediente (int idEnum, String txt){
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
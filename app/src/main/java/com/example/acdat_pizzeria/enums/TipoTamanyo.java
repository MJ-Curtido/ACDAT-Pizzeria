package com.example.acdat_pizzeria.enums;

import java.io.Serializable;

public enum TipoTamanyo implements Serializable {
    PEQUEÑA(1, "Pequeña"), MEDIANA(2, "Mediana"), FAMILIAR(3, "Familiar"), MEDIO_METRO(4, "Medio metro");

    private int idEnum;
    private String txt;

    private TipoTamanyo (int idEnum, String txt){
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
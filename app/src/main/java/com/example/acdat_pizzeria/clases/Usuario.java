package com.example.acdat_pizzeria.clases;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {
    private String nomUsuario;
    private String contrasenya;

    public Usuario(String nomUsuario, String contrasenya) {
        this.nomUsuario = nomUsuario;
        this.contrasenya = contrasenya;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getNomUsuario() {
        return this.nomUsuario;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getContrasenya() {
        return this.contrasenya;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return nomUsuario.equals(usuario.nomUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomUsuario);
    }

    @Override
    public String toString() {
        return "Nombre de usuario: " + nomUsuario + ", contraseña: " + contrasenya;
    }
}